package model.items.itemsSelect;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import bean.ItemBean;
import dao.DatabaseConnection;
import dao.GeneralDao;

public class SelectItemAndOptionListByDelFlg {
	//条件を元に商品を並び替えるSQL
    public static ArrayList<ItemBean> selectItemAndOptionListByDelFlg(int itemDeleteFlg, String itemCategoryName, String sortOrder, String keyword) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT "																						);
        sb.append(    "items.item_id, "																			);
        sb.append(    "items.item_name, "																		);
        sb.append(    "items.item_category_name, "																);
        sb.append(    "items.price, "																			);
        sb.append(    "items.stock, "																			);
        sb.append(    "items.file_name, "																		);
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

        ArrayList<Object> params = new ArrayList<>();

        if (itemDeleteFlg != 2) {
            sb.append("WHERE item_delete_flg = ? ");
            params.add(itemDeleteFlg);
        }
        if (!itemCategoryName.isEmpty()) {
            sb.append(itemDeleteFlg != 2 ? "AND " : "WHERE ");
            sb.append("item_category_name = ? "				);
            params.add(itemCategoryName);
        }
        if (keyword != null && !keyword.trim().isEmpty()) {
            sb.append("AND items.item_name LIKE ? "			);
            params.add("%" + keyword + "%"					);
        }
        sb.append("ORDER BY ");
        if (sortOrder != null) {
            sb.append("items.item_id " + sortOrder + ", "	);
        } else {
            sb.append("items.item_id, "						);
        }
        sb.append("CASE "												);
        sb.append(    "WHEN options.option_category_name = '色' THEN 0 "	);
        sb.append(    "ELSE 1 "											);
        sb.append(  "END;"												);

        final String SELECT_ITEM_AND_OPTION_LIST_DELFLG_SQL = sb.toString();

        ArrayList<ItemBean> itemBeanList = new ArrayList<>();

        try (Connection conn = DatabaseConnection.getConnection();) {
            try (ResultSet rs = GeneralDao.executeQuery(conn, SELECT_ITEM_AND_OPTION_LIST_DELFLG_SQL, params)) {
                ItemBean currentIb = null;
                int lastItemId = -1;

                while (rs.next()) {
                    int currentItemId = rs.getInt("item_id");

                    if (currentIb == null || currentItemId != lastItemId) {
                        currentIb = new ItemBean();
                        currentIb.setItemId(currentItemId);
                        currentIb.setItemCategoryName(rs.getString("item_category_name"));
                        currentIb.setItemName(rs.getString("item_name"));
                        currentIb.setItemPrice(rs.getInt("price"));
                        currentIb.setItemStock(rs.getInt("stock"));
                        currentIb.setImageFileName(rs.getString("file_name"));
                        currentIb.setDeleted(rs.getBoolean("item_delete_flg"));
                        currentIb.setItemFirstOptionValue(rs.getString("option_category_value"));
                        itemBeanList.add(currentIb);
                        lastItemId = currentItemId;
                    } else {
                        currentIb.setItemSecondOptionValue(rs.getString("option_category_value"));
                    }
                }
            } catch (SQLException e) {
                if (!conn.isClosed()) {
                    conn.rollback();
                    e.printStackTrace();
                    return null;
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
        return itemBeanList;
    }
}
