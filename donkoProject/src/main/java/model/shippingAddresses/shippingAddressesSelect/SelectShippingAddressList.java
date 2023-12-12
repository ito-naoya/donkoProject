package model.shippingAddresses.shippingAddressesSelect;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import bean.ShippingAddressBean;
import classes.user.CustomerUser;
import dao.DatabaseConnection;
import dao.GeneralDao;

public class SelectShippingAddressList {
	
	//配送住所の一覧を取得する
	public static ArrayList<ShippingAddressBean> selectShippingAddressList(CustomerUser CustomerUser){
		// SQLコマンド生成
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT " );
		sb.append(	"shipping_address_id, "						);
		sb.append(	"postal_code, "								);
		sb.append(	"address, "									);
		sb.append(	"main_shipping_address, "					);
		sb.append(	"addressee "								);
		sb.append("FROM "										);
		sb.append(	"shipping_addresses "						);
		sb.append("WHERE "										);
		sb.append(	"shipping_addresses.user_id = ?"			);
		String sql = sb.toString();
		
		// ？の引数に渡す値
		ArrayList<Object> param = new ArrayList<Object>();
		param.add(CustomerUser.getUserLoginId());
		
		ArrayList<ShippingAddressBean> shippingAddressList = new ArrayList<ShippingAddressBean>();
		ShippingAddressBean shippingAddressBean;
		
		try (Connection connection = DatabaseConnection.getConnection()) {
			try (ResultSet results = GeneralDao.executeQuery(connection, sql, param)) {
				
				while (results.next()) {
					shippingAddressBean = new ShippingAddressBean();
					shippingAddressBean.setShippingAddressId(results.getInt("shipping_address_id"));
					shippingAddressBean.setPostalCode(results.getString("postal_code"));
					shippingAddressBean.setAddress(results.getString("address"));
					shippingAddressBean.setMainShippingAddress(results.getInt("main_shipping_address"));
					shippingAddressBean.setAddressee(results.getString("addressee"));
					shippingAddressList.add(shippingAddressBean);
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
		return shippingAddressList;
	}
}
