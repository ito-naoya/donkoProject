package model.items.itemsSelect;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import bean.ItemBean;
import dao.DatabaseConnection;
import dao.GeneralDao;

public class SelectItemAllDetailFromItems {

	public static ItemBean selectItemAllDetailFromItems(ItemBean itemBean) {

		//商品のオプションの詳細を取得するSQL
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT ");
		sql.append(		"group_concat(option_categories.option_category_value separator ',') ");
		sql.append("FROM ");
		sql.append(		"items ");
		sql.append("INNER JOIN ");
		sql.append(		"`options` ");
		sql.append("ON ");
		sql.append(		"items.item_id = `options`.item_id ");
		sql.append("INNER JOIN ");
		sql.append(		"option_categories ");
		sql.append("ON ");
		sql.append(		"options.option_category_name  = option_categories.option_category_name ");
		sql.append("AND ");
		sql.append(		"options.option_category_increment_id  = option_categories.option_category_increment_id ");
		sql.append("WHERE ");
		//パラメータをここで使う
		sql.append(		"items.item_id = ?");
		//sqlを文字列化
		final String SELECT_ITEMDETAILOPTION_SQL = sql.toString();

		//詳細オプション情報を取得したい商品のIDをリストに追加
		ArrayList<Object> params = new ArrayList<Object>();
		params.add(itemBean.getItemId());

		//オプション情報を保持するitembeanをnullで初期化
		ItemBean ib = null;

		//データベース接続
		try(Connection conn = DatabaseConnection.getConnection();){
			//商品詳細オプションを取得する
			try(ResultSet rs = GeneralDao.executeQuery(conn, SELECT_ITEMDETAILOPTION_SQL, params)){

				while(rs.next()) {
					//商品詳細オプションを保持するitemBeanをnew
					ib = new ItemBean();
					ib.setItemFirstOptionValue(rs.getString("group_concat(option_categories.option_category_value separator ',')"));
				}

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
		//商品詳細オプションを保持したitemBeanを返す
		return ib;
	}
}
