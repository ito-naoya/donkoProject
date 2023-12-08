package model.items.itemsSelect;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import bean.ItemBean;
import dao.DatabaseConnection;
import dao.GeneralDao;

public class SelectItemListWithoutDuplicate {
	
	//登録されている商品名を重複なしで取得する
	public static ArrayList<ItemBean> selectItemListWithoutDuplicate(){
		
		StringBuilder sb = new StringBuilder();

		sb.append("SELECT DISTINCT "		 	);
		sb.append(	"file_name "					);
		sb.append("FROM "	  					);
		sb.append(	"items;"					);
		
		String sql = sb.toString();
		
		
		List<Object> params = Arrays.asList();
		try (Connection conn = DatabaseConnection.getConnection()){

		    try(ResultSet rs = GeneralDao.executeQuery(conn, sql, params)) {

		   		ArrayList<ItemBean> items = new ArrayList<>();
		   		//OptionCategoryBeanに挿入
		   		while(rs.next()) {
		   			ItemBean item = new ItemBean();
		        	String file = rs.getString("file_name");
		        	item.setImageFileName(file);
		        	
		        	items.add(item);
		        }
			       //カテゴリーで紐づいたオプションのリストを返す
			        return items;

		    } catch (SQLException e) {
		    	if(!conn.isClosed()) {
			        conn.rollback();
			        e.printStackTrace();
		    	}
		    }

		} catch (SQLException | ClassNotFoundException e) {
		  e.printStackTrace();
		}
		return null;
	};

}
