package model.items.itemsSelect;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import bean.ItemBean;
import dao.DatabaseConnection;
import dao.GeneralDao;

public class SelectItemDetailFromItems {

	//商品の詳細を取得する
	public static ItemBean selectItemDetailFromItems(ItemBean itemBean) {

		//商品の詳細情報を取得するSQL
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT ");
		sql.append(		"item_id, ");
		sql.append(		"item_category_name, ");
		sql.append(		"item_name, ");
		sql.append(		"item_description, ");
		sql.append(		"price, ");
		sql.append(		"stock, ");
		sql.append(		"file_name ");
		sql.append("FROM ");
		sql.append(		"items ");
		sql.append("WHERE ");
		sql.append(		"item_id = ?");
		final String SELECT_ITEMDETAIL_SQL = sql.toString();

		ArrayList<Object> params = new ArrayList<Object>();
		params.add(itemBean.getItemId());

		ItemBean ib = null;

		try (Connection conn = DatabaseConnection.getConnection();) {
			try (ResultSet rs = GeneralDao.executeQuery(conn, SELECT_ITEMDETAIL_SQL, params);) {

				while (rs.next()) {
					ib = new ItemBean();
					ib.setItemId(rs.getInt("item_id"));
					ib.setItemCategoryName(rs.getString("item_category_name"));
					ib.setItemName(rs.getString("item_name"));
					ib.setItemDescription(rs.getString("item_description"));
					ib.setItemPrice(rs.getInt("price"));
					ib.setItemStock(rs.getInt("stock"));
					ib.setImageFileName(rs.getString("file_name"));
				}

			} catch (SQLException e) {
				if (!conn.isClosed()) {
					//SQL文が一つでも失敗したらロールバックする
					conn.rollback();
					e.printStackTrace();
					return null;
				}
			}
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}
		return ib;
	}
}
