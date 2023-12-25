package model.carts.cartsUpdate;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import bean.CartBean;
import dao.DatabaseConnection;
import dao.GeneralDao;

public class UpdateItemQuantityInCarts {

	//カート内の商品の数量を更新する
	public static Boolean updateItemQuantityInCarts(CartBean cartBean) {

		//カート内の対象の商品の数量を更新するSQL
		StringBuilder sb = new StringBuilder();
		sb.append("UPDATE ");
		sb.append(	"carts ");
		sb.append("SET ");
		//パラメータをここで使う(1/3)
		sb.append(	"quantity = ? ");
		sb.append("WHERE ");
		//パラメータをここで使う(2/3)
		sb.append(	"user_id = ? ");
		sb.append("AND ");
		//パラメータをここで使う(3/3)
		sb.append(	"item_id = ? ");
		final String UPDATE_QUANTITY_SQL = sb.toString();

		//数量更新したい商品IDとログインしているユーザーのID、更新したい数量をリストに追加
		ArrayList<Object> params = new ArrayList<Object>();
		params.add(cartBean.getQuantity());
		params.add(cartBean.getUserId());
		params.add(cartBean.getItemId());
		
		//コミットフラグをfalseで初期化
		Boolean isCommit = false;

		//データベース接続
		try (Connection conn = DatabaseConnection.getConnection();) {
			try {
				//カート内の商品の数量を更新
				int updatedRows = GeneralDao.executeUpdate(conn, UPDATE_QUANTITY_SQL, params);
				
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
