package model.options.optionsDelete;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import bean.OptionCategoryBean;
import dao.DatabaseConnection;
import dao.GeneralDao;

public class DeleteItemOptionName {
	// 商品のオプションを削除する
    public static Integer deleteItemOptionName(OptionCategoryBean optionCategoryBean) {
        StringBuilder sb1 = new StringBuilder();
        sb1.append("DELETE FROM "					);
        sb1.append(		"option_category_names "	);
        sb1.append("WHERE "							);
        sb1.append(		"option_category_name = ?;"	);

        StringBuilder sb2 = new StringBuilder();
        sb2.append("DELETE FROM ");
        sb2.append(		"option_categories ");
        sb2.append("WHERE "							);
        sb2.append(		"option_category_name = ?;"	);


        final String DELETE_OLD_SQL = sb1.toString();
        final String DELETE_OPTION_CATEGORIES_SQL = sb2.toString();

        ArrayList<Object> param = new ArrayList<>();
        param.add(optionCategoryBean.getOptionCategoryName());

        int totalDeletedRows;
        try (Connection conn = DatabaseConnection.getConnection()) {
            try {
                totalDeletedRows = GeneralDao.executeUpdate(conn, DELETE_OLD_SQL, param);
                totalDeletedRows += GeneralDao.executeUpdate(conn, DELETE_OPTION_CATEGORIES_SQL, param);

                conn.commit(); // コミット
            } catch (SQLException e) {
                conn.rollback(); // ロールバック
                if (e.getSQLState().startsWith("23")) { // SQLステート23は外部キー制約違反のエラー
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
