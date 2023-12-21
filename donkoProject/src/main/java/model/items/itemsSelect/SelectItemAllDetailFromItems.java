package model.items.itemsSelect;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import bean.ItemBean;
import dao.DatabaseConnection;
import dao.GeneralDao;
//商品がもつ全ての情報を取得する
public class SelectItemAllDetailFromItems {

	public static ItemBean selectItemAllDetailFromItems(ItemBean itemBean) {
		//商品がもつ全ての情報を取得するSQL(id=1なら、itemsテーブル上の情報と、optionに紐づいた情報(色；緑色　衣類サイズ；Sサイズ)を全て獲得)
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT "																						);
		sb.append(		"* "																					);
		sb.append("FROM "																						);
		sb.append(		"items "																				);
		sb.append("INNER JOIN "																					);
		sb.append(		"options "																			);
		sb.append("ON "																							);
		sb.append(		"items.item_id = options.item_id "													);
		sb.append("INNER JOIN "																					);
		sb.append(		"option_categories "																	);
		sb.append("ON "																							);
		sb.append(		"options.option_category_name = option_categories.option_category_name "				);
		sb.append("AND "																						);
		sb.append(		"options.option_category_increment_id = option_categories.option_category_increment_id ");
		sb.append("WHERE "																						);
		sb.append(		"items.item_id = ?;"																	);

		final String SELECT_ITEM_ALLDETAIL = sb.toString();

		ArrayList<Object> params = new ArrayList<Object>();
		params.add(itemBean.getItemId());
		ItemBean ib = new ItemBean();
		try(Connection conn = DatabaseConnection.getConnection();){
			try(ResultSet rs = GeneralDao.executeQuery(conn, SELECT_ITEM_ALLDETAIL, params)){

				if(rs.next()){
					ib.setItemId(rs.getInt("item_id"));
		            ib.setItemCategoryName(rs.getString("item_category_name"));
		            ib.setItemName(rs.getString("item_name"));
		            ib.setItemDescription(rs.getString("item_description"));
		            ib.setItemPrice(rs.getInt("price"));
		            ib.setItemStock(rs.getInt("stock"));
		            ib.setDeleted(rs.getBoolean("item_delete_flg"));
		            ib.setImageFileName(rs.getString("file_name"));
		            ib.setItemFirstOptionName(rs.getString("option_category_name"));
		            ib.setItemFirstOptionValue(rs.getString("option_category_value"));
		            ib.setItemFirstOptionIncrementId(rs.getInt("option_category_increment_id"));
				}
				//2個目のオプション（２列目）があれば、そちらも登録
				while(rs.next()) {
					ib.setItemSecondOptionName(rs.getString("option_category_name"));
		            ib.setItemSecondOptionValue(rs.getString("option_category_value"));
		            ib.setItemSecondOptionIncrementId(rs.getInt("option_category_increment_id"));
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

		//商品詳細オプションを保持したitemBeanを返す
		return ib;
	}
}
