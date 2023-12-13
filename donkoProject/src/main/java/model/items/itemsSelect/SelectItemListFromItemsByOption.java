package model.items.itemsSelect;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import bean.ItemBean;
import dao.DatabaseConnection;
import dao.GeneralDao;

public class SelectItemListFromItemsByOption {
	//商品をカテゴリとオプション指定して取得する
	public static ArrayList<ItemBean> selectItemListFromItemsByOption(String[] checkedOption, String categoryName){
		final String SELECT_OPTION_ITEMLIST_SQL = "SELECT items.item_id, items.item_name, items.file_name, option_categories.option_category_value "
				+ "FROM items "
				+ "INNER JOIN options ON items.item_id = options.item_id "
				+ "INNER JOIN option_categories ON options.option_category_name = option_categories.option_category_name "
				+ "AND items.item_category_name = ? "
				+ "WHERE option_categories.option_category_value IN (" + checkedOption(checkedOption) + ")";
		ArrayList<Object> paramLists = new ArrayList<>() {{
			add(categoryName);
		}};
		ArrayList<ItemBean> OptionList = new ArrayList<>();
		try (Connection conn = DatabaseConnection.getConnection()) {
			try (ResultSet result = GeneralDao.executeQuery(conn, SELECT_OPTION_ITEMLIST_SQL, paramLists)) {
				
				while(result.next()){ 
					ItemBean IBeans = new ItemBean();
					int itemId = result.getInt("item_id");
					String itemName = result.getString("item_name");
					String imageFileName = result.getString("file_name");
					IBeans.setItemId(itemId);
					IBeans.setItemName(itemName);
					IBeans.setImageFileName(imageFileName);
					OptionList.add(IBeans);
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
		return OptionList;
	};
	
	public static String checkedOption(String[] checkedOption) {
		String str = "'" + checkedOption[0] + "'";
	    for (int index = 1; index < checkedOption.length; index++) {
	        str += "," + "'" + checkedOption[index] + "'";
	    }
	    return str;
	}
}
