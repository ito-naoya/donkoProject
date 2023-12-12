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
		sb.append("SELECT " );
		sb.append(	"purchase_details.purchase_id, "			);
		sb.append(	"items.item_id, "							);
		sb.append(	"items.item_name, "							);
		sb.append(	"purchase_details.purchase_amount, "		);
		sb.append(	"purchase_details.quantity "				);
		sb.append("FROM "										);
		sb.append(	"( "										);
		sb.append(	"purchase_details "							);
		sb.append("INNER JOIN "									);
		sb.append(	"items "									);
		sb.append("ON "											);
		sb.append(	"purchase_details.item_id = items.item_id "	);
		sb.append(	") "										);
		sb.append("WHERE "										);
		sb.append(	"purchase_details.purchase_id = ?"			);
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
					purchaseDetailBeans.setItemId(results.getInt("item_id"));
					purchaseDetailBeans.setItemName(results.getString("item_name"));
					purchaseDetailBeans.setPurchaseAmount(results.getInt("purchase_amount"));
					purchaseDetailBeans.setQuantity(results.getInt("quantity"));
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
