package model.carts.cartsUpdate;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import bean.CartBean;
import dao.DatabaseConnection;
import dao.GeneralDao;

public class UpdateItemQuantityInCarts {
	
	//カート内の商品の数量を更新する
	public static void updateItemQuantityInCarts(CartBean cartBean){
		
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE "		   );
		sql.append(		"carts "	   );
		sql.append("SET "	 		   );
		sql.append(		"quantity = ? ");
		sql.append("WHERE "			   );
		sql.append(		"user_id = ? " );
		sql.append("AND "			   );
		sql.append(		"item_id = ? " );
		final String UPDATE_QUANTITY_SQL = sql.toString();
		
		ArrayList<Object> params = new ArrayList<Object>();
		params.add(cartBean.getQuantity());
		params.add(cartBean.getUserId());
		params.add(cartBean.getItemId());
		
		try(Connection conn = DatabaseConnection.getConnection();){
			try {
				GeneralDao.executeUpdate(conn, UPDATE_QUANTITY_SQL, params);
				conn.commit();
			}catch(SQLException e) {
				if(!conn.isClosed()) {
					conn.rollback();
				}
				e.printStackTrace();
			}
		}catch(SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	};
}
