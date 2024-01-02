package model.options.optionsInsert;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import bean.OptionCategoryBean;
import dao.DatabaseConnection;
import dao.GeneralDao;

public class InsertItemOptionValue {

    // 商品のオプション詳細を登録する
    public static Integer insertItemOptionValue(OptionCategoryBean optionCategoryBean) {

    	//重複をチェックする
    	StringBuilder sb1 = new StringBuilder();
		sb1.append("SELECT COUNT(*) "				);
		sb1.append("FROM "							);
		sb1.append(		"option_categories  "		);
		sb1.append("WHERE "							);
		sb1.append(		"option_category_name = ? "	);
		sb1.append("AND "							);
		sb1.append(		"option_category_value = ?;");
        final String CHECK_DUPLICATE_SQL = sb1.toString();

        //incrementIdの最大値を取得
        StringBuilder sb2 = new StringBuilder();
        sb2.append("SELECT MAX( "						);
        sb2.append(		"option_category_increment_id) ");
        sb2.append("FROM "								);
        sb2.append(		"option_categories  "			);
        sb2.append("WHERE "								);
        sb2.append(		"option_category_name = ?;"		);
        final String QUERY_MAX_ID_SQL = sb2.toString();

        //incrementIdの最大値を取得
        StringBuilder sb3 = new StringBuilder();
        sb3.append("INSERT INTO "																		);
        sb3.append(		"option_categories "															);
        sb3.append(		"(option_category_name, option_category_increment_id, option_category_value) "	);
        sb3.append("VALUES "																			);
        sb3.append(		"(?, ?, ?);"																	);
        final String INSERT_NEWOPTION_SQL = sb3.toString();

        Integer totalUpdatedRows = null;
        int maxId = 0;

        ArrayList<Object> params1 = new ArrayList<Object>();
		params1.add(optionCategoryBean.getOptionCategoryName());
		params1.add(optionCategoryBean.getOptionCategoryValue());

		ArrayList<Object> params2 = new ArrayList<Object>();
		params2.add(optionCategoryBean.getOptionCategoryName());

		try (Connection conn = DatabaseConnection.getConnection()) {
            try {
            	//重複確認
            	ResultSet rs = GeneralDao.executeQuery(conn,CHECK_DUPLICATE_SQL, params1);
                    if (rs.next() && rs.getInt(1) > 0) {
                        // 重複が存在する
                        return 0;
                    }
                // 最大IDの取得
                ResultSet rsm = GeneralDao.executeQuery(conn,QUERY_MAX_ID_SQL, params2);
                if (rsm.next()) {
                    maxId = rsm.getInt(1);
                }
                int newId = maxId + 1;

                // 新しいオプションの挿入
                ArrayList<Object> params3 = new ArrayList<>();
                params3.add(optionCategoryBean.getOptionCategoryName());
                params3.add(newId);
                params3.add(optionCategoryBean.getOptionCategoryValue());

                totalUpdatedRows = GeneralDao.executeUpdate(conn, INSERT_NEWOPTION_SQL, params3);
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
		return totalUpdatedRows;
    }
}
