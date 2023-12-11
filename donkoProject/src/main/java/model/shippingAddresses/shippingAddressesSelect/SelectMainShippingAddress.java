package model.shippingAddresses.shippingAddressesSelect;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import bean.ShippingAddressBean;
import classes.user.CustomerUser;
import dao.DatabaseConnection;
import dao.GeneralDao;

public class SelectMainShippingAddress {
	
	//メインの配送先を取得する
	public static ShippingAddressBean selectMainShippingAddress(CustomerUser customerUser){
		
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT ");
		sql.append("shipping_address_id, ");
		sql.append("postal_code, ");
		sql.append("address, ");
		sql.append("addressee ");
		sql.append("FROM ");
		sql.append("shipping_addresses ");
		sql.append("WHERE ");
		sql.append("user_id = ? ");
		sql.append("AND ");
		sql.append("main_shipping_address = 1");
		final String SELECT_MAINADDRESS_SQL = sql.toString();
		
		ArrayList<Object> params = new ArrayList<Object>();
		params.add(customerUser.getUserId());
		
		ShippingAddressBean sa = null;
		
		try(Connection conn = DatabaseConnection.getConnection();){
			try(ResultSet rs = GeneralDao.executeQuery(conn, SELECT_MAINADDRESS_SQL, params)) {
				
				while(rs.next()) {
					sa = new ShippingAddressBean();
					sa.setShippingAddressId(rs.getInt("shipping_address_id"));
					sa.setPostalCode(rs.getString("postal_code"));
					sa.setAddress(rs.getString("address"));
					sa.setAddressee(rs.getString("addressee"));
				}
				
			}catch(SQLException e) {
				if(!conn.isClosed()) {
					conn.rollback();
				}
				e.printStackTrace();
				return null;
			}
		}catch(SQLException | ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}
		return sa;
	};
}
