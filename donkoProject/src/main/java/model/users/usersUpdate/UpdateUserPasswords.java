package model.users.usersUpdate;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import classes.user.CustomerUser;
import dao.DatabaseConnection;
import dao.GeneralDao;
import hash.HashGenerator;

public class UpdateUserPasswords {
	
	//ユーザー情報を更新する
	public static void updateUserPasswords(CustomerUser customerUser){
		// SQLコマンド生成
		StringBuilder sb = new StringBuilder();
		sb.append("UPDATE "								);
		sb.append(	"users "							);
		sb.append("SET "								);
		sb.append(	"password = ? "					);
		sb.append("WHERE "								);
		sb.append(	"user_login_id = ? "				);
		final String UPDATE_USER_PASSWORD_SQL = sb.toString();
		
		// パスワードのハッシュ化
		String password = customerUser.getPassword();
		String hashedPassword = null;
		try {
			hashedPassword = HashGenerator.generateHash(password);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		 
		// ？の値を渡す
		ArrayList<Object> param = new ArrayList<Object>();
		param.add(hashedPassword);
		param.add(customerUser.getUserLoginId());
		
		// SQL実行
		try (Connection connection = DatabaseConnection.getConnection()) {
			try {
				GeneralDao.executeUpdate(connection, UPDATE_USER_PASSWORD_SQL, param);
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
