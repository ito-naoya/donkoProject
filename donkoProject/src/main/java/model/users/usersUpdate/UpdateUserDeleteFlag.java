package model.users.usersUpdate;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import classes.user.CustomerUser;
import dao.DatabaseConnection;
import dao.GeneralDao;

public class UpdateUserDeleteFlag {
	
	//ユーザーを論理削除
	public static Boolean updateUserDeleteFlags(CustomerUser customerUser){
		// SQLコマンド生成
		StringBuilder sb = new StringBuilder();
		sb.append("UPDATE "								);
		sb.append(	"users "							);
		sb.append("SET "								);
		sb.append(	"user_delete_flg = 1 "				);
		sb.append("WHERE "								);
		sb.append(	"user_id = ? "						);
		final String UPDATE_USER_DELETE_FLAG_SQL = sb.toString();
		
		// ？の値を渡す
		ArrayList<Object> param = new ArrayList<Object>();
		param.add(customerUser.getUserId());
		
		// SQL実行
		try (Connection connection = DatabaseConnection.getConnection()) {
			try {
				GeneralDao.executeUpdate(connection, UPDATE_USER_DELETE_FLAG_SQL, param);
				connection.commit();
			} catch (Exception e) {
				if(!connection.isClosed()) {
					connection.rollback();
				}
				e.printStackTrace();
				return false;
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	};
}
