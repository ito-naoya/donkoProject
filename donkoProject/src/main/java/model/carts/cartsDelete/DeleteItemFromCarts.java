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
		StringBuilder sb = new StringBuilder();
		sb.append("DELETE ");
		sb.append("FROM ");
		sb.append(		"carts ");
		sb.append("WHERE ");
		//ここでパラメータを使う(1/2)
		sb.append(		"user_id = ? ");
		sb.append("AND ");
		//ここでパラメータを使う(2/2)
		sb.append(		"item_id = ? ");
		final String DELETE_ITEM_ALL_SQL = sb.toString();

		//削除したい商品のIDと登録したユーザーのIDをリストに追加
		ArrayList<Object> params = new ArrayList<Object>();
		params.add(cartBean.getUserId());
		params.add(cartBean.getItemId());

		//データベース接続
		try (Connection conn = DatabaseConnection.getConnection();) {
			try {
				//カートから対象の商品を削除
				GeneralDao.executeUpdate(conn, DELETE_ITEM_ALL_SQL, params);
				//sqlをコミット
				conn.commit();
			} catch (SQLException e) {
				if (!conn.isClosed()) {
					//一つでも処理が失敗したらロールバック
					conn.rollback();
				}
				e.printStackTrace();
			}
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}

	};

}
