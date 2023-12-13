package model.purchases.purchasesInsert;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import bean.PurchaseBean;
import dao.DatabaseConnection;
import dao.GeneralDao;

public class InsertPurchaseToPurchases {

	//商品を購入する
	public static void insertPurchaseToPurchases(PurchaseBean purchaseBean) {

		//購入情報を追加するSQL
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO ");
		sql.append(		"purchases");
		sql.append("(");
		sql.append(		"user_id, ");
		sql.append(		"total_amount, ");
		sql.append(		"shipping_address_id, ");
		sql.append(		"shipping_id");
		sql.append(")");
		sql.append("VALUES ");
		sql.append("(");
		//パラメータをここで使う(1/3)
		sql.append(		"?, ");
		//パラメータをここで使う(2/3)
		sql.append(		"?, ");
		//パラメータをここで使う(3/3)
		sql.append(		"?, ");
		sql.append(		"1");
		sql.append(")");
		//sqlを文字列化
		final String INSERT_PURCHASES_SQL = sql.toString();

		//購入したユーザーのIDと合計金額、配送先IDをリストに追加
		ArrayList<Object> params = new ArrayList<Object>();
		params.add(purchaseBean.getUserId());
		params.add(purchaseBean.getTotalAmount());
		params.add(purchaseBean.getShippingAddressId());

		//データベース接続
		try (Connection conn = DatabaseConnection.getConnection()) {
			try {
				//購入情報を追加する
				GeneralDao.executeUpdate(conn, INSERT_PURCHASES_SQL, params);
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
