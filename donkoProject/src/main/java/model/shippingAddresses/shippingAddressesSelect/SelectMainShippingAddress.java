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
		
		//メインの配送先を取得するSQL
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT ");
		sb.append(		"shipping_address_id, ");
		sb.append(		"postal_code, ");
		sb.append(		"address, ");
		sb.append(		"addressee ");
		sb.append("FROM ");
		sb.append		("shipping_addresses ");
		sb.append("WHERE ");
		//パラメータをここで使う
		sb.append(		"user_id = ? ");
		sb.append("AND ");
		sb.append(		"main_shipping_address = 1");
		//sqlを文字列化
		final String SELECT_MAIN_ADDRESS_SQL = sb.toString();
		
		//ログインしているユーザーのIDをリストに追加
		ArrayList<Object> params = new ArrayList<Object>();
		params.add(customerUser.getUserId());
		
		//配送先情報を保持するshippingAddressBeanをnullで初期化
		ShippingAddressBean sa = null;
		
		//データベース接続
		try(Connection conn = DatabaseConnection.getConnection();){
			try(ResultSet rs = GeneralDao.executeQuery(conn, SELECT_MAIN_ADDRESS_SQL, params)) {
				
				while(rs.next()) {
					//配送先情報を保持するshippingAddressBeanをnew
					sa = new ShippingAddressBean();
					sa.setShippingAddressId(rs.getInt("shipping_address_id"));
					sa.setPostalCode(rs.getString("postal_code"));
					sa.setAddress(rs.getString("address"));
					sa.setAddressee(rs.getString("addressee"));
				}
				if(sa == null) throw new SQLException();
				
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
