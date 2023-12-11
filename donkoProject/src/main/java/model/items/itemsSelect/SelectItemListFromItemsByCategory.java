package model.items.itemsSelect;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.regex.Pattern;

import bean.ItemBean;
import dao.DatabaseConnection;
import dao.GeneralDao;

public class SelectItemListFromItemsByCategory {
	public static ArrayList<ItemBean> selectItemListFromItemsByCategory(ItemBean itemBean){
		final String SELECT_ITEMLIST_SQL = "SELECT item_id, item_name, file_name FROM items WHERE items.item_delete_flg = 0 AND item_category_name = ?";
		ArrayList<ItemBean> Itemlist = new ArrayList<>();
		ArrayList<Object> paramLists = new ArrayList<>() {{
			add(itemBean.getItemCategoryName());
		}};
		try (Connection conn = DatabaseConnection.getConnection()) {
			try (ResultSet result = GeneralDao.executeQuery(conn, SELECT_ITEMLIST_SQL, paramLists)) {
				// 1回目
				ItemBean IBeans = new ItemBean();
				if(result.next()){ 
					int itemId = result.getInt("item_id");
					String itemName = result.getString("item_name");
					String imageFileName = result.getString("file_name");
					IBeans.setItemId(itemId);
					IBeans.setItemName(itemName);
					IBeans.setImageFileName(imageFileName);
					Itemlist.add(IBeans);
				}
				// 2回目以降
				String previousFN = IBeans.getImageFileName(); // この時はまだ1つ目の画像名
				while (result.next()) {
				    int itemId = result.getInt("item_id");
				    String itemName = result.getString("item_name");
				    String imageFileName = result.getString("file_name");

				    if (!imageFileName.equals(previousFN)) {
				        ItemBean IB = new ItemBean();
				        IB.setItemId(itemId);
				        IB.setItemName(itemName);
				        IB.setImageFileName(imageFileName);
				        Itemlist.add(IB);
				        
				        // 38行目でgetした値を再代入して2回目以降の写真を格納
				        previousFN = imageFileName;
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
		return Itemlist;
	};
	
	protected boolean isChecked(String imageFileName, String iFN) {
		Pattern p = Pattern.compile(imageFileName);
        p.matcher(iFN);
		return false;
	}
}
