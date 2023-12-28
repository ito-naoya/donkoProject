package model.users.usersInsert;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import classes.user.CustomerUser;
import dao.DatabaseConnection;
import dao.GeneralDao;

public class InsertNewUserToUsers {

	//ユーザー新規登録する
	public static Boolean insertNewUserToUsers(CustomerUser customerUser){
		// SQLコマンド生成
				StringBuilder sb = new StringBuilder();
				sb.append("INSERT INTO " 				);
				sb.append(	"users ( "					);
				sb.append(		"user_login_id, "		);
				sb.append(		"password, "			);
				sb.append(		"user_name, "			);
				sb.append(		"user_delete_flg, "		);
				sb.append(		"false_count) "			);
				sb.append("VALUES ( "					);
				sb.append(	"?, "						);
				sb.append(	"?, "						);
				sb.append(	"?, "						);
				sb.append(	"0, "						);
				sb.append(	"0);"						);
				final String INSERT_NEWUSER_SQL = sb.toString();

				// ？の引数に渡す値
				ArrayList<Object> param = new ArrayList<Object>();
				param.add(customerUser.getUserLoginId());
				param.add(customerUser.getPassword());
				param.add(customerUser.getUserName());

				try (Connection connection = DatabaseConnection.getConnection()) {
					try {
						int updatedRows= GeneralDao.executeUpdate(connection, INSERT_NEWUSER_SQL, param);
						//更新件数が0件の場合
						if(updatedRows == 0) throw new SQLException();
						connection.commit();
					} catch (Exception e) {
						if(!connection.isClosed()) {
							connection.rollback();
							e.printStackTrace();
							return false;
						}
					}

				} catch (ClassNotFoundException | SQLException e) {
					e.printStackTrace();
					return false;
				}
				return true;
	};

}
