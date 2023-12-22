package model.shippingAddresses.shippingAddressesUpdate;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import bean.ShippingAddressBean;
import dao.DatabaseConnection;
import dao.GeneralDao;

public class UpdateMainShippingAddress {
	
	//メイン配送先を更新する
	public static Boolean updateMainShippingAddress(ShippingAddressBean shippingAddressBean){
		// SQLコマンド生成(メイン設定削除)
		StringBuilder sbDelete = new StringBuilder();
		sbDelete.append("UPDATE " );
		sbDelete.append(	"shipping_addresses "							);
		sbDelete.append(	"SET "											);
		sbDelete.append(		"main_shipping_address = 0 "				);
		sbDelete.append("WHERE "											);
		sbDelete.append(	"shipping_addresses.user_id = ? "				);
		sbDelete.append(	"AND "											);
		sbDelete.append(	"shipping_addresses.main_shipping_address = 1 "	);
		final String DELETE_MAIN_ADDRESS_SQL = sbDelete.toString();
		
		// ？の引数に渡す値(メイン設定削除)
		ArrayList<Object> paramDelete = new ArrayList<Object>();
		paramDelete.add(shippingAddressBean.getUserId());
		
		// SQLコマンド生成(メイン設定)
		StringBuilder sbUpdate = new StringBuilder();
		sbUpdate.append("UPDATE "											);
		sbUpdate.append(	"shipping_addresses "							);
		sbUpdate.append(	"SET "											);
		sbUpdate.append(		"main_shipping_address = 1 "				);
		sbUpdate.append("WHERE "											);
		sbUpdate.append(	"shipping_addresses.user_id = ? "				);
		sbUpdate.append(	"AND "											);
		sbUpdate.append(	"shipping_addresses.shipping_address_id = ? "	);
		final String UPDATE_MAIN_ADDRESS_SQL = sbUpdate.toString();
		
		// ？の引数に渡す値(メイン設定)
		ArrayList<Object> paramUpdate = new ArrayList<Object>();
		paramUpdate.add(shippingAddressBean.getUserId());
		paramUpdate.add(shippingAddressBean.getShippingAddressId());
		
		try (Connection connection = DatabaseConnection.getConnection()) {
			try {
				GeneralDao.executeUpdate(connection, DELETE_MAIN_ADDRESS_SQL, paramDelete);
				GeneralDao.executeUpdate(connection, UPDATE_MAIN_ADDRESS_SQL, paramUpdate);
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
