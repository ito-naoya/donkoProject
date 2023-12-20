package model.carts.cartsDelete;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import classes.user.CustomerUser;
import dao.DatabaseConnection;
import dao.GeneralDao;

public class DeleteItemAllFromCarts {

	//カートから全ての商品を削除する
	public static Boolean deleteItemAllFromCarts(CustomerUser customerUser) {

		//カートからログインしているユーザーが追加した商品を全て削除するSQL
		StringBuilder sb = new StringBuilder();
		sb.append("DELETE ");
		sb.append("FROM ");
		sb.append(	"carts ");
		sb.append("WHERE ");
		//パラメータをここで使う
		sb.append(	"user_id = ? ");
		//sqlを文字列化
		final String DELETE_ITEM_ALL_SQL = sb.toString();

		//対象のユーザーのuser_idをリストに追加
		ArrayList<Object> params = new ArrayList<Object>();
		params.add(customerUser.getUserId());
		
		//コミットフラグをfalseで初期化
		Boolean isCommit = false;

		//データベースに接続
		try (Connection conn = DatabaseConnection.getConnection();) {
			try {
				//カートからログインしているユーザーが追加した商品を全て削除する
				GeneralDao.executeUpdate(conn, DELETE_ITEM_ALL_SQL, params);
				//sqlをコミット
				conn.commit();
				isCommit = true;
			} catch (SQLException e) {
				if (!conn.isClosed()) {
					//一つでも処理が失敗したらロールバック
					conn.rollback();
				}
				e.printStackTrace();
				return false;
			}
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
			return false;
		}
		
		return isCommit;
		
	};
}
