package model.users.usersSelect;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import classes.user.AdminUser;
import dao.DatabaseConnection;
import dao.GeneralDao;

public class SelectAdminFromAdmins {
	//アドミン側からログインする
		public static boolean selectAdminFromAdmins(AdminUser adminUser){

			//アドミン情報が存在するか確認する
			StringBuilder sb = new StringBuilder();
			sb.append("SELECT ");
			sb.append(		"count(*) ");
			sb.append("FROM ");
			sb.append(		"admins ");
			sb.append("WHERE ");
			sb.append(		"admin_login_id = ?");
			sb.append("AND ");
			sb.append(		"admin_password = ?");
			//sqlを文字列化
			final String SELECT_ADMINUSER_SQL = sb.toString();

			//ユーザーIDとパスワードを取得
			String adminUserId = adminUser.getAdminLoginId();
			String adminPassword = adminUser.getPassword();
			//アドミン情報が存在するか確認するための変数
	        boolean adminExists = false;

			List<Object> params = Arrays.asList(adminUserId,adminPassword);
			//データベース接続
			try(Connection conn = DatabaseConnection.getConnection();){
				//ユーザーを全て取得
				try(ResultSet rs = GeneralDao.executeQuery(conn, SELECT_ADMINUSER_SQL, params)){

					adminExists = rs.next() && rs.getLong(1) > 0;

				} catch (SQLException e) {
	                if (!conn.isClosed()) {
	                    // 処理が一つでも失敗したらロールバック
	                    conn.rollback();
	                }
	                e.printStackTrace();
	                return false;
	            }
	        } catch (SQLException | ClassNotFoundException e) {
	            e.printStackTrace();
	            return false;
	        }

	        // 管理者が存在するかどうかに基づいて結果を返す
	        return adminExists;
	    };
	}