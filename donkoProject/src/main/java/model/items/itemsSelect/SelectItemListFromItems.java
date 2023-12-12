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
		final String SELECT_ALL_ITEMLIST_SQL = "SELECT item_id, file_name FROM items WHERE items.item_delete_flg = 0 ORDER BY RAND()";
		ArrayList<ItemBean> Itemlist = new ArrayList<>();
		ArrayList<Object> paramLists = new ArrayList<>() {{ }};
		try (Connection conn = DatabaseConnection.getConnection()) {
			try (ResultSet result = GeneralDao.executeQuery(conn, SELECT_ALL_ITEMLIST_SQL, paramLists)) {
				
				if(result.next()){ 
					ItemBean IBeans = new ItemBean();
					int itemId = result.getInt("item_id");
					String imageFileName = result.getString("file_name");
					IBeans.setItemId(itemId);
					IBeans.setImageFileName(imageFileName);
					Itemlist.add(IBeans);
				}
				
				while (result.next()) {
				    int itemId = result.getInt("item_id");
				    String imageFileName = result.getString("file_name");
				    
				    boolean check = isChecked(Itemlist, imageFileName);
				    
				    if(check == true) {
				    	ItemBean IBeans = new ItemBean();
						IBeans.setItemId(itemId);
						IBeans.setImageFileName(imageFileName);
						Itemlist.add(IBeans);
				    }
				    
				    if (Itemlist.size() == 8) {
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
		}
		return Itemlist;
	};
	
	protected static boolean isChecked(ArrayList<ItemBean> Itemlist, String imageFileName) {
		boolean isChecked = false;
		    for (int i = 0; i < Itemlist.size(); i++) {
             String imn = Itemlist.get(i).getImageFileName();
             if (imn.equals(imageFileName)) {
            	 isChecked = false;
                 break;
             } else {
            	isChecked = true;
             	i++;
             }
         }
		return isChecked;
	}
}
