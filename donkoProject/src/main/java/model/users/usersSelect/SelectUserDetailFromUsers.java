package model.users.usersSelect;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import classes.user.CustomerUser;
import dao.DatabaseConnection;
import dao.GeneralDao;

public class SelectUserDetailFromUsers {
	
	//ユーザーの詳細情報を取得する
	public static CustomerUser selectUserDetailFromUsers(CustomerUser customerUser){
		
		// SQLコマンド生成
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT " );
		sb.append(	"user_login_id, "											);
		sb.append(	"user_name, "												);
		sb.append(	"password, "												);
		sb.append(	"birthday, "												);
		sb.append(	"gender "													);
		sb.append("FROM "														);
		sb.append(	"users "													);
		sb.append("WHERE "														);
		sb.append(	"users.user_id = ?"											);
		String sql = sb.toString();
		
		// ？の引数に渡す値
		ArrayList<Object> param = new ArrayList<Object>();
		param.add(customerUser.getUserId());
		
		try (Connection connection = DatabaseConnection.getConnection()) {
			try (ResultSet results = GeneralDao.executeQuery(connection, sql, param)) {
				
				while (results.next()) {
					customerUser = new CustomerUser();
					customerUser.setUserLoginId(results.getString("user_login_id"));
					customerUser.setUserName(results.getString("user_name"));
					customerUser.setPassword(results.getString("password"));
					customerUser.setBirthday(results.getDate("birthday"));
					customerUser.setGender(results.getString("gender"));
				}
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
		return customerUser;
	}
}
