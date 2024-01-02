package model.options.optionsDelete;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import bean.OptionCategoryBean;
import dao.DatabaseConnection;
import dao.GeneralDao;

public class DeleteItemOptionValue {

    // 商品のオプション詳細を削除する
    public static Integer deleteItemOptionValue(OptionCategoryBean optionCategoryBean) {

    	//incrementIdの最大値を取得
        StringBuilder sb = new StringBuilder();
        sb.append("DELETE FROM "						);
        sb.append(		"option_categories "			);
        sb.append("WHERE "								);
        sb.append(		"option_category_name = ? "		);
        sb.append("AND "								);
        sb.append(		"option_category_value = ?;"	);
        final String DELETE_OPTION_SQL = sb.toString();

        ArrayList<Object> params = new ArrayList<>();
        params.add(optionCategoryBean.getOptionCategoryName());
        params.add(optionCategoryBean.getOptionCategoryValue());

        try (Connection conn = DatabaseConnection.getConnection()) {
            try {
                int totalDeletedRows = GeneralDao.executeUpdate(conn, DELETE_OPTION_SQL, params);
                conn.commit(); // コミット
                return totalDeletedRows;
            } catch (SQLException e) {
                conn.rollback(); // ロールバック
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
    }
}
