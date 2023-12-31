package model.users.usersUpdate;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import classes.user.CustomerUser;
import dao.DatabaseConnection;
import dao.GeneralDao;

public class UpdateUserInfoByAdmin {
	
	//ユーザー情報を編集する(アドミン権限)
	public static Boolean updateUserInfoByAdmin(CustomerUser customerUser){
		
		// ユーザー情報を更新するSQL（アドミン権限）
		StringBuilder sb = new StringBuilder();
		sb.append("UPDATE "						);
		sb.append(	"users "					);
		sb.append("SET "						);
		//パラメータをここで使う(1/2)
		sb.append(	"user_login_id = ?, "		);
		if (customerUser.isDeleted()) {
		 sb.append("user_delete_flg = 1 ") ;
		}else {
		 sb.append("user_delete_flg = 0 ");
		}
		sb.append("WHERE "						);
		//パラメータをここで使う(2/2)
		sb.append(	"user_id = ? ;"				);
		//sqlを文字列化
		final String UPDATE_USER_INFO_SQL = sb.toString();
		
		//更新する値をすべてリストに追加
		ArrayList<Object> params = new ArrayList<Object>();
		params.add(customerUser.getUserLoginId());
		params.add(customerUser.getUserId());
		
		//コミットフラグをfalseで初期化
		Boolean isCommit = false;
		
		// SQL実行
		try (Connection conn = DatabaseConnection.getConnection()) {
			try {
				//ユーザー情報の更新
				int updatedRows = GeneralDao.executeUpdate(conn, UPDATE_USER_INFO_SQL, params);
				
				if(updatedRows == 0) throw new SQLException();
				
				//sqlをコミット
				conn.commit();
				isCommit = true;
			} catch (SQLException e) {
				if(!conn.isClosed()) {
					//一つでも処理が失敗したらロールバック
					conn.rollback();
				}
				e.printStackTrace();
				return false;
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			return false;
		}
		
		return isCommit;
	};

}
