package model.items.itemsSelect;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import bean.ItemBean;
import dao.DatabaseConnection;
import dao.GeneralDao;

public class SelectItemListFromItemsByCategory {
	// カテゴリー名を指定して一覧を取得
	public static ArrayList<ItemBean> selectItemListFromItemsByCategory(ItemBean itemBean){
		
		// カテゴリーがnullだった場合はnullを返す
		if (itemBean.getItemCategoryName() == null) {
            return null;
        }
		
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT "                           );
		sb.append(    "items.item_id, "               );
		sb.append(    "items.item_name, "             );
		sb.append(    "items.file_name "              );
		sb.append("FROM "                             );
		sb.append(    "items "                        );
		sb.append("WHERE "                            );
		sb.append(    "items.item_delete_flg = 0 "    );
		sb.append("AND "                              );
		sb.append(    "item_category_name = ? "       );
		
		// SQLを文字列化
		final String SELECT_ITEMLIST_SQL = sb.toString();
		
		ArrayList<ItemBean> itemlist = new ArrayList<>();
		ArrayList<Object> paramLists = new ArrayList<>() {{
			add(itemBean.getItemCategoryName());
		}};
		try (Connection conn = DatabaseConnection.getConnection()) {
			try (ResultSet result = GeneralDao.executeQuery(conn, SELECT_ITEMLIST_SQL, paramLists)) {
				
				while(result.next()){ 
					ItemBean ib = new ItemBean();
					int itemId = result.getInt("item_id");
				    String itemName = result.getString("item_name");
				    String imageFileName = result.getString("file_name");
					
					// 画像名が一致するかを判定
					boolean isNotExist = checkExist(itemlist, imageFileName);
					// 一致しない場合は配列に格納
					if (isNotExist) {
						ib.setItemId(itemId);
						ib.setItemName(itemName);
						ib.setImageFileName(imageFileName);
						itemlist.add(ib);
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
		return itemlist;
	};
	
	// 取得したデータを格納した配列内に同じ画像名が存在するかを判定
	private static boolean checkExist(ArrayList<ItemBean> itemList, String imageFileName) {
		boolean isNotExist = true;
		for (ItemBean item : itemList) {
			String fileNameInList = item.getImageFileName();
			
			if (imageFileName.equals(fileNameInList)) {
				isNotExist = false;
				break;
			}
		}
		return isNotExist;
	}
}
