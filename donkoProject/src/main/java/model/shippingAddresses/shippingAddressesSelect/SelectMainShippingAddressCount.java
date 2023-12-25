package model.shippingAddresses.shippingAddressesSelect;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import bean.ShippingAddressBean;
import classes.user.CustomerUser;
import dao.DatabaseConnection;
import dao.GeneralDao;

public class SelectMainShippingAddressCount {
	
	//メインの配送先を取得する
	public static Boolean selectMainShippingAddressCounts(CustomerUser customerUser){
		
		//メインの配送先を取得するSQL
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT "								);
		sb.append(		"COUNT(main_shipping_address) "	);
		sb.append(		"AS 'main_status' "				);
		sb.append("FROM "								);
		sb.append(		"shipping_addresses "			);
		sb.append("WHERE "								);
		sb.append(		"user_id = ? "					);
		sb.append("AND "								);
		sb.append(		"main_shipping_address = 1"		);
		//sqlを文字列化
		final String SELECT_MAINADDRESS_SQL = sb.toString();
		
		//ログインしているユーザーのIDをリストに追加
		ArrayList<Object> params = new ArrayList<Object>();
		params.add(customerUser.getUserId());
		
		//データベース接続
		try(Connection connection = DatabaseConnection.getConnection();){
			try(ResultSet result = GeneralDao.executeQuery(connection, SELECT_MAINADDRESS_SQL, params)) {
				
				while(result.next()) {
					ShippingAddressBean shippingAddressBean = new ShippingAddressBean();
					shippingAddressBean.setmainShippingAddressCount(result.getInt("main_status"));
				}
				
			}catch(SQLException e) {
				if(!connection.isClosed()) {
					connection.rollback();
				}
				e.printStackTrace();
				return false;
			}
		}catch(SQLException | ClassNotFoundException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	};
}
