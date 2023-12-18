package model.shippingAddresses.shippingAddressesDelete;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import bean.ShippingAddressBean;
import dao.DatabaseConnection;
import dao.GeneralDao;

public class DeleteIteShippingAddresses {

	//配送先を削除する
	public static void deleteUpdateShippingAddress(ShippingAddressBean shippingAddressBean){

		StringBuilder sb = new StringBuilder();
			//削除フラグ（item_delete_flg)の0と1を切り替えるSQL
			sb.append("DELETE FROM "									);
			sb.append(		"shipping_addresses "						);
			sb.append("WHERE "											);
			sb.append(		"shipping_address_id= ? "					);
			final String DELETE_ADDRESS_SQL = sb.toString();
			
			//？に値をセットする
			ArrayList<Object> param = new ArrayList<>();
			param.add(shippingAddressBean.getShippingAddressId());
			
			//データベースに接続
			try (Connection conn = DatabaseConnection.getConnection();) {
				try {
					GeneralDao.executeUpdate(conn, DELETE_ADDRESS_SQL, param);
					conn.commit();
				} catch (SQLException e) {
					if (!conn.isClosed()) {
						conn.rollback();
					}
					e.printStackTrace();
				}
			} catch (SQLException | ClassNotFoundException e) {
				e.printStackTrace();
			}

	};
}