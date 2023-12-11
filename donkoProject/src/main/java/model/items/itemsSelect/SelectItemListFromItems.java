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
				ItemBean IBeans = new ItemBean();
				if(result.next()){ 
					int itemId = result.getInt("item_id");
					String imageFileName = result.getString("file_name");
					IBeans.setItemId(itemId);
					IBeans.setImageFileName(imageFileName);
					Itemlist.add(IBeans);
				}
				String previousFN = IBeans.getImageFileName();
				while (result.next()) {
				    int itemId = result.getInt("item_id");
				    String imageFileName = result.getString("file_name");

				    if (!imageFileName.equals(previousFN)) {
				        ItemBean IB = new ItemBean();
				        IB.setItemId(itemId);
				        IB.setImageFileName(imageFileName);
				        Itemlist.add(IB);
				        
				        previousFN = imageFileName;
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

}
