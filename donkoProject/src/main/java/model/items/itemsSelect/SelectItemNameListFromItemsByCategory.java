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

public class SelectItemNameListFromItemsByCategory {

	//商品名をカテゴリ指定して取得する
	public static ArrayList<ItemBean> selectItemNameListFromItemsByCategory(ItemBean itemBean){
		//商品名をカテゴリ指定して取得するSQL（?=衣服：トップス、シャツ、アウター）
		StringBuilder sb = new StringBuilder();

		sb.append("SELECT DISTINCT "		 	);
		sb.append(	"item_name "				);
		sb.append("FROM "	 					);
		sb.append(	"items "					);
		sb.append("WHERE "	  					);
		sb.append(	"item_category_name = ?;"	);

		String sql = sb.toString();

		ArrayList<ItemBean> items = new ArrayList<>();
		String parm = itemBean.getItemCategoryName();
		List<Object> params = Arrays.asList(parm);
		try (Connection conn = DatabaseConnection.getConnection()){
		    try(ResultSet rs = GeneralDao.executeQuery(conn, sql, params)) {

		   		//ItemBeanに挿入
		   		while(rs.next()) {
		   			ItemBean item = new ItemBean();
		        	String file = rs.getString("item_name");
		        	item.setItemName(file);

		        	items.add(item);
		        }

		    } catch (SQLException e) {
		    	if(!conn.isClosed()) {
			        conn.rollback();
			        e.printStackTrace();
			        return null;
		    	}
		    }

		} catch (SQLException | ClassNotFoundException e) {
		  e.printStackTrace();
		  return null;
		}
		//カテゴリーで紐づいたオプションのリストを返す
        return items;
	}
}