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
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT ");
		sb.append("  items.item_id, ");
		sb.append("  items.file_name ");
		sb.append("FROM ");
		sb.append("  items ");
		sb.append("WHERE ");
		sb.append("  items.item_delete_flg = 0 ");
		sb.append("ORDER BY ");
		sb.append("  RAND()");
		
		//SQLを文字列化
		final String SELECT_ALL_ITEMLIST_SQL = sb.toString();
		
		ArrayList<ItemBean> itemlist = new ArrayList<>();
		ArrayList<Object> paramList = new ArrayList<>() {{ }};
		try (Connection conn = DatabaseConnection.getConnection()) {
			try (ResultSet result = GeneralDao.executeQuery(conn, SELECT_ALL_ITEMLIST_SQL, paramList)) {
				
				while (result.next()) {
					ItemBean itemBean = new ItemBean();
				    int itemId = result.getInt("item_id");
				    String imageFileName = result.getString("file_name");
				    
				    // 画像の重複を判定
				    boolean isNotExist = checkExist(itemlist, imageFileName);
				    // 重複しないデータを配列に格納
					if (isNotExist) {
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
