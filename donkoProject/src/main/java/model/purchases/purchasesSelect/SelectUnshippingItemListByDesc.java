package model.purchases.purchasesSelect;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import bean.PurchaseBean;
import dao.DatabaseConnection;
import dao.GeneralDao;

public class SelectUnshippingItemListByDesc {
	
	//未発送の商品一覧を降順で取得する
	public static ArrayList<PurchaseBean> selectUnshippingItemListByDesc(){
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT ");
		sb.append("    * ");
		sb.append("FROM ");
		sb.append("    purchases ");
		sb.append("WHERE ");
		sb.append("    shipping_id = 1 ");
		sb.append("ORDER BY ");
		sb.append("    purchase_date DESC");
		
		// SQLを文字列化
		final String SELECT_PROSESSING_LIST_SQL = sb.toString();

		ArrayList<Object> paramList = new ArrayList<>() {{ }};
		ArrayList<PurchaseBean> prosessingList = new ArrayList<>();
		try (Connection conn = DatabaseConnection.getConnection()) {
			try (ResultSet result = GeneralDao.executeQuery(conn, SELECT_PROSESSING_LIST_SQL, paramList)) {
				
				while(result.next()){ 
					PurchaseBean purchaseBean = new PurchaseBean();
					int purchaseId = result.getInt("purchase_id");
					int userId = result.getInt("user_id");
					int shippingAddressId = result.getInt("shipping_address_id");
					Timestamp purchaseDate = result.getTimestamp("purchase_date");
					int totalAmount = result.getInt("total_amount");
					purchaseBean.setPurchaseId(purchaseId);
					purchaseBean.setUserId(userId);
					purchaseBean.setShippingAddressId(shippingAddressId);
					purchaseBean.setPurchaseDate(purchaseDate);
					purchaseBean.setTotalAmount(totalAmount);
					
					prosessingList.add(purchaseBean);
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
		return prosessingList;
	}
}
