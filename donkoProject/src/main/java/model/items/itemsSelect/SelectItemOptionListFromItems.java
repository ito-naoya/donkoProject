package model.items.itemsSelect;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import bean.ItemBean;
import dao.DatabaseConnection;
import dao.GeneralDao;

public class SelectItemOptionListFromItems {
	
	public static ArrayList<ItemBean> selectItemOptionListFromItems(ItemBean itemBean){
		
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT "																						);
		sb.append(		"items.item_id, "																			);
		sb.append(		"option_categories.option_category_value "													);
		sb.append("FROM "																							);
		sb.append(		"items "																					);
		sb.append("INNER JOIN "																					);
		sb.append(		"`options` "																				);
		sb.append("ON "																							);
		sb.append(		"items.item_id = `options`.item_id "														);
		sb.append("INNER JOIN "																					);
		sb.append(		"option_categories "																		);
		sb.append("ON "																							);
		sb.append(		"options.option_category_name = option_categories.option_category_name "					);
		sb.append("AND "																							);
		sb.append(		"options.option_category_increment_id  = option_categories.option_category_increment_id "	);
		sb.append("WHERE "																							);
		sb.append(		"items.file_name = (SELECT "																);
		sb.append(								"file_name "														);
		sb.append(							"FROM "																	);
		sb.append(								"items "															);
		sb.append(							"WHERE "																);
		//渡された商品IDをここで使う
		sb.append(								"item_id = ?) "														);
		sb.append("AND "																							);
		sb.append(		"options.option_category_name != '色' "														);
		sb.append("AND "																							);
		sb.append(		"items.item_delete_flg  != 1 "																);
		sb.append("ORDER BY "																						);
		sb.append(		"option_categories.option_category_value ASC"												);
		final String SELECT_ITEM_OPTION_SQL = sb.toString();
		
		ArrayList<Object> params = new ArrayList<Object>();
		params.add(itemBean.getItemId());
		
		ArrayList<ItemBean> itemBeanList = new ArrayList<ItemBean>();
		
		try(Connection conn = DatabaseConnection.getConnection();){
			try(ResultSet rs = GeneralDao.executeQuery(conn, SELECT_ITEM_OPTION_SQL, params)){
				
				while(rs.next()) {
					ItemBean ib = new ItemBean();
					ib.setItemId(rs.getInt("item_id"));
					ib.setItemFirstOptionValue(rs.getString("option_category_value"));
					itemBeanList.add(ib);
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
		return itemBeanList;
	}
}
