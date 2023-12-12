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

		StringBuilder sql = new StringBuilder();
		sql.append("DELETE ");
		sql.append("FROM ");
		sql.append(		"carts ");
		sql.append("WHERE ");
		sql.append(		"user_id = ? ");
		sql.append("AND ");
		sql.append(		"item_id = ? ");
		final String DELETE_ITEMALL_SQL = sql.toString();

		ArrayList<Object> params = new ArrayList<Object>();
		params.add(cartBean.getUserId());
		params.add(cartBean.getItemId());

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
