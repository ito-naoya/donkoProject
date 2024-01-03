package model.itemCategories.itemCategoriesDelete;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import bean.ItemCategoryBean;
import dao.DatabaseConnection;
import dao.GeneralDao;

public class DeleteItemCategories {

	public static Integer deleteItemCategories(ItemCategoryBean category) {

        // 特定のアイテムカテゴリを削除するSQL
        StringBuilder sb = new StringBuilder();
        sb.append("DELETE FROM ");
        sb.append(      "item_categories ");
        sb.append("WHERE ");
        sb.append(      "item_category_name = ?;");
        final String DELETE_CATEGORY_SQL = sb.toString();

        ArrayList<Object> params = new ArrayList<>();
        params.add(category.getItemCategoryName());
        int totalDeletedRows;
        try (Connection conn = DatabaseConnection.getConnection()) {
            try {
                totalDeletedRows = GeneralDao.executeUpdate(conn, DELETE_CATEGORY_SQL, params);
                conn.commit(); // コミット

            } catch (SQLException e) {
                conn.rollback();
                if (e.getSQLState().startsWith("23")) { // SQLステート23は外部キー制約違反の可能性がある
                    return 0;
                }
                e.printStackTrace();
                return null;
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
        return totalDeletedRows;
	}
}
