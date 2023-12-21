package model.purchaseDetails.purchaseDetailsSelect;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import bean.PurchaseDetailBean;
import dao.DatabaseConnection;
import dao.GeneralDao;

public class SelectPurchaseDetail {
	
	//購入の詳細情報を取得する
	public static ArrayList<PurchaseDetailBean> selectPurchaseDetail (PurchaseDetailBean purchaseDetailBean){
		
		// SQLコマンド生成
		StringBuilder sb = new StringBuilder();

		sb.append("SELECT "                                       			    				);
		sb.append(	  "pd.purchase_id, "			            								);
		sb.append(	  "pd.purchase_detail_id, "	                								);
		sb.append(	  "pd.purchase_amount, "		            								);
		sb.append(	  "pd.quantity, "			            									);
		sb.append(	  "i.item_name, "				    										);	
		sb.append(	  "i.file_name, "				    										);
		sb.append(	  "group_concat(oc.option_category_value separator ',') "					);
		sb.append("FROM "											           					);
		sb.append(	  "purchase_details pd "													);
		sb.append("INNER JOIN "									            					);
		sb.append(	  "items i "								        						);
		sb.append("ON "										            	    				);
		sb.append(	  "pd.item_id = i.item_id "                                 				);
		sb.append("INNER JOIN "									            					);
		sb.append(	  "options o "								        						);
		sb.append("ON "										            	    				);
		sb.append(	  "i.item_id = o.item_id "                               					);
		sb.append("INNER JOIN "									            					);
		sb.append(	  "option_categories oc "								 					);
		sb.append("ON "										            	    				);
		sb.append(	  "oc.option_category_name = o.option_category_name "     					);
		sb.append("AND "										            					);
		sb.append(	  "oc.option_category_increment_id = o.option_category_increment_id "       );
		sb.append("WHERE "																		);
		sb.append(	  "pd.purchase_id = ? "														);
		sb.append("GROUP BY "																	);
		sb.append(	  "pd.purchase_detail_id, "													);
		sb.append(	  "i.item_id "																);

		String sql = sb.toString();
		
		// ？の引数に渡す値
		ArrayList<Object> param = new ArrayList<Object>();
		param.add(purchaseDetailBean.getPurchaseId());
		
		ArrayList<PurchaseDetailBean> purchaseDetailList = new ArrayList<PurchaseDetailBean>();
		PurchaseDetailBean purchaseDetailBeans;
		
		try (Connection connection = DatabaseConnection.getConnection()) {
			try (ResultSet results = GeneralDao.executeQuery(connection, sql, param)) {
				
				while (results.next()) {
					purchaseDetailBeans = new PurchaseDetailBean();
					purchaseDetailBeans.setPurchaseId(results.getInt("purchase_id"));
					purchaseDetailBeans.setPurchaseDetailId(results.getInt("purchase_detail_id"));
					purchaseDetailBeans.setPurchaseAmount(results.getInt("purchase_amount"));
					purchaseDetailBeans.setQuantity(results.getInt("quantity"));
					purchaseDetailBeans.setItemName(results.getString("item_name"));
					purchaseDetailBeans.setImageFileName(results.getString("file_name"));
					purchaseDetailBeans.setOptionCategoryValue(results.getString("group_concat(oc.option_category_value separator ',')"));
					
					purchaseDetailList.add(purchaseDetailBeans);
				}
			} catch (Exception e) {
				if(!connection.isClosed()) {
					connection.rollback();
					e.printStackTrace();
					return null;
				}
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			return null;
		}
		return purchaseDetailList;
	}
}
