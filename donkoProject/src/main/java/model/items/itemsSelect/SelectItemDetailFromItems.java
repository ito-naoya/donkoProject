package model.items.itemsSelect;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import bean.ItemBean;
import dao.DatabaseConnection;
import dao.GeneralDao;

public class SelectItemDetailFromItems {

	//商品の詳細を取得する
	public static ItemBean selectItemDetailFromItems(ItemBean itemBean) {

		//商品の詳細情報を取得するSQL
		StringBuilder sb1 = new StringBuilder();
		sb1.append("SELECT 			  ");
		sb1.append("	*	 		  ");
		sb1.append("FROM 			  ");
		sb1.append("	items 		  ");
		sb1.append("WHERE			  ");
		sb1.append("	item_id = ?	  ");
		final String SELECT_ITEMDETAIL = sb1.toString();

		//商品の登録されている画像を全て取得
		StringBuilder sb2 = new StringBuilder();
		sb2.append("SELECT 		  	  ");
		sb2.append("DISTINCT	  	  ");
		sb2.append("	file_name 	  ");
		sb2.append("FROM 		  	  ");
		sb2.append("	items 	  	  ");
		sb2.append("WHERE 		  	  ");
		sb2.append("	item_name = ? ");
		sb2.append("AND			  	  ");
		sb2.append("	item_id != ?  ");
		final String SELECT_FILENAME = sb2.toString();

		StringBuilder selectDetailSql = new StringBuilder();
		selectDetailSql.append("SELECT 			  ");
		selectDetailSql.append("	*	 		  ");
		selectDetailSql.append("FROM 			  ");
		selectDetailSql.append("	items 		  ");
		selectDetailSql.append("WHERE			  ");
		selectDetailSql.append("	item_id = ?	  ");
		final String SELECT_ITEMDETAIL_SQL = selectDetailSql.toString();

		//商品の登録されている画像を全て取得
		StringBuilder selectFileNameSql = new StringBuilder();
		selectFileNameSql.append("SELECT 		  	   ");
		selectFileNameSql.append("DISTINCT	  	   ");
		selectFileNameSql.append("	file_name 	   ");
		selectFileNameSql.append("FROM 		  	   ");
		selectFileNameSql.append("	items 	  	   ");
		selectFileNameSql.append("WHERE 		  	   ");
		selectFileNameSql.append("	file_name != ? ");
		selectFileNameSql.append("AND			  	   ");
		selectFileNameSql.append("	item_name = ?  ");
		final String SELECT_FILENAME_SQL = selectFileNameSql.toString();
		
		StringBuilder selectSizeSql = new StringBuilder();
		selectSizeSql.append("SELECT ");
		selectSizeSql.append("");
		selectSizeSql.append();
		selectSizeSql.append();
		selectSizeSql.append();
		selectSizeSql.append();
		selectSizeSql.append();
		selectSizeSql.append();
		final String SELECT_SIZE_SQL selectSizeSql.toString();
		
		

		ArrayList<Object> params = new ArrayList<Object>();
		params.add(itemBean.getItemId());

		try (Connection conn = DatabaseConnection.getConnection();) {
			
			try (ResultSet selectDetailRs = GeneralDao.executeQuery(conn, SELECT_ITEMDETAIL, params);
				 ResultSet selectFileNameRs = GeneralDao.executeQuery(conn, SELECT_FILENAME, params);
					ResultSet selectSizeRs = GeneralDao.executeQuery(conn, SELECT_SIZE, params);) {

				conn.commit();
				
			}catch (SQLException e) {
				if (!conn.isClosed()) {
					//SQL文が一つでも失敗したらロールバックする
					conn.rollback();
					e.printStackTrace();
				}
			}

		} catch (SQLException |ClassNotFoundException e) {
			e.printStackTrace();
		}
}
}
