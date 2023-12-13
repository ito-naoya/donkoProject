package model.items.itemsUpdate;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import bean.CartBean;
import bean.PurchaseBean;
import classes.Cart;
import classes.user.CustomerUser;
import dao.DatabaseConnection;
import dao.GeneralDao;

public class UpdateItemStockInItems {
	
	public static void updateItemStockInItems(PurchaseBean purchaseBean) {
		
		 StringBuilder sql  = new StringBuilder();
		 sql.append("UPDATE ");
		 sql.append(	"items ");
		 sql.append("SET ");
		 sql.append(	"stock = stock - ? ");
		 sql.append("WHERE ");
		 sql.append(	"item_id = ?");
		 final String UPDATE_ITEMSTOCK_SQL = sql.toString();
		 
		 try(Connection conn = DatabaseConnection.getConnection();){
				 
				CustomerUser customerUser = new CustomerUser();
				customerUser.setUserId(purchaseBean.getUserId());
				ArrayList<CartBean> cartList = Cart.getItemListFromCart(customerUser);
				
				cartList.forEach(cb -> {
					 ArrayList<Object> params = new ArrayList<Object>();
					 params.add(cb.getQuantity());
					 params.add(cb.getItemId());
					 
					try {
						GeneralDao.executeUpdate(conn, UPDATE_ITEMSTOCK_SQL, params);
						conn.commit();
					} catch (SQLException e) {
						e.printStackTrace();
					}
					 
				});
				 
		 }catch(SQLException | ClassNotFoundException e) {
			 e.printStackTrace();
		 }
		 
	}

}
