package model.users.usersSelect;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import classes.user.CustomerUser;
import dao.DatabaseConnection;
import dao.GeneralDao;

public class SelectUserDuplicate {

	//ユーザーの重複をチェックする
	public static Integer selectUserDuplicate(CustomerUser customerUser){;

		//アドミン情報が存在するか確認する
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT ");
		sb.append(		"count(*) ");
		sb.append("FROM ");
		sb.append(		"users ");
		sb.append("WHERE ");
		sb.append(		"user_login_id = ?;");

		//sqlを文字列化
		final String SELECT_ADMINUSER_SQL = sb.toString();

		//ユーザーIDを取得
		String customerUserId = customerUser.getUserLoginId();
		Integer adminExists;

		List<Object> params = Arrays.asList(customerUserId);
		//データベース接続
		try(Connection conn = DatabaseConnection.getConnection();){
			//ユーザーを全て取得
			try(ResultSet rs = GeneralDao.executeQuery(conn, SELECT_ADMINUSER_SQL, params)){
				rs.next();
				adminExists = rs.getInt("count(*)");

			} catch (SQLException e) {
                if (!conn.isClosed()) {
                    // 処理が一つでも失敗したらロールバック
                    conn.rollback();
                }
                e.printStackTrace();
                return null;
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }

        // 管理者が存在するかどうかに基づいて結果を返す
        return adminExists;
    };

}
