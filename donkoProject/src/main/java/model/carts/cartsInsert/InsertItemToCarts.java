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

		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO "				  );
		sql.append(		"carts "				  );
		sql.append(			"(user_id, "		  );
		sql.append(			"item_id, "			  );
		sql.append(			"quantity) "		  );
		sql.append("VALUES "					  );
		sql.append(		"(?, "					  );
		sql.append(		"?, "					  );
		sql.append(		"1) "					  );
		sql.append("ON DUPLICATE KEY UPDATE "	  );
		sql.append(		"quantity = quantity + 1 ");
		final String INSERT_CART_SQL = sql.toString();

		ArrayList<Object> params = new ArrayList<Object>();
		params.add(cartBean.getUserId());
		params.add(cartBean.getItemId());

		try (Connection conn = DatabaseConnection.getConnection();) {
			try {
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
