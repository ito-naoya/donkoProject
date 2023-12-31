package model.carts.cartsInsert;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import bean.CartBean;
import dao.DatabaseConnection;
import dao.GeneralDao;

public class InsertItemToCarts {

	//カートに商品を追加する
	public static Boolean insertItemToCarts(CartBean cartBean) {

		//カートに商品を追加するSQL
		StringBuilder sb = new StringBuilder();		
		sb.append("INSERT INTO ");
		sb.append(	"carts ");
		sb.append(		"( ");
		sb.append(			"user_id, ");
		sb.append(			"item_id, ");
		sb.append(			"quantity ");
		sb.append(		") ");
		sb.append("VALUES ");
		sb.append(	"( ");
		//ここでパラメータを使う(1/7)
		sb.append(		"?, ");
		//ここでパラメータを使う(2/7)
		sb.append(		"?, ");
		//ここでパラメータを使う(3/7)
		sb.append(		"? ");
		sb.append(	") ");
		sb.append("ON ");
		sb.append(	"DUPLICATE ");
		sb.append("KEY UPDATE ");
		sb.append(	"quantity = IF");
		sb.append(				"( ");
		//ここでパラメータを使う(4/7)
		sb.append(					"quantity + ? <= ( ");
		sb.append(										"SELECT ");
		sb.append(											"stock ");
		sb.append(										"FROM ");
		sb.append(											"items ");
		sb.append(										"WHERE ");
		//ここでパラメータを使う(5/7)
		sb.append(											"item_id = ? ");
		sb.append(									"), ");
		//ここでパラメータを使う(6/7)
		sb.append(									"quantity + ?, ");
		sb.append(									"(");
		sb.append(										"SELECT ");
		sb.append(											"stock ");
		sb.append(										"FROM ");
		sb.append(											"items ");
		sb.append(										"WHERE ");
		//ここでパラメータを使う(7/7)
		sb.append(											"item_id = ? ");
		sb.append(									")");
		sb.append(				")");
		//sqlを文字列化
		final String INSERT_CART_SQL = sb.toString();

		//追加したい商品のIDとログインしているユーザーのIDをリストに追加
		ArrayList<Object> params = new ArrayList<Object>();
		params.add(cartBean.getUserId());
		params.add(cartBean.getItemId());
		params.add(cartBean.getQuantity());
		params.add(cartBean.getQuantity());
		params.add(cartBean.getItemId());
		params.add(cartBean.getQuantity());
		params.add(cartBean.getItemId());

		//コミットフラグをfalseで初期化
		Boolean isCommit = false;

		//データベース接続
		try (Connection conn = DatabaseConnection.getConnection();) {
			try {
				//商品をカートに追加
				int updatedRows = GeneralDao.executeUpdate(conn, INSERT_CART_SQL, params);
				
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
