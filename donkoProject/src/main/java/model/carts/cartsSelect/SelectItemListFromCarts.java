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
		sql.append(		"items.item_id, ");
		sql.append(		"items.item_name, ");
		sql.append(		"items.file_name, ");
		sql.append(		"items.price, ");
		sql.append(		"items.stock, ");
		sql.append(		"carts.quantity, ");
		sql.append(		"group_concat(option_categories.option_category_value separator ',') ");
		sql.append("FROM ");
		sql.append(		"carts ");
		sql.append("INNER JOIN ");
		sql.append(		"items ");
		sql.append("ON ");
		sql.append(		"items.item_id = carts.item_id ");
		sql.append("INNER JOIN ");
		sql.append(		"`options` ");
		sql.append("ON ");
		sql.append(		"carts.item_id = `options`.item_id ");
		sql.append("INNER JOIN ");
		sql.append(		"option_categories ");
		sql.append("ON ");
		sql.append(		"`options`.option_category_name = option_categories.option_category_name ");
		sql.append("AND ");
		sql.append(		"`options`.option_category_increment_id = option_categories.option_category_increment_id ");
		sql.append("WHERE ");
		//ここでパラメータを使う
		sql.append(		"carts.user_id = ? ");
		sql.append("GROUP BY ");
		sql.append(		"items.item_id");
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
					cb.setItemStock(rs.getInt("stock"));
					cb.setItemOptionDetail(rs.getString("group_concat(option_categories.option_category_value separator ',')"));
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
