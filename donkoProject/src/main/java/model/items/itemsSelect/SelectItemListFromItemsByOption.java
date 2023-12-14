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
		final String SELECT_OPTION_ITEMLIST_SQL = "SELECT items.item_id, items.item_name, items.file_name "
				+ "FROM items "
				+ "INNER JOIN options ON items.item_id = options.item_id "
				+ "INNER JOIN option_categories ON "
				+ "options.option_category_name = option_categories.option_category_name AND "
				+ "options.option_category_increment_id = option_categories.option_category_increment_id "
				+ "WHERE items.item_category_name = ? "
				+ "AND option_categories.option_category_value IN (" + questionNum(checkedOption) + ")";

		ArrayList<Object> paramLists = new ArrayList<>() {{
			add(categoryName);
			for (int index = 0; index < checkedOption.length; index++) {
		        add(checkedOption[index]);
		    }
		}};
		ArrayList<ItemBean> OptionList = new ArrayList<>();
		try (Connection conn = DatabaseConnection.getConnection()) {
			try (ResultSet result = GeneralDao.executeQuery(conn, SELECT_OPTION_ITEMLIST_SQL, paramLists)) {
				
				// 1回目
				if(result.next()){ 
					ItemBean IBeans = new ItemBean();
					int itemId = result.getInt("item_id");
					String itemName = result.getString("item_name");
					String imageFileName = result.getString("file_name");
					IBeans.setItemId(itemId);
					IBeans.setItemName(itemName);
					IBeans.setImageFileName(imageFileName);
					OptionList.add(IBeans);
				}
				
				// 2回目以降
				while(result.next()){ 
					ItemBean IBeans = new ItemBean();
					int itemId = result.getInt("item_id");
					String itemName = result.getString("item_name");
					String imageFileName = result.getString("file_name");
					
					boolean isNotExist = isNotExist(OptionList, imageFileName);
					if (isNotExist == true) {
						IBeans.setItemId(itemId);
						IBeans.setItemName(itemName);
						IBeans.setImageFileName(imageFileName);
						OptionList.add(IBeans);
					}
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
	
	protected static String questionNum(String[] checkedOption) {
		String str = "?";
	    for (int index = 1; index < checkedOption.length; index++) {
	        str += ",?";
	    }
	    return str;
	}
	
	protected static boolean isNotExist(ArrayList<ItemBean> OptionList, String imageFileName) {
		boolean isNotExist = false;
		for (int i = 0; i < OptionList.size(); i++) {
			// i番目のファイル名の取得
			ItemBean IB = OptionList.get(i);
			String FN_num_i = IB.getImageFileName();
			
			if (!imageFileName.equals(FN_num_i)) {
				isNotExist = true;
			} else {
				isNotExist = false;
			}
		}
		return isNotExist;
	}
}
