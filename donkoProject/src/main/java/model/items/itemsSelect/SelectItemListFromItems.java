package model.items.itemsSelect;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import bean.ItemBean;
import dao.DatabaseConnection;
import dao.GeneralDao;

public class SelectItemListFromItems {
	
	//商品の一覧を取得する
	public static ArrayList<ItemBean> selectItemListFromItems(){
		final String SELECT_ITEMLIST_SQL = "SELECT item_id, file_name, item_category_name, item_name FROM items";
//				+ "GROUP BY file_name, item_category_name, item_name "
//				+ "HAVING COUNT(*) = 1";
		ArrayList<ItemBean> Itemlist = new ArrayList<>();
		ArrayList<Object> paramLists = new ArrayList<>() {{ }};
		try (Connection conn = DatabaseConnection.getConnection()) {
			try {
				// resultsetはcloseの必要がない？ので無闇にtryの(）内に記述してcloseする必要はないかも
				ResultSet result = GeneralDao.executeQuery(conn, SELECT_ITEMLIST_SQL, paramLists);
				while (result.next()) {
					ItemBean IBeans = new ItemBean();
					
					int itemId = result.getInt("item_id");
					String imageFileName = result.getString("file_name");
					String itemCategoryName = result.getString("item_category_name");
	                String itemName = result.getString("item_name");
	                
	                IBeans.setItemId(itemId);
	                IBeans.setItemName(itemName);
	                IBeans.setItemCategoryName(itemCategoryName);
	                IBeans.setImageFileName(imageFileName);
	                
	                Itemlist.add(IBeans);
	            }
			// SQL実行時のExceptionをcatch
			} catch (SQLException e) {
				if (conn != null && !conn.isClosed()&& !conn.getAutoCommit()) {
					conn.rollback();
					e.printStackTrace();
				}
			}
		// getConnection時のExceptionをcatch
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return Itemlist;
	};

}
