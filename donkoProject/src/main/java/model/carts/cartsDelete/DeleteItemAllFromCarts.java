package model.carts.cartsDelete;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import classes.user.CustomerUser;
import dao.DatabaseConnection;
import dao.GeneralDao;

public class DeleteItemAllFromCarts {

	//カートから全ての商品を削除する
	public static void deleteItemAllFromCarts(CustomerUser customerUser) {

		//カート内の対象のユーザーの商品を全て削除するSQL
		StringBuilder sql = new StringBuilder();
		sql.append("DELETE ");
		sql.append("FROM ");
		sql.append(		"carts ");
		sql.append("WHERE ");
		//パラメータをここで使う
		sql.append(		"user_id = ? ");
		//sqlを文字列化
		final String DELETE_ITEMALL_SQL = sql.toString();

		//対象のユーザーのuser_idをリストに追加
		ArrayList<Object> params = new ArrayList<Object>();
		params.add(customerUser.getUserId());

		//データベースに接続
		try (Connection conn = DatabaseConnection.getConnection();) {
			try {
				GeneralDao.executeUpdate(conn, DELETE_ITEMALL_SQL, params);
				conn.commit();
			} catch (SQLException e) {
				if (!conn.isClosed()) {
					conn.rollback();
				}
				e.printStackTrace();
			}
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	};
}
