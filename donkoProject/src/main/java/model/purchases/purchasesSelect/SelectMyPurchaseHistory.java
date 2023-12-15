package model.purchases.purchasesSelect;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import bean.PurchaseBean;
import classes.user.CustomerUser;
import dao.DatabaseConnection;
import dao.GeneralDao;

public class SelectMyPurchaseHistory {
	
	//購入履歴を取得する
	public static ArrayList<PurchaseBean> selectMyPurchaseHistory(CustomerUser CustomerUser){
			
			// SQLコマンド生成
			StringBuilder sb = new StringBuilder();
			sb.append("SELECT ");
			sb.append(	"purchases.purchase_id, "													);
			sb.append(	"purchases.total_amount, "													);
			sb.append(	"purchases.purchase_date, "													);
			sb.append(	"shipping_addresses.address, "												);
			sb.append(	"shippings.shipping_status "												);
			sb.append("FROM "																		);
			sb.append(	"( "																		);
			sb.append(	"purchases "																);
			sb.append(	"INNER JOIN "																);
			sb.append(	"shipping_addresses "														);
			sb.append(	"ON "																		);
			sb.append(	"purchases.shipping_address_id = shipping_addresses.shipping_address_id "	);
			sb.append(	"INNER JOIN "																);
			sb.append(	"shippings "																);
			sb.append(	"ON "																		);
			sb.append(	"purchases.shipping_id = shippings.shipping_id "							);
			sb.append(	") "																		);
			sb.append("WHERE "																		);
			sb.append(	"purchases.user_id = ? "													);
			sb.append("ORDER BY "																	);
			sb.append(	"purchases.purchase_date DESC;"												);
			String sql = sb.toString();
			
			// ？の値を渡す
			ArrayList<Object> params = new ArrayList<Object>();
			params.add(CustomerUser.getUserId());
			
			ArrayList<PurchaseBean> purchaseList = new ArrayList<PurchaseBean>();
			PurchaseBean purchaseBean;
			
			// SQL実行
			try (Connection connection = DatabaseConnection.getConnection()){
				try (ResultSet results = GeneralDao.executeQuery(connection, sql, params)) {
					
					while (results.next()) {
						purchaseBean = new PurchaseBean();
						
						purchaseBean.setPurchaseId(results.getInt("purchase_id"));
						purchaseBean.setTotalAmount(results.getInt("total_amount"));
						purchaseBean.setPurchaseDate(results.getTimestamp("purchase_date"));
						purchaseBean.setShippingAddress(results.getString("address"));
						purchaseBean.setShippingStatus(results.getString("shipping_status"));
						purchaseList.add(purchaseBean);
						}
					} catch (SQLException e) {
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
			return purchaseList;
		}
}
