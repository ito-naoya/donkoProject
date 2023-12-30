package model.shippingAddresses.shippingAddressesSelect;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import bean.ShippingAddressBean;
import classes.user.CustomerUser;
import dao.DatabaseConnection;
import dao.GeneralDao;

public class SelectMainShippingAddressSort {
	
	//配送先一覧をメインの配送先順にソートして取得する
	public static ArrayList<ShippingAddressBean> selectMainShippingAddressSort(CustomerUser customerUser){
		
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT "					);
		sql.append("shipping_address_id, "		);
		sql.append("postal_code, "				);
		sql.append("address, "					);
		sql.append("addressee "					);
		sql.append("FROM "						);
		sql.append("shipping_addresses "		);
		sql.append("WHERE "						);
		sql.append("user_id = ? "				);
		sql.append("ORDER BY "					);
		sql.append("main_shipping_address DESC"	);
		final String SELECT_MAINADDRESS_SORT_SQL = sql.toString();
		
		ArrayList<Object> params = new ArrayList<Object>();
		params.add(customerUser.getUserId());
		
		ArrayList<ShippingAddressBean> shippingAddressList = new ArrayList<ShippingAddressBean>();
		ShippingAddressBean shippingAddressBean;
		
		try(Connection connection = DatabaseConnection.getConnection();){
			try(ResultSet result = GeneralDao.executeQuery(connection, SELECT_MAINADDRESS_SORT_SQL, params)) {
				
				while(result.next()) {
					shippingAddressBean = new ShippingAddressBean();
					shippingAddressBean.setShippingAddressId(result.getInt("shipping_address_id"));
					shippingAddressBean.setPostalCode(result.getString("postal_code"));
					shippingAddressBean.setAddress(result.getString("address"));
					shippingAddressBean.setAddressee(result.getString("addressee"));
					shippingAddressList.add(shippingAddressBean);
				}
				
			}catch(SQLException e) {
				if(!connection.isClosed()) {
					connection.rollback();
					e.printStackTrace();
					return null;
				}
			}
		}catch(SQLException | ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}
		return shippingAddressList;
	};
}
