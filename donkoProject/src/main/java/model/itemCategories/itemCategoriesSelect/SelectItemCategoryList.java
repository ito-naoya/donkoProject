package model.itemCategories.itemCategoriesSelect;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import bean.ItemCategoryBean;
import dao.DatabaseConnection;
import dao.GeneralDao;

public class SelectItemCategoryList {
	
	//全ての商品カテゴリを取得する
	public static ArrayList<ItemCategoryBean> selectItemCategoryList(){
		
		//カテゴリーテーブルでitem_category_nameの重複を許さないSELECT文を回して、全ての商品カテゴリを取得
		StringBuilder sb = new StringBuilder();

		sb.append("SELECT DISTINCT "		 	);
		sb.append(	"item_category_name "		);
		sb.append("FROM "	  					);
		sb.append(	"item_categories;"		  	);
		
		String sql = sb.toString();
		
		List<Object> params = Arrays.asList();
		

		try (Connection conn = DatabaseConnection.getConnection()){

		    try(ResultSet rs = GeneralDao.executeQuery(conn, sql, params)) {

		   		ArrayList<ItemCategoryBean> itemCategories = new ArrayList<>();
		        //取得された順番にItemCategoryBeanに格納
		   		while (rs.next()) {
		        	ItemCategoryBean itemCategory = new ItemCategoryBean();
		        	String item = rs.getString("item_category_name");
		        	itemCategory.setItemCategoryName(item);
		        	itemCategories.add(itemCategory);
		        }
			       //カテゴリーリストを返す
			        return itemCategories;
			        
		    } catch (SQLException e) {
		    	if(!conn.isClosed()) {
			        conn.rollback();
			        e.printStackTrace();
		    	}
		    }
		    
		} catch (SQLException | ClassNotFoundException e) {
		  e.printStackTrace();
		}
		return null;};
}
