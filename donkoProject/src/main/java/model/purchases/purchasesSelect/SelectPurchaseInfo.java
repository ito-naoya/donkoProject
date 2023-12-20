package model.purchases.purchasesSelect;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import bean.PurchaseBean;
import dao.DatabaseConnection;
import dao.GeneralDao;

public class SelectPurchaseInfo {
	
	//一件の購入情報を取得する
	public static PurchaseBean selectPurchaseInfo(PurchaseBean purchaseBean){
  		StringBuilder sb = new StringBuilder();
		sb.append("SELECT "                      );
		sb.append(    "p.purchase_id, "          );
		sb.append(    "u.user_name, "            );
		sb.append(    "p.purchase_date, "        );
		sb.append(    "p.total_amount, "         );
		sb.append(    "p.shipping_id "           );
		sb.append("FROM "                        );
		sb.append(    "purchases p "             );
		sb.append("INNER JOIN "                  );
		sb.append(    "users u "                 );
		sb.append("ON "                          );
		sb.append(    "p.user_id = u.user_id "   );
		sb.append("WHERE "                       );
		sb.append(    "p.purchase_id = ?"        );
		
		// SQLを文字列化
		final String SELECT_PURCHASE_INFO_SQL = sb.toString();
		
		ArrayList<Object> paramList = new ArrayList<>() {{ 
			add(purchaseBean.getPurchaseId());
		}};
		
		PurchaseBean purchaseInfo = new PurchaseBean();
		try (Connection conn = DatabaseConnection.getConnection()) {
			try (ResultSet result = GeneralDao.executeQuery(conn, SELECT_PURCHASE_INFO_SQL, paramList)) {
				
				while (result.next()) {
					int purchaseId = result.getInt("purchase_id");
					String userName = result.getString("user_name");
					Timestamp purchaseDate = result.getTimestamp("purchase_date");
					int totalAmount = result.getInt("total_amount");
					int shippingId = result.getInt("shipping_id");
					purchaseInfo.setPurchaseId(purchaseId);
					purchaseInfo.setUserName(userName);
					purchaseInfo.setPurchaseDate(purchaseDate);
					purchaseInfo.setTotalAmount(totalAmount);
					purchaseInfo.setShippingId(shippingId);
				}
				
			} catch (Exception e) {
				if (conn != null && !conn.isClosed()&& !conn.getAutoCommit()) {
					conn.rollback();
					e.printStackTrace();
				}
				return null;
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			return null;
		}
		return purchaseInfo;
	}
}
