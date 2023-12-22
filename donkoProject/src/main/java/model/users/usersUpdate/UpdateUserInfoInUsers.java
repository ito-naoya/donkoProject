package model.users.usersUpdate;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import classes.user.CustomerUser;
import dao.DatabaseConnection;
import dao.GeneralDao;

public class UpdateUserInfoInUsers {
	
	//ユーザー情報を更新する
	public static Boolean updateUserInfoInUsers(CustomerUser customerUser){
		// SQLコマンド生成
		StringBuilder sb = new StringBuilder();
		sb.append("UPDATE "						);
		sb.append(	"users "					);
		sb.append("SET "						);
		sb.append(	"user_login_id = ?, "		);
		sb.append(	"user_name = ?, "			);
		sb.append(	"birthday = ?, "			);
		sb.append(	"gender = ? "				);
		sb.append("WHERE "						);
		sb.append(	"user_id = ? ;"				);
		final String UPDATE_USER_INFO_SQL = sb.toString();
		
		// ？の値を渡す
		ArrayList<Object> param = new ArrayList<Object>();
		param.add(customerUser.getUserLoginId());
		param.add(customerUser.getUserName());
		param.add(customerUser.getBirthday());
		param.add(customerUser.getGender());
		param.add(customerUser.getUserId());
		
		// SQL実行
		try (Connection connection = DatabaseConnection.getConnection()) {
			try {
				GeneralDao.executeUpdate(connection, UPDATE_USER_INFO_SQL, param);
				connection.commit();
			} catch (Exception e) {
				if(!connection.isClosed()) {
					connection.rollback();
					e.printStackTrace();
					return null;
				}
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			return null;
		}
		return true;
	};
}
