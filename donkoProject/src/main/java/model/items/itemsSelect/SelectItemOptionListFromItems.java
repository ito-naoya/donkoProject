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
		
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT "																						);
		sql.append(		"items.item_id, "																			);
		sql.append(		"option_categories.option_category_value "													);
		sql.append("FROM "																							);
		sql.append(		"items "																					);
		sql.append("INNER JOIN "																					);
		sql.append(		"`options` "																				);
		sql.append("ON "																							);
		sql.append(		"items.item_id = `options`.item_id "														);
		sql.append("INNER JOIN "																					);
		sql.append(		"option_categories "																		);
		sql.append("ON "																							);
		sql.append(		"options.option_category_name = option_categories.option_category_name "					);
		sql.append("AND "																							);
		sql.append(		"options.option_category_increment_id  = option_categories.option_category_increment_id "	);
		sql.append("WHERE "																							);
		sql.append(		"items.file_name = (SELECT "																);
		sql.append(								"file_name "														);
		sql.append(							"FROM "																	);
		sql.append(								"items "															);
		sql.append(							"WHERE "																);
		//渡された商品IDをここで使う
		sql.append(								"item_id = ?) "														);
		sql.append("AND "																							);
		sql.append(		"options.option_category_name != '色' "														);
		sql.append("AND "																							);
		sql.append(		"items.item_delete_flg  != 1 "																);
		sql.append("ORDER BY "																						);
		sql.append(		"option_categories.option_category_value ASC"												);
		final String SELECT_ITEMOPTION_SQL = sql.toString();
		
		ArrayList<Object> params = new ArrayList<Object>();
		params.add(itemBean.getItemId());
		
		ArrayList<ItemBean> itemBeanList = new ArrayList<ItemBean>();
		
		try(Connection conn = DatabaseConnection.getConnection();){
			try(ResultSet rs = GeneralDao.executeQuery(conn, SELECT_ITEMOPTION_SQL, params)){
				
				while(rs.next()) {
					ItemBean ib = new ItemBean();
					ib.setItemId(rs.getInt("item_id"));
					ib.setItemFirstOptionValue(rs.getString("option_category_value"));
					itemBeanList.add(ib);
				}
				
			}catch(SQLException e) {
				if(!conn.isClosed()) {
					conn.rollback();
					e.printStackTrace();
					return null;
				}
			}
		}catch(SQLException | ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}
		return itemBeanList;
	}
}
