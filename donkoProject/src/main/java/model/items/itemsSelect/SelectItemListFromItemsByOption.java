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

		ArrayList<Object> paramList = new ArrayList<>() {{
			add(categoryName);
			for (int i = 0; i < checkedOption.length; i++) {
		        add(checkedOption[i]);
		    }
		}};
		ArrayList<ItemBean> optionList = new ArrayList<>();
		try (Connection conn = DatabaseConnection.getConnection()) {
			try (ResultSet result = GeneralDao.executeQuery(conn, SELECT_OPTION_ITEMLIST_SQL, paramList)) {
				
				// 1回目
				if(result.next()){ 
					ItemBean itemBean = new ItemBean();
					int itemId = result.getInt("item_id");
					String itemName = result.getString("item_name");
					String imageFileName = result.getString("file_name");
					itemBean.setItemId(itemId);
					itemBean.setItemName(itemName);
					itemBean.setImageFileName(imageFileName);
					optionList.add(itemBean);
				}
				
				// 2回目以降
				while(result.next()){ 
					ItemBean IBeans = new ItemBean();
					int itemId = result.getInt("item_id");
					String itemName = result.getString("item_name");
					String imageFileName = result.getString("file_name");
					
					// 画像名が一致するかを判定
					boolean isNotExist = isNotExist(optionList, imageFileName);
					// 一致しない場合は配列に格納
					if (isNotExist == true) {
						IBeans.setItemId(itemId);
						IBeans.setItemName(itemName);
						IBeans.setImageFileName(imageFileName);
						optionList.add(IBeans);
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
			return null;
		}
		return optionList;
	};
	
	// SQL文の?を検索キーワードの数だけ用意する
	private static String questionNum(String[] checkedOption) {
		String str = "?";
	    for (int index = 1; index < checkedOption.length; index++) {
	        str += ",?";
	    }
	    return str;
	}
	
	// 取得したデータを格納する配列の中に同じ画像名がないかを判定
	private static boolean isNotExist(ArrayList<ItemBean> optionList, String imageFileName) {
		boolean isNotExist = false;
		for (int i = 0; i < optionList.size(); i++) {
			// i番目のファイル名の取得
			ItemBean itembean = optionList.get(i);
			String fileNameInList = itembean.getImageFileName();
			
			// ファイル名が一致するかを判定
			if (!imageFileName.equals(fileNameInList)) {
				isNotExist = true;
			} else {
				isNotExist = false;
				break;
			}
		}
		return isNotExist;
	}
}
