package model.shippingAddresses.shippingAddressesUpdate;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import bean.ShippingAddressBean;
import dao.DatabaseConnection;
import dao.GeneralDao;

public class UpdateShippingAddress {
	
	//配送先情報を更新する
	public static void updateShippingAddress(ShippingAddressBean shippingAddressBean){
		
		// SQLコマンド生成
		StringBuilder sb = new StringBuilder();
		sb.append("UPDATE " );
		sb.append(	"shipping_addresses "				);
		sb.append(	"SET "								);
		sb.append(		"postal_code = ? "				);
		sb.append(		"address = ? "					);
		sb.append(		"addressee = ? "				);
		sb.append("WHERE "								);
		sb.append(	"shipping_addresses.user_id = ?"	);
		final String UPDATE_ADDRESS_SQL = sb.toString();
		
		// ？の引数に渡す値
		ArrayList<Object> param = new ArrayList<Object>();
		param.add(shippingAddressBean.getPostalCode());
		param.add(shippingAddressBean.getAddress());
		param.add(shippingAddressBean.getAddressee());
		param.add(shippingAddressBean.getUserId());
		
		try (Connection connection = DatabaseConnection.getConnection()) {
			try {
				GeneralDao.executeUpdate(connection, UPDATE_ADDRESS_SQL, param);
				connection.commit();
			} catch (Exception e) {
				if(!connection.isClosed()) {
					connection.rollback();
					e.printStackTrace();
				}
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
}
