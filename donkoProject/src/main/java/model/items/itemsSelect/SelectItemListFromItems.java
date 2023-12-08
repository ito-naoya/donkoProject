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
		final String SELECT_ITEMLIST_SQL = "SELECT * FROM human_resources_infos";
		ArrayList<ItemBean> itemlist = new ArrayList<>();
		ArrayList<Object> paramLists = new ArrayList<Object>() {{ }};
		try (Connection conn = DatabaseConnection.getConnection()) {
			ResultSet result = GeneralDao.executeQuery(conn, SELECT_ITEMLIST_SQL, paramLists);
			
			while (result.next()) {
				// TODO 以下変更する
                
                ItemBean IBeans = new ItemBean();
                itemlist.add(IBeans);
                
            }
		} catch (SQLException | ClassNotFoundException ex) {
			ex.printStackTrace();
		}
		return itemlist;
	};

}
