package model.items.itemsSelect;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import bean.ItemBean;
import dao.DatabaseConnection;
import dao.GeneralDao;

public class SelectItemListFromItemsByCategory {
	public static ArrayList<ItemBean> selectItemListFromItemsByCategory(ItemBean itemBean){
		final String SELECT_ITEMLIST_SQL = "SELECT item_id, item_name, file_name FROM items WHERE items.item_category_name = ?";
		ArrayList<ItemBean> Itemlist = new ArrayList<>();
		ArrayList<Object> paramLists = new ArrayList<>() {{
			add(itemBean.getItemCategoryName());
		}};
		try (Connection conn = DatabaseConnection.getConnection()) {
			try (ResultSet result = GeneralDao.executeQuery(conn, SELECT_ITEMLIST_SQL, paramLists)) {
				while (result.next()) {
					ItemBean IBeans = new ItemBean();
					
					int itemId = result.getInt("item_id");
					String itemName = result.getString("item_name");
					String imageFileName = result.getString("file_name");

					IBeans.setItemId(itemId);
					IBeans.setImageFileName(itemName);
					IBeans.setImageFileName(imageFileName);

					Itemlist.add(IBeans);
				}
			} catch (SQLException e) {
				if (conn != null && !conn.isClosed()&& !conn.getAutoCommit()) {
					conn.rollback();
					e.printStackTrace();
				}
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return Itemlist;
	};
}
