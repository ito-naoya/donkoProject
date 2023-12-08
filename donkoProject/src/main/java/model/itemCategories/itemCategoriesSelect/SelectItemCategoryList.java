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
		String getItemCategorySQL = "SELECT DISTINCT item_category_name FROM item_categories;";
		List<Object> params = Arrays.asList();
		try (Connection conn = DatabaseConnection.getConnection();
		     ResultSet rs = GeneralDao.executeQuery(conn, getItemCategorySQL, params)) {
				//arrayListに格納する順番を　衣類,靴,携帯,本,食料品に変更
		   		List<ItemCategoryBean> itemCategory = new ArrayList<>();
			        while (rs.next()) {
			        	String file = rs.getString("file_name");
			        	itemCategory.add(file);
			        }
			        return itemCategory;
		   	} catch (ClassNotFoundException | SQLException e) {
					e.printStackTrace();
			}
		
		
		//close文を忘れない
		
		//取得した結果をItemCategoryBeanに格納
		
		
		return null;};

}
