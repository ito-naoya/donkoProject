package model.shippingAddresses.shippingAddressesSelect;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import bean.ShippingAddressBean;
import dao.DatabaseConnection;
import dao.GeneralDao;

public class SelectShippingAddressDetail {
	
	//配送住所の詳細情報を取得する
	public static ShippingAddressBean selectShippingAddressDetail(ShippingAddressBean shippingAddressBean){
		// SQLコマンド生成
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT " );
		sb.append(	"postal_code, "											);
		sb.append(	"address, "												);
		sb.append(	"addressee "											);
		sb.append("FROM "													);
		sb.append(	"shipping_addresses "									);
		sb.append("WHERE "													);
		sb.append(	"shipping_addresses.shipping_address_id = ? "			);
		sb.append(	"AND "													);
		sb.append(	"shipping_addresses.user_id = ?"						);
		String sql = sb.toString();

		// ？の引数に渡す値
		ArrayList<Object> param = new ArrayList<Object>();
		param.add(shippingAddressBean.getShippingAddressId());
		param.add(shippingAddressBean.getUserId());
		
		ShippingAddressBean shippingAddressBeans = null;
		
		try (Connection connection = DatabaseConnection.getConnection()) {
			try (ResultSet results = GeneralDao.executeQuery(connection, sql, param)) {

				while (results.next()) {
					shippingAddressBeans = new ShippingAddressBean();
					shippingAddressBeans.setPostalCode(results.getString("postal_code"));
					shippingAddressBeans.setAddress(results.getString("address"));
					shippingAddressBeans.setAddressee(results.getString("addressee"));
				}
			} catch (Exception e) {
				if(!connection.isClosed()) {
					connection.rollback();
					e.printStackTrace();
					return null;
				}
			}

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			return null;
		}
		return shippingAddressBeans;
		}
}
