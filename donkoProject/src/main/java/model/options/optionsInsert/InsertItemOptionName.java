package model.options.optionsInsert;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import bean.OptionCategoryBean;
import dao.DatabaseConnection;
import dao.GeneralDao;

public class InsertItemOptionName {

    // 商品のオプションを登録する
    public static Integer insertItemOptionName(OptionCategoryBean optionCategoryBean) {
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO old ");
        sb.append("(option_category_name) ");
        sb.append("VALUES ");
        sb.append("(?);");

        final String INSERT_NEWOPTION_SQL = sb.toString();

        ArrayList<Object> param = new ArrayList<Object>();
        param.add(optionCategoryBean.getOptionCategoryName());

        try (Connection conn = DatabaseConnection.getConnection()) {
            try {
                int totalUpdatedRows = GeneralDao.executeUpdate(conn, INSERT_NEWOPTION_SQL, param);
                conn.commit(); // コミット
                return totalUpdatedRows;
            } catch (SQLException e) {
                conn.rollback(); // ロールバック
                // 重複した値の挿入の場合は0件を返す
                if(e.getSQLState().startsWith("23")) { // SQLステート23は一般的に重複キーのエラー
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
