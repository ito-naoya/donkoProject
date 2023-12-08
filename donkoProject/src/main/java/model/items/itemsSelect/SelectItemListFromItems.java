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
		final String SELECT_ITEMLIST_SQL = "SELECT * FROM items";
		ArrayList<ItemBean> Itemlist = new ArrayList<>();
		ArrayList<Object> paramLists = new ArrayList<Object>() {{ }};
		try (Connection conn = DatabaseConnection.getConnection()) {
			ResultSet result = GeneralDao.executeQuery(conn, SELECT_ITEMLIST_SQL, paramLists);
			
			while (result.next()) {
				ItemBean IBeans = new ItemBean();
				int itemId = result.getInt("item_id");
                String itemName = result.getString("item_name");
                String imageFileName = result.getString("file_name");
                
                IBeans.setItemId(itemId);
                IBeans.setItemName(itemName);
                IBeans.setImageFileName(imageFileName);
                
                Itemlist.add(IBeans);
            }
		} catch (SQLException | ClassNotFoundException ex) {
			ex.printStackTrace();
		}
		return Itemlist;
	};

}
