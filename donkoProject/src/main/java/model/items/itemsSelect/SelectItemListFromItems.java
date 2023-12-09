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
		final String SELECT_ITEMLIST_SQL = "SELECT file_name FROM items GROUP BY file_name ORDER BY RAND() LIMIT 8;";
		ArrayList<ItemBean> Itemlist = new ArrayList<>();
		ArrayList<Object> paramLists = new ArrayList<>() {{ }};
		try (Connection conn = DatabaseConnection.getConnection()) {
			try (ResultSet result = GeneralDao.executeQuery(conn, SELECT_ITEMLIST_SQL, paramLists)) {
				while (result.next()) {
					ItemBean IBeans = new ItemBean();
					
					String imageFileName = result.getString("file_name");
					
					IBeans.setImageFileName(imageFileName);

					Itemlist.add(IBeans);
				}
				// SQL実行時のExceptionをcatch
			} catch (SQLException e) {
				if (conn != null && !conn.isClosed()&& !conn.getAutoCommit()) {
					conn.rollback();
					e.printStackTrace();
				}
			}
			// getConnection時のExceptionをcatch
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return Itemlist;
	};

}
