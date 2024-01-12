package model.purchases.purchasesUpdate;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import bean.PurchaseBean;
import dao.DatabaseConnection;
import dao.GeneralDao;

public class UpdateShippingStatus {
	
	//商品の発送処理をする
	public static Boolean updateShippingStatus(PurchaseBean purchaseBean){
		StringBuilder sb = new StringBuilder();
		sb.append("UPDATE "						);
		sb.append(		"purchases "			);
		sb.append("SET "						);
		sb.append(		"shipping_id = ? "		);
		sb.append("WHERE "						);
		sb.append(		"purchase_id = ?"		);
		
		// SQLを文字列化
		final String UPDATE_SHIPPING_SQL = sb.toString();
		ArrayList<Object> paramList = new ArrayList<>() {{
			add(purchaseBean.getShippingId());
			add(purchaseBean.getPurchaseId());
		}};
		
		int totalUpdatedRows = 0;
		
		try (Connection conn = DatabaseConnection.getConnection()) {
			try {
				totalUpdatedRows += GeneralDao.executeUpdate(conn, UPDATE_SHIPPING_SQL, paramList);
				
				if(totalUpdatedRows < 1) throw new SQLException();
				
				conn.commit();
			} catch (SQLException e) {
				if (conn != null && !conn.isClosed()&& !conn.getAutoCommit()) {
					conn.rollback();
				}
				e.printStackTrace();
				return false;
			} finally {
		        if (conn != null && !conn.isClosed()) {
		            conn.close();
		        }
		    }
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	};
}
