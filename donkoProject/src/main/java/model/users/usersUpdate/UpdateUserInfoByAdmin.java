package model.users.usersUpdate;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import classes.user.CustomerUser;
import dao.DatabaseConnection;
import dao.GeneralDao;

public class UpdateUserInfoByAdmin {
	
	//ユーザーを削除する(論理削除)
	public static Boolean updateUserInfoByAdmin(CustomerUser customerUser){
		
		// ユーザー情報を更新するSQL（アドミン権限）
		StringBuilder sb = new StringBuilder();
		sb.append("UPDATE "						);
		sb.append(	"users "					);
		sb.append("SET "						);
		//パラメータをここで使う(1/5)
		sb.append(	"user_login_id = ?, "		);
		//パラメータをここで使う(2/5)
		sb.append(	"user_name = ?, "			);
		//パラメータをここで使う(3/5)
		sb.append(	"birthday = ?, "			);
		if (customerUser.isDeleted()) {
		 sb.append("user_delete_flg = 1, ") ;
		}else {
		 sb.append("user_delete_flg = 0, ");
		}
		//パラメータをここで使う(4/5)
		sb.append(	"gender = ? "				);
		sb.append("WHERE "						);
		//パラメータをここで使う(5/5)
		sb.append(	"user_id = ? ;"				);
		//sqlを文字列化
		final String UPDATE_USER_INFO_SQL = sb.toString();
		
		//更新する値をすべてリストに追加
		ArrayList<Object> param = new ArrayList<Object>();
		param.add(customerUser.getUserLoginId());
		param.add(customerUser.getUserName());
		param.add(customerUser.getBirthday());
		param.add(customerUser.getGender());
		param.add(customerUser.getUserId());
		
		//コミットフラグをfalseで初期化
		Boolean isCommit = false;
		
		// SQL実行
		try (Connection conn = DatabaseConnection.getConnection()) {
			try {
				GeneralDao.executeUpdate(conn, UPDATE_USER_INFO_SQL, param);
				conn.commit();
				isCommit = true;
			} catch (SQLException e) {
				if(!conn.isClosed()) {
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
