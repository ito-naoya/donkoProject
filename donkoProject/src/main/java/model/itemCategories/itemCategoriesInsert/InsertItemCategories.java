package model.itemCategories.itemCategoriesInsert;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import bean.ItemCategoryBean;
import dao.DatabaseConnection;
import dao.GeneralDao;

public class InsertItemCategories {

    public static Integer insertItemCategories(ItemCategoryBean category, String[] selectedOptions) {

    	// 同じ名前のカテゴリが既に存在するかを確認するSQL
        StringBuilder sb1 = new StringBuilder();
        sb1.append("SELECT COUNT(*) ");
        sb1.append("FROM item_categories ");
        sb1.append("WHERE item_category_name = ?;");
        final String CHECK_EXISTING_CATEGORY_SQL = sb1.toString();

        // 新しいアイテムカテゴリの挿入SQL
        StringBuilder sb2 = new StringBuilder();
        sb2.append("INSERT INTO item_categories ");
        sb2.append("(item_category_name, option_category_name) ");
        sb2.append("VALUES (?, ?);");
        final String INSERT_NEW_CATEGORY_SQL = sb2.toString();

        try (Connection conn = DatabaseConnection.getConnection()) {
        	try {
		            // 同じ名前のカテゴリが既に存在するか確認
		            ArrayList<Object> params1 = new ArrayList<>();
		            params1.add(category.getItemCategoryName());
		            ResultSet rs = GeneralDao.executeQuery(conn, CHECK_EXISTING_CATEGORY_SQL, params1);
		            if (rs.next() && rs.getInt(1) > 0) {
		                // 既に同じ名前のカテゴリが存在する
		                return 0;
		            }
		
		            for (String optionName : selectedOptions) {
		                if (optionName != null && !optionName.isEmpty()) {
		                    // 新しいカテゴリとオプションの組み合わせを挿入
		                    ArrayList<Object> params2 = new ArrayList<>();
		                    params2.add(category.getItemCategoryName());
		                    params2.add(optionName);
		                    GeneralDao.executeUpdate(conn, INSERT_NEW_CATEGORY_SQL, params2);
		                }
		            }
		
		            conn.commit(); // コミット           
		        } catch (SQLException e) {
		                conn.rollback(); // ロールバック
		                e.printStackTrace();
		                return null;
		        }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
        return 1; // 問題なく挿入完了
    }
}
