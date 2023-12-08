package model.optionCategories.optionCategoriesSelect;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

<<<<<<< Updated upstream
=======
import bean.ItemBean;
>>>>>>> Stashed changes
import bean.ItemCategoryBean;
import bean.OptionCategoryBean;
import dao.DatabaseConnection;
import dao.GeneralDao;

public class SelectOptionCategoryListByCategory {
<<<<<<< Updated upstream

	//カテゴリ名でオプションを取得する
	public static ArrayList<OptionCategoryBean> selectOptionCategoryListByCategory(ItemCategoryBean itemCategoryBean) {
		return null;
	};
=======
	
	//カテゴリ指定してオプションを取得する
	public static ArrayList<OptionCategoryBean> selectOptionCategoryListByCategory(ItemBean itemBean){
		//item_categoryテーブルからカテゴリーをキーにしてSQL発行
		StringBuilder sb = new StringBuilder();

		sb.append("SELECT "		 				);
		sb.append(	"option_category_name "		);
		sb.append("FROM "	  					);
		sb.append(	"item_categories;"		  	);
		sb.append("WHERE "	  					);
		sb.append(	"item_category_name = ?;"  	);
		
		String sql = sb.toString();
		
		//ItemBeanからカテゴリを取得
		String categoryName = itemBean.getItemCategoryName();
		
		List<Object> params = Arrays.asList();
		try (Connection conn = DatabaseConnection.getConnection()){

		    try(ResultSet rs = GeneralDao.executeQuery(conn, sql, params)) {

		   		ArrayList<ItemCategoryBean> itemCategories = new ArrayList<>();
		   		//OptionCategoryBeanに挿入
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
		return null;};
>>>>>>> Stashed changes

}
