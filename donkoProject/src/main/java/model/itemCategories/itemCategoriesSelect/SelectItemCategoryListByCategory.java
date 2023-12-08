package model.itemCategories.itemCategoriesSelect;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import bean.ItemBean;
import bean.ItemCategoryBean;
import dao.DatabaseConnection;
import dao.GeneralDao;

public class SelectItemCategoryListByCategory {

	//カテゴリ指定してオプションを取得する
	public static ArrayList<ItemCategoryBean> selectItemCategoryListByCategory(ItemBean itemBean) {
		
		StringBuilder sb = new StringBuilder();

		sb.append("SELECT "		 				);
		sb.append(	"item_category_name,"		);
		sb.append(	"option_category_name "		);
		sb.append("FROM "	  					);
		sb.append(	"item_categories "		  	);
		sb.append("WHERE "	  					);
		sb.append(	"item_category_name = ?;"  	);
		
		String sql = sb.toString();
		
		//引数のItemBeanからカテゴリを取得
		String categoryName = itemBean.getItemCategoryName();
		
		List<Object> params = Arrays.asList(categoryName);
		try (Connection conn = DatabaseConnection.getConnection()){

		    try(ResultSet rs = GeneralDao.executeQuery(conn, sql, params)) {

		   		ArrayList<ItemCategoryBean> itemCategories = new ArrayList<>();
		   		//OptionCategoryBeanに挿入
		   		while(rs.next()) {
		   			ItemCategoryBean itemCategory = new ItemCategoryBean();
		        	String category = rs.getString("item_category_name");
		        	itemCategory.setItemCategoryName(category);
		        	String option = rs.getString("option_category_name");
		        	itemCategory.setOptionCategoryName(option);
		        	
		        	itemCategories.add(itemCategory);
		        }
			       //カテゴリーで紐づいたオプションのリストを返す
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
		
		return null;
	};

}
