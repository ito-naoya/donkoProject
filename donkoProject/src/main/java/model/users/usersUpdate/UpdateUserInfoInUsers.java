package model.users.usersUpdate;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import classes.user.CustomerUser;
import dao.DatabaseConnection;
import dao.GeneralDao;
import hash.HashGenerator;

public class UpdateUserInfoInUsers {
	
	//ユーザー情報を更新する
	public static void updateUserInfoInUsers(CustomerUser CustomerUser){
		// SQLコマンド生成
		StringBuilder sb = new StringBuilder();
		sb.append("UPDATE "						);
		sb.append(	"users "					);
		sb.append("SET "						);
		sb.append(	"user_login_id = ?, "		);
		sb.append(	"user_name = ?, "			);
		sb.append(	"password = ?, "			);
		sb.append(	"birthday = ?, "			);
		sb.append(	"gender = ?, "				);
		sb.append("WHERE "						);
		sb.append(	"user_id = ?;"				);
		String sql = sb.toString();
		
		// パスワードのハッシュ化
		String password = CustomerUser.getPassword();
		String hashedPassword = null;
		try {
			hashedPassword = HashGenerator.generateHash(password);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
		// ？の値を渡す
		ArrayList<Object> param = new ArrayList<Object>();
		param.add(CustomerUser.getUserLoginId());
		param.add(CustomerUser.getUserName());
		param.add(hashedPassword);
		param.add(CustomerUser.getBirthday());
		param.add(CustomerUser.getGender());
		param.add(CustomerUser.getUserId());
		
		// SQL実行
		try (Connection connection = DatabaseConnection.getConnection()) {
			try {
				GeneralDao.executeUpdate(connection, sql, param);
				connection.commit();
			} catch (Exception e) {
				if(!connection.isClosed()) {
					connection.rollback();
					e.printStackTrace();
				}
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	};
}
