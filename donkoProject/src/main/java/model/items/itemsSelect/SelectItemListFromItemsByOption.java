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
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT ");
		sb.append("    items.item_id, ");
		sb.append("    items.item_name, ");
		sb.append("    items.file_name ");
		sb.append("FROM ");
		sb.append("    items ");
		sb.append("INNER JOIN ");
		sb.append("    options ");
		sb.append("ON ");
		sb.append("    items.item_id = options.item_id ");
		sb.append("INNER JOIN ");
		sb.append("    option_categories ");
		sb.append("ON ");
		sb.append("    options.option_category_name = option_categories.option_category_name ");
		sb.append("AND ");
		sb.append("    options.option_category_increment_id = option_categories.option_category_increment_id ");
		sb.append("WHERE ");
		sb.append("    items.item_category_name = ? ");
		sb.append("AND ");
		sb.append("    option_categories.option_category_value ");
		sb.append("    IN (" + questionNum(checkedOption) + ")");
		
		// SQLを文字列化
		final String SELECT_OPTION_ITEMLIST_SQL = sb.toString();

		ArrayList<Object> paramList = new ArrayList<>() {{
			add(categoryName);
			for (int i = 0; i < checkedOption.length; i++) {
		        add(checkedOption[i]);
		    }
		}};
		ArrayList<ItemBean> optionList = new ArrayList<>();
		try (Connection conn = DatabaseConnection.getConnection()) {
			try (ResultSet result = GeneralDao.executeQuery(conn, SELECT_OPTION_ITEMLIST_SQL, paramList)) {
				
				while(result.next()){ 
					ItemBean IBeans = new ItemBean();
					int itemId = result.getInt("item_id");
					String itemName = result.getString("item_name");
					String imageFileName = result.getString("file_name");
					
					// 画像名が一致するかを判定
					boolean isNotExist = checkExist(optionList, imageFileName);
					// 一致しない場合は配列に格納
					if (isNotExist) {
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
	
	// 取得したデータを格納した配列内に同じ画像名が存在するかを判定
		private static boolean checkExist(ArrayList<ItemBean> optionList, String imageFileName) {
			boolean isNotExist = true;
			for (int i = 0; i < optionList.size(); i++) {
				// i番目のファイル名の取得
				ItemBean itemBean = optionList.get(i);
				String fileNameInList = itemBean.getImageFileName();
				
				// 配列の中のファイル名が同じかを判定
				if (imageFileName.equals(fileNameInList)) {
					isNotExist = false;
					break;
				}
			}
			return isNotExist;
		}
}
