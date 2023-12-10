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
	public static ArrayList<CartBean> selectItemListFromCarts(CustomerUser customerUser){
		
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT "					);
		sql.append(		"c.cart_id, "			);
		sql.append(		"i.item_id, "			);
		sql.append(		"i.item_name, "			);
		sql.append(		"i.file_name, "			);
		sql.append(		"i.price, "				);
		sql.append(		"c.quantity "			);
		sql.append("FROM "						);
		sql.append(		"carts as c "			);
		sql.append("INNER JOIN "				);
		sql.append(		"items as i "			);
		sql.append("ON "						);
		sql.append(		"i.item_id = c.item_id ");
		sql.append("WHERE "						);
		sql.append(		"c.user_id = ? "		);
		final String SELECT_CARTLIST_SQL = sql.toString();
		
		ArrayList<Object> params = new ArrayList<Object>();
		params.add(customerUser.getUserId());
		
		CartBean cb = null;
		ArrayList<CartBean> cartBeanList = new ArrayList<CartBean>();
		
		try(Connection conn = DatabaseConnection.getConnection();){
			try(ResultSet rs = GeneralDao.executeQuery(conn, SELECT_CARTLIST_SQL, params);){
				
				while(rs.next()) {
					cb = new CartBean();
					cb.setCartId(rs.getInt("cart_id"));
					cb.setItemId(rs.getInt("item_id"));
					cb.setItemName(rs.getString("item_name"));
					cb.setImageFileName(rs.getString("file_name"));
					cb.setItemPrice(rs.getInt("price"));
					cb.setQuantity(rs.getInt("quantity"));
					cartBeanList.add(cb);
				}
				
			} catch (SQLException e) {
				if(!conn.isClosed()) {
					conn.rollback();
				}
			e.printStackTrace();
			}
			
		} catch (ClassNotFoundException | SQLException e1) {
			e1.printStackTrace();
		}
		return cartBeanList;
	};	
}
