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
	public static ArrayList<PurchaseBean> selectPurchaseInfo(PurchaseBean purchaseBean){
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT " );
		sb.append("SELECT " );
		sb.append("SELECT " );
		sb.append("SELECT " );
		sb.append("SELECT " );
		sb.append("SELECT " );
		
		
		// SQLを文字列化
		final String SELECT_PURCHASE_INFO_SQL = sb.toString();
		
		ArrayList<Object> paramList = new ArrayList<>() {{ 
			add(purchaseBean.getPurchaseId());
		}};
		ArrayList<PurchaseBean> purchaseInfo = new ArrayList<PurchaseBean>();
		
		try (Connection conn = DatabaseConnection.getConnection()) {
			try (ResultSet result = GeneralDao.executeQuery(conn, SELECT_PURCHASE_INFO_SQL, paramList)) {
				
				while (result.next()) {
					PurchaseBean pb = new PurchaseBean();
					int purchaseId = result.getInt("purchase_id");
					String userName = result.getString("user_name");
					Timestamp purchaseDate = result.getTimestamp("purchase_date");
					int totalAmount = result.getInt("total_amount");
					pb.setPurchaseId(purchaseId);
					pb.setUserName(userName);
					pb.setPurchaseDate(purchaseDate);
					pb.setTotalAmount(totalAmount);
					
					purchaseInfo.add(purchaseBean);
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
