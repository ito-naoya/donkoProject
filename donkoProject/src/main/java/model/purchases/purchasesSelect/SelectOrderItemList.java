package model.purchases.purchasesSelect;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import bean.PurchaseBean;
import dao.DatabaseConnection;
import dao.GeneralDao;

public class SelectOrderItemList {
	
	//受注一覧を取得する
	public static ArrayList<PurchaseBean> selectOrderItemList(){
		final String SELECT_ORDER_ITEMLIST_SQL = "SELECT item_id, item_name, file_name FROM items WHERE items.item_delete_flg = 0 AND item_category_name = ?";
		ArrayList<PurchaseBean> orderItemlist = new ArrayList<>();
		ArrayList<Object> paramLists = new ArrayList<>() {{
		
		}};
		try (Connection conn = DatabaseConnection.getConnection()) {
			try (ResultSet result = GeneralDao.executeQuery(conn, SELECT_ORDER_ITEMLIST_SQL, paramLists)) {
				
				while (result.next()) {
				    int itemId = result.getInt("item_id");
				    String itemName = result.getString("item_name");
				    String imageFileName = result.getString("file_name");
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
		}
		return orderItemlist;
	};
}
