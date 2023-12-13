package model.carts.cartsInsert;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import bean.CartBean;
import dao.DatabaseConnection;
import dao.GeneralDao;

public class InsertItemToCarts {

	//カートに商品を追加する
	public static void insertItemToCarts(CartBean cartBean) {

		//カートに商品を追加するSQL
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO ");
		sql.append(		"carts ");
		sql.append(		"(");
		sql.append(		"user_id, ");
		sql.append(		"item_id, ");
		sql.append(		"quantity");
		sql.append(		") ");
		sql.append("VALUES ");
		sql.append(		"(");
		//ここでパラメータを使う(1/2)
		sql.append(		"?, ");
		//ここでパラメータを使う(2/2)
		sql.append(		"?, ");
		sql.append(		"1");
		sql.append(		") ");
		sql.append("ON DUPLICATE KEY UPDATE ");
		sql.append(		"quantity = quantity + 1 ");
		//sqlを文字列化
		final String INSERT_CART_SQL = sql.toString();

		//追加したい商品のIDと登録したユーザーのIDをリストに追加
		ArrayList<Object> params = new ArrayList<Object>();
		params.add(cartBean.getUserId());
		params.add(cartBean.getItemId());

		//データベース接続
		try (Connection conn = DatabaseConnection.getConnection();) {
			try {
				//商品をカートに追加
				GeneralDao.executeUpdate(conn, INSERT_CART_SQL, params);
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
