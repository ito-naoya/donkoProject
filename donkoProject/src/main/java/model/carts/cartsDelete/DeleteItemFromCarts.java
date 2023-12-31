package model.carts.cartsDelete;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import bean.CartBean;
import dao.DatabaseConnection;
import dao.GeneralDao;

public class DeleteItemFromCarts {

	//カートから商品を削除する
	public static Boolean deleteItemFromCarts(CartBean cartBean) {

		//カートからユーザーが登録した商品を削除するSQL
		StringBuilder sb = new StringBuilder();
		sb.append("DELETE ");
		sb.append("FROM ");
		sb.append(	"carts ");
		sb.append("WHERE ");
		//ここでパラメータを使う(1/2)
		sb.append(	"user_id = ? ");
		sb.append("AND ");
		//ここでパラメータを使う(2/2)
		sb.append(	"item_id = ? ");
		final String DELETE_ITEM_ALL_SQL = sb.toString();

		//削除したい商品のIDと登録したユーザーのIDをリストに追加
		ArrayList<Object> params = new ArrayList<Object>();
		params.add(cartBean.getUserId());
		params.add(cartBean.getItemId());
		
		//コミットフラグをfalseで初期化
		Boolean isCommit = false;

		//データベース接続
		try (Connection conn = DatabaseConnection.getConnection();) {
			try {
				//カートから対象の商品を削除
				int updatedRows = GeneralDao.executeUpdate(conn, DELETE_ITEM_ALL_SQL, params);
				
				//更新件数が0件の場合
				if(updatedRows == 0) throw new SQLException();
				
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
