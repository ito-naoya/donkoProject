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
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT ");
		sb.append(	"items.item_id, ");
		sb.append(	"items.item_name, ");
		sb.append(	"items.item_category_name, ");
		sb.append(	"items.item_description, ");
		sb.append(	"items.price, ");
		sb.append(	"items.stock, ");
		sb.append(	"items.file_name, ");
		sb.append(	"group_concat(option_categories.option_category_value separator ',')");
		sb.append("FROM ");
		sb.append(	"items ");
		sb.append("INNER JOIN ");
		sb.append(	"`options` ");
		sb.append("ON ");
		sb.append(	"items.item_id = `options`.item_id ");
		sb.append("INNER JOIN ");
		sb.append(	"option_categories ");
		sb.append("ON ");
		sb.append(	"`options`.option_category_name = option_categories.option_category_name ");
		sb.append("AND ");
		sb.append(	"`options`.option_category_increment_id = option_categories.option_category_increment_id ");
		sb.append("WHERE ");
		//パラメータをここで使う
		sb.append(	"items.item_id = ? ");
		sb.append("GROUP BY ");
		sb.append(	"items.item_id");
		//sbを文字列化
		final String SELECT_ITEM_DETAIL_SQL = sb.toString();

		//詳細情報を取得したい商品のIDをリストに追加
		ArrayList<Object> params = new ArrayList<Object>();
		params.add(itemBean.getItemId());

		//商品情報を保持するitemBeanをnullで初期化
		ItemBean ib = null;

		//データベース接続
		try (Connection conn = DatabaseConnection.getConnection();) {
			//商品の詳細情報を取得
			try (ResultSet rs = GeneralDao.executeQuery(conn, SELECT_ITEM_DETAIL_SQL, params);) {

				while (rs.next()) {
					//商品情報を保持するitemBeanをnew
					ib = new ItemBean();
					ib.setItemId(rs.getInt("item_id"));
					ib.setItemCategoryName(rs.getString("item_category_name"));
					ib.setItemName(rs.getString("item_name"));
					ib.setItemDescription(rs.getString("item_description"));
					ib.setItemPrice(rs.getInt("price"));
					ib.setItemStock(rs.getInt("stock"));
					ib.setImageFileName(rs.getString("file_name"));
					ib.setItemFirstOptionValue(rs.getString("group_concat(option_categories.option_category_value separator ',')"));
				}
				
				//取得件数が0件の場合
				if(ib == null) throw new SQLException();

			} catch (SQLException e) {
				if (!conn.isClosed()) {
					//SQL文が一つでも失敗したらロールバックする
					conn.rollback();
				}
				e.printStackTrace();
				return null;
			}
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}
		//取得した商品詳細情報を保持したitemBeanを返す
		return ib;
	}
}
