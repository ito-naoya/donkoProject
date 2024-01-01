package model.purchases.purchasesSelect;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import bean.PurchaseBean;
import dao.DatabaseConnection;
import dao.GeneralDao;

public class SelectOrderItemList {
	
	//受注一覧を取得する
	public static ArrayList<PurchaseBean> selectOrderItemList(){
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT "                                                  );
		sb.append(	  "p.purchase_id, "                                      );
		sb.append(	  "p.purchase_date, "                                    );
		sb.append(	  "p.user_id, "                                          );
		sb.append(	  "u.user_name, "                                        );
		sb.append(	  "p.total_amount, "                                     );
		sb.append(	  "p.postal_code, "                                      );
		sb.append(	  "p.address, "                                          );
		sb.append(	  "p.addressee, "                                        );
		sb.append(	  "s.shipping_status "                                   );
		sb.append("FROM "                                                    );
		sb.append(	  "purchases p "                                         );
		sb.append("INNER JOIN "                                              );
		sb.append(    "users u "                                             );
		sb.append("ON "                                                      );
		sb.append(    "p.user_id = u.user_id "                               );
		sb.append("INNER JOIN "                                              );
		sb.append(    "shippings s "                                         );
		sb.append("ON "                                                      );
		sb.append(    "p.shipping_id = s.shipping_id "                       );
		sb.append("ORDER BY "                                                );
		sb.append(    "purchase_id "                                         );
		sb.append("DESC"                                                     );
		
		// SQLを文字列化
		final String SELECT_ORDER_ITEMLIST_SQL = sb.toString();
		
		ArrayList<PurchaseBean> orderItemlist = new ArrayList<>();
		ArrayList<Object> paramLists = new ArrayList<>() {{ }};
		try (Connection conn = DatabaseConnection.getConnection()) {
			try (ResultSet result = GeneralDao.executeQuery(conn, SELECT_ORDER_ITEMLIST_SQL, paramLists)) {
				
				while (result.next()) {
					PurchaseBean pb = new PurchaseBean();
				    int purchaseId = result.getInt("purchase_id");
					Timestamp purchaseDate = result.getTimestamp("purchase_date");
					int userId = result.getInt("user_id");
				    String userName = result.getString("user_name");
				    int totalAmount = result.getInt("total_amount");
				    String postalCode = result.getString("postal_code");
				    String address = result.getString("address");
				    String addressee = result.getString("addressee");
				    String shippingStatus = result.getString("shipping_status");
					
				    pb.setPurchaseId(purchaseId);
				    pb.setPurchaseDate(purchaseDate);
				    pb.setUserId(userId);
				    pb.setUserName(userName);
				    pb.setTotalAmount(totalAmount);
				    pb.setPostalCode(postalCode);
				    pb.setAddress(address);
				    pb.setAddressee(addressee);
				    pb.setShippingStatus(shippingStatus);
				    
					orderItemlist.add(pb);
				}
			} catch (SQLException e) {
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
		return orderItemlist;
	};
}
