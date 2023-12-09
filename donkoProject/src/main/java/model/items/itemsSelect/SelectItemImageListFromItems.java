package model.items.itemsSelect;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import bean.ItemBean;
import dao.DatabaseConnection;
import dao.GeneralDao;

public class SelectItemImageListFromItems {

	//商品の画像一覧を取得する
	public static ArrayList<ItemBean> selectItemImageListFromItems(ItemBean itemBean) {
		
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT "			 																									  );
		sql.append(		"items.item_id, "																									  );
		sql.append(		"items.file_name "																									  );
		sql.append("FROM "																													  );
		sql.append(		"items "																											  );
		sql.append("INNER JOIN	"																											  );
		sql.append(		"item_categories "																									  );
		sql.append("ON "																													  );
		sql.append(		"items.item_category_name = item_categories.item_category_name "													  );
		sql.append("INNER JOIN "																											  );
		sql.append(		"`options` "																										  );
		sql.append("ON "																													  );
		sql.append(		"items.item_id = options.item_id "																					  );
		sql.append("AND "																													  );
		sql.append(		"item_categories.option_category_name = options.option_category_name "												  );
		sql.append("INNER JOIN "																											  );
		sql.append(		"option_categories "																								  );
		sql.append("ON "																													  );
		sql.append(		"`options`.option_category_name = option_categories.option_category_name "											  );
		sql.append("AND	"																													  );
		sql.append(		"`options`.option_category_increment_id  = option_categories.option_category_increment_id "							  );
		sql.append("WHERE "																													  );
		sql.append(		"item_name = (SELECT "																								  );
		sql.append(					 	"item_name	"																						  );
		sql.append(					 "FROM "																								  );
		sql.append(						"items "																							  );
		sql.append(					 "WHERE "																								  );
		//渡された商品のIDをここで使用する(1/2回目)
		sql.append(						"item_id = ?) "																						  ); 
		sql.append("AND "																													  );
		sql.append(		"option_category_value = (SELECT "																					  );
		sql.append(									"option_categories.option_category_value "												  );
		sql.append(								 "FROM "																					  );
		sql.append(								  	"`options` "																			  );
		sql.append(								 "INNER JOIN "																				  );
		sql.append(									"option_categories "																	  );
		sql.append(								 "ON "																						  );
		sql.append(								 	"`options`.option_category_name = option_categories.option_category_name "				  );
		sql.append(								 "AND "																						  );
		sql.append(									"`options`.option_category_increment_id = option_categories.option_category_increment_id ");
		sql.append(								 "AND "																						  );
		sql.append(									"`options`.option_category_name != '色' "												  );
		sql.append(								 "WHERE "																					  );
		//渡された商品のIDをここで使用する(2/2回目)
		sql.append(									"`options`.item_id = ?)"																  );

		//SQL文を文字列に変換
		final String SELECT_ITEMIMAGELIST_SQL = sql.toString();
		
		ArrayList<Object> params = new ArrayList<Object>();
		params.add(itemBean.getItemId());
		params.add(itemBean.getItemId());

		ItemBean ib = null;
		ArrayList<ItemBean> itemBeanList = new ArrayList<ItemBean>();

		try (Connection conn = DatabaseConnection.getConnection();) {
			try (ResultSet rs = GeneralDao.executeQuery(conn, SELECT_ITEMIMAGELIST_SQL, params);) {
				
				while (rs.next()) {
					ib = new ItemBean();
					ib.setItemId(rs.getInt("item_id"));
					ib.setImageFileName(rs.getString("file_name"));
					itemBeanList.add(ib);
				}
				
			} catch (SQLException e) {
				if (!conn.isClosed()) {
					//SQL文が一つでも失敗したらロールバックする
					conn.rollback();
					e.printStackTrace();
				}
			}
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return itemBeanList;
	}
}

