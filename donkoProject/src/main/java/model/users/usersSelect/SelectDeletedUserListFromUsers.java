package model.users.usersSelect;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import classes.user.CustomerUser;
import dao.DatabaseConnection;
import dao.GeneralDao;

public class SelectDeletedUserListFromUsers {

	//削除したユーザーの一覧を取得する
	public static ArrayList<CustomerUser> selectDeletedUserListFromUsers() {
	
		//削除済みユーザーの一覧を取得するsql
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT ");
		sb.append(	"user_id, ");
		sb.append(	"user_login_id, ");
		sb.append(	"user_name, ");
		sb.append(	"user_delete_flg ");
		sb.append("FROM ");
		sb.append(	"users ");
		sb.append("WHERE ");
		sb.append(	"user_delete_flg = 1");
		//sqlを文字列化
		final String SELECT_DELETED_USER_SQL = sb.toString();
		
		//削除済みユーザーの情報を保持するリストをnew
		ArrayList<CustomerUser> customerUserList = new ArrayList<CustomerUser>();
		
		//データベース接続
		try(Connection conn = DatabaseConnection.getConnection();){
			//削除済みユーザーを全て取得
			try(ResultSet rs = GeneralDao.executeQuery(conn, SELECT_DELETED_USER_SQL, new ArrayList<Object>());){
				
				while(rs.next()) {
					//ユーザー情報を保持するcustomerUserをnew
					CustomerUser cu = new CustomerUser();
					cu.setUserId(rs.getInt("user_id"));
					cu.setUserLoginId(rs.getString("user_login_id"));
					cu.setUserName(rs.getString("user_name"));
					cu.setDeleted(rs.getBoolean("user_delete_flg"));
					//ユーザー情報を保持したcustomerUserをcustomerUserListに追加
					customerUserList.add(cu);
				}
				
			}catch(SQLException e) {
				if(!conn.isClosed()) {
					//処理が一つでも失敗したらロールバック
					conn.rollback();
				}
				e.printStackTrace();
				return null;
			}
		}catch(SQLException | ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}
		return customerUserList;
	};

}
