package model.items.itemsSelect;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import bean.ItemBean;
import dao.DatabaseConnection;
import dao.GeneralDao;

public class SelectRandomItemListFromItems {
	
	//商品の一覧を取得する
	public static ArrayList<ItemBean> selectItemListFromItems(){
		final String SELECT_ALL_ITEMLIST_SQL = "SELECT item_id, file_name FROM items WHERE items.item_delete_flg = 0 ORDER BY RAND()";
		ArrayList<ItemBean> itemlist = new ArrayList<>();
		ArrayList<Object> paramList = new ArrayList<>() {{ }};
		try (Connection conn = DatabaseConnection.getConnection()) {
			try (ResultSet result = GeneralDao.executeQuery(conn, SELECT_ALL_ITEMLIST_SQL, paramList)) {
				
				// 1回目
				if(result.next()){ 
					ItemBean itemBean = new ItemBean();
					int itemId = result.getInt("item_id");
					String imageFileName = result.getString("file_name");
					itemBean.setItemId(itemId);
					itemBean.setImageFileName(imageFileName);
					itemlist.add(itemBean);
				}
				
				// 2回目以降
				while (result.next()) {
					ItemBean itemBean = new ItemBean();
				    int itemId = result.getInt("item_id");
				    String imageFileName = result.getString("file_name");
				    
				    // 画像の重複を判定
				    boolean isNotExist = isNotExist(itemlist, imageFileName);
				    // 重複しないデータを配列に格納
					if (isNotExist == true) {
						itemBean.setItemId(itemId);;
						itemBean.setImageFileName(imageFileName);
						itemlist.add(itemBean);
					}
				    
					// 配列の中身が8個になったらwhile文を終了
				    if (itemlist.size() == 8) {
				    	break;
				    }
				}
			// SQL実行時のExceptionをcatch
			} catch (SQLException e) {
				if (conn != null && !conn.isClosed()&& !conn.getAutoCommit()) {
					conn.rollback();
					e.printStackTrace();
				}
				return null;
			}
		// getConnection時のExceptionをcatch
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			return null;
		}
		return itemlist;
	};
	
	// 取得したデータを格納したList内に同じ画像名が存在するかを判定
	private static boolean isNotExist(ArrayList<ItemBean> itemList, String imageFileName) {
		boolean isNotExist = false;
		for (int i = 0; i < itemList.size(); i++) {
			// i番目のファイル名の取得
			ItemBean itemBean = itemList.get(i);
			String fileNameInList = itemBean.getImageFileName();
			
			// 配列の中のファイル名が同じかを判定
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
