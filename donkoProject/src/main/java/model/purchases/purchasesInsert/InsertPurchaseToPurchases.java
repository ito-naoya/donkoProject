package model.purchases.purchasesInsert;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import bean.PurchaseBean;
import dao.DatabaseConnection;
import dao.GeneralDao;

public class InsertPurchaseToPurchases {
	
	//商品を購入する
	public static void insertPurchaseToPurchases(PurchaseBean purchaseBean){
		
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO "				);
		sql.append(		"purchases"				);
		sql.append("("							);
		sql.append(		"user_id, "				);
		sql.append(		"total_amount, "		);
		sql.append(		"shipping_address_id, "	);
		sql.append(		"shipping_id"			);
		sql.append(")"							);
		sql.append("VALUES "						);
		sql.append("("							);	
		sql.append(		"?, "					);
		sql.append(		"?, "					);
		sql.append(		"?, "					);
		sql.append(		"1"						);
		sql.append(")"							);
		final String INSERT_PURCHASES_SQL = sql.toString();
		
		ArrayList<Object> params = new ArrayList<Object>();
		params.add(purchaseBean.getUserId());
		params.add(purchaseBean.getTotalAmount());
		params.add(purchaseBean.getShippingAddressId());
		
		try(Connection conn = DatabaseConnection.getConnection()){
			try {
			GeneralDao.executeUpdate(conn, INSERT_PURCHASES_SQL, params);
			conn.commit();
			}catch(SQLException e) {
				if(!conn.isClosed()) {
					conn.rollback();
				}
				e.printStackTrace();
			}
		}catch(SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}

	};

}
