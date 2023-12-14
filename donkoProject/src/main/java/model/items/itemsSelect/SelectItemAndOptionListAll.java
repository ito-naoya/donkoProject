package model.items.itemsSelect;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import bean.ItemBean;
import dao.DatabaseConnection;
import dao.GeneralDao;
//商品の一覧と商品が保有するオプションを取得する
public class SelectItemAndOptionListAll {

	public static ArrayList<ItemBean>  selectItemAndOptionListAll(String itemCategoryName) {

		//削除済みも含む全てのアイテムを抽出
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT "																						);
		sb.append(    "items.item_id, "																			);
		sb.append(    "items.item_name, "																		);
		sb.append(    "items.item_category_name, "																);
		sb.append(    "items.price, "																			);
		sb.append(    "items.stock, "																			);
		sb.append(    "items.item_delete_flg, "																	);
		sb.append(    "option_categories.option_category_value "												);
		sb.append("FROM "																						);
		sb.append(    "items "																					);
		sb.append("INNER JOIN "																					);
		sb.append(    "options "																				);
		sb.append("ON "																							);
		sb.append(    "items.item_id = options.item_id "														);
		sb.append("INNER JOIN "																					);
		sb.append(    "option_categories "																		);
		sb.append("ON "																							);
		sb.append(    "options.option_category_name = option_categories.option_category_name "					);
		sb.append("AND "																						);
		sb.append(    "options.option_category_increment_id = option_categories.option_category_increment_id "	);
		if(!itemCategoryName.isEmpty()) {
			sb.append("WHERE "																					);
			sb.append(		"item_category_name  = ? "															);
		}
		sb.append("ORDER BY "																					);
		sb.append(    "items.item_id;"																			);

		final String SELECT_ITEM_AND_OPTION_ALL_SQL = sb.toString();

		ArrayList<Object> params = new ArrayList<Object>();
		if(!itemCategoryName.isEmpty()) {
			params.add(itemCategoryName);
		}
		ArrayList<ItemBean> itemBeanList = new ArrayList<ItemBean>();

		try(Connection conn = DatabaseConnection.getConnection();){
			try(ResultSet rs = GeneralDao.executeQuery(conn, SELECT_ITEM_AND_OPTION_ALL_SQL, params)){

				ItemBean currentIb = null;
	            int lastItemId = -1; // item_idがint型であるため、初期値として無効な値を設定

	            while(rs.next()) {
	                int currentItemId = rs.getInt("item_id");

	                // 新しいitem_idが前のitem_idと異なる場合、新しいItemBeanを作成
	                if(currentIb == null || currentItemId != lastItemId) {
	                    currentIb = new ItemBean();

	                    currentIb.setItemId(currentItemId);
	                    currentIb.setItemCategoryName(rs.getString("item_category_name"));
	                    currentIb.setItemName(rs.getString("item_name"));
	                    currentIb.setItemPrice(rs.getInt("price"));
	                    currentIb.setItemStock(rs.getInt("stock"));
	                    currentIb.setDeleted(rs.getBoolean("item_delete_flg"));
	                    currentIb.setItemFirstOptionValue(rs.getString("option_category_value"));

	                    itemBeanList.add(currentIb);
	                    lastItemId = currentItemId; // 最後に処理したitem_idを更新
	                } else {
	                    // 同じitem_idの場合、2つ目のオプションを設定
	                    currentIb.setItemSecondOptionName(rs.getString("option_category_value"));
	                }
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
