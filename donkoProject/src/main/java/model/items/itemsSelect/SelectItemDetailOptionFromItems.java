package model.items.itemsSelect;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import bean.ItemBean;
import dao.DatabaseConnection;
import dao.GeneralDao;

public class SelectItemDetailOptionFromItems {
	
	public static ItemBean selectItemDetailOptionFromItems(ItemBean itemBean) {
		
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT ");
		sql.append(		"group_concat(option_categories.option_category_value separator ',') ");
		sql.append("FROM ");
		sql.append(		"items ");
		sql.append("INNER JOIN ");
		sql.append(		"`options` ");
		sql.append("ON ");
		sql.append(		"items.item_id = `options`.item_id ");
		sql.append("INNER JOIN ");
		sql.append(		"option_categories ");
		sql.append("ON ");
		sql.append(		"options.option_category_name  = option_categories.option_category_name ");
		sql.append("AND ");
		sql.append(		"options.option_category_increment_id  = option_categories.option_category_increment_id ");
		sql.append("WHERE ");
		sql.append(		"items.item_id = ?");
		final String SELECT_ITEMDETAILOPTION_SQL = sql.toString();
		
		
		ArrayList<Object> params = new ArrayList<Object>();
		params.add(itemBean.getItemId());
		
		ItemBean ib = null;
		
		try(Connection conn = DatabaseConnection.getConnection();){
			try(ResultSet rs = GeneralDao.executeQuery(conn, SELECT_ITEMDETAILOPTION_SQL, params)){
				
				while(rs.next()) {
					ib = new ItemBean();
					ib.setItemFirstOptionValue(rs.getString("group_concat(option_categories.option_category_value separator ',')"));
				}

			}catch(SQLException e) {
				if(!conn.isClosed()) {
					conn.rollback();
				}
				e.printStackTrace();
				return null;
			}
		}catch(SQLException | ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}
		return ib;
	}
}
