package model.carts.cartsSelect;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import bean.CartBean;
import classes.user.CustomerUser;
import dao.DatabaseConnection;
import dao.GeneralDao;

public class SelectItemListFromCarts {

	//カート内の商品一覧を取得する
	public static ArrayList<CartBean> selectItemListFromCarts(CustomerUser customerUser) {

		
		//カートから対象のユーザーが登録した商品の一覧を取得
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT ");
		sql.append(		"i.item_id, ");
		sql.append(		"i.item_name, ");
		sql.append(		"i.file_name, ");
		sql.append(		"i.price, ");
		sql.append(		"c.quantity ");
		sql.append("FROM ");
		sql.append(		"carts as c ");
		sql.append("INNER JOIN ");
		sql.append(		"items as i ");
		sql.append("ON ");
		sql.append(		"i.item_id = c.item_id ");
		sql.append("WHERE ");
		//パラメータをここで使う
		sql.append(		"c.user_id = ? ");
		//sqlを文字列化
		final String SELECT_CARTLIST_SQL = sql.toString();

		//商品をカートに追加したユーザーのIDをリストに追加
		ArrayList<Object> params = new ArrayList<Object>();
		params.add(customerUser.getUserId());

		//取得したカート内にある商品情報を保持するリストをnew
		ArrayList<CartBean> cartBeanList = new ArrayList<CartBean>();

		//データベース接続
		try (Connection conn = DatabaseConnection.getConnection();) {
			//カート内の対象ユーザーが追加した商品を全て取得
			try (ResultSet rs = GeneralDao.executeQuery(conn, SELECT_CARTLIST_SQL, params);) {

				while (rs.next()) {
					//カート情報を保持するcartBeanをnew
					CartBean cb = new CartBean();
					cb.setItemId(rs.getInt("item_id"));
					cb.setItemName(rs.getString("item_name"));
					cb.setImageFileName(rs.getString("file_name"));
					cb.setItemPrice(rs.getInt("price"));
					cb.setQuantity(rs.getInt("quantity"));
					 //カート情報を保持したcartBeanをcartBeanListに追加する
					cartBeanList.add(cb);
				}

			} catch (SQLException e) {
				if (!conn.isClosed()) {
					conn.rollback();
				}
				e.printStackTrace();
				return null;
			}

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			return null;
		}
		//カート情報を保持したcartBeanListを返す
		return cartBeanList;
	};
}
