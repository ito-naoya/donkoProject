package model.carts.cartsDelete;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import bean.CartBean;
import dao.DatabaseConnection;
import dao.GeneralDao;

public class DeleteItemFromCarts {

	//カートから商品を削除する
	public static void deleteItemFromCarts(CartBean cartBean) {

		//カートからユーザーが登録した商品を削除するSQL
		StringBuilder sql = new StringBuilder();
		sql.append("DELETE ");
		sql.append("FROM ");
		sql.append(		"carts ");
		sql.append("WHERE ");
		//ここでパラメータを使う(1/2)
		sql.append(		"user_id = ? ");
		sql.append("AND ");
		//ここでパラメータを使う(2/2)
		sql.append(		"item_id = ? ");
		final String DELETE_ITEMALL_SQL = sql.toString();

		//削除したい商品のIDと登録したユーザーのIDをリストに追加
		ArrayList<Object> params = new ArrayList<Object>();
		params.add(cartBean.getUserId());
		params.add(cartBean.getItemId());

		//データベース接続
		try (Connection conn = DatabaseConnection.getConnection();) {
			try {
				//カートから対象の商品を削除
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
