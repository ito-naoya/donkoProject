package model.shippingAddresses.shippingAddressesInsert;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import bean.ShippingAddressBean;
import dao.DatabaseConnection;
import dao.GeneralDao;

public class InsertNewShippingAddress {
	
	//新規配送先を登録する
	public static Boolean insertNewShippingAddress(ShippingAddressBean shippingAddressBean){
		
		// SQLコマンド生成
		StringBuilder sb = new StringBuilder();
		sb.append("INSERT INTO " 								);
		sb.append(	"shipping_addresses ( "						);
		sb.append(		"user_id, "								);
		sb.append(		"postal_code, "							);
		sb.append(		"address, "								);
		sb.append(		"main_shipping_address, "				);
		sb.append(		"addressee"								);
		sb.append(	") "										);
		sb.append("VALUES ( "									);
		// ユーザーID
		sb.append(	"?, "										);
		// 郵便番号
		sb.append(	"?, "										);
		// 住所
		sb.append(	"?, "										);
		// 配送先住所の設定
		sb.append(	"?, "										);
		// 宛名
		sb.append(	"? "										);
		sb.append(	") "										);
		final String INSERT_SHIPPING_ADDRESS_SQL = sb.toString();
		
		// ？の引数に渡す値
		ArrayList<Object> param = new ArrayList<Object>();
		param.add(shippingAddressBean.getUserId());
		param.add(shippingAddressBean.getPostalCode());
		param.add(shippingAddressBean.getAddress());
		param.add(shippingAddressBean.getMainShippingAddress());
		param.add(shippingAddressBean.getAddressee());
		
		try (Connection connection = DatabaseConnection.getConnection()) {
			try {
				GeneralDao.executeUpdate(connection, INSERT_SHIPPING_ADDRESS_SQL, param);
				connection.commit();
			} catch (Exception e) {
				if(!connection.isClosed()) {
					connection.rollback();
					e.printStackTrace();
					return false;
				}
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
