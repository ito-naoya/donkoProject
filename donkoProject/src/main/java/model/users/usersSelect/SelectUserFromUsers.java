package model.users.usersSelect;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import classes.user.CustomerUser;
import dao.DatabaseConnection;
import dao.GeneralDao;
import hash.HashGenerator;

public class SelectUserFromUsers {
	
	//ログインする
	public static CustomerUser selectUserFromUsers(CustomerUser customerUser) {
		
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT ");
		sb.append(	"user_id, ");
		sb.append(	"user_delete_flg ");
		sb.append("FROM ");
		sb.append(	"users ");
		sb.append("WHERE ");
		sb.append(	"user_login_id = ? ");
		sb.append("AND ");
		sb.append(	"password = ? ");
		final String SELECT_USER_SQL = sb.toString();

		// パスワードのハッシュ化
		String password = customerUser.getPassword();
		String hashedPassword = null;
		try {
			hashedPassword = HashGenerator.generateHash(password);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
		ArrayList<Object> params = new ArrayList<Object>();
		params.add(customerUser.getUserLoginId());
		params.add(hashedPassword);
	
		CustomerUser cu = null;
		
		try(Connection conn = DatabaseConnection.getConnection();){
			try(ResultSet rs = GeneralDao.executeQuery(conn, SELECT_USER_SQL, params)){
				
				while(rs.next()) {
					cu = new CustomerUser();
					cu.setUserId(rs.getInt("user_id"));
					cu.setDeleted(rs.getBoolean("user_delete_flg"));
				}
				
				//ユーザー情報がない場合
				if(cu == null) throw new SQLException();
				
			}catch(SQLException e) {
				if(!conn.isClosed()) {
					conn.rollback();
				}
				e.printStackTrace();
				return null;
			}
			
		}catch(SQLException | ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		} 
		
		return cu;

	};

}
