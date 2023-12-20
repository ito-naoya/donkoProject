package model.items.itemsSelect;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import bean.ItemBean;
import dao.DatabaseConnection;
import dao.GeneralDao;

public class SelectItemImageListFromItems {

	//商品の画像一覧を取得する
	public static ArrayList<ItemBean> selectItemImageListFromItems(ItemBean itemBean) {
		
		//詳細表示している商品の色違い画像を取得するSQL
		StringBuilder sb = new StringBuilder();	
		sb.append("SELECT ");
		sb.append(		"items.item_id, ");
		sb.append(		"items.file_name ");
		sb.append("FROM ");
		sb.append(		"items ");
		sb.append("INNER JOIN ");
		sb.append(		"item_categories ");
		sb.append("ON ");
		sb.append(		"items.item_category_name = item_categories.item_category_name ");
		sb.append("INNER JOIN ");
		sb.append(		"`options` ");
		sb.append("ON ");
		sb.append(		"items.item_id = options.item_id ");
		sb.append("AND ");
		sb.append(		"item_categories.option_category_name = options.option_category_name ");
		sb.append("AND ");
		sb.append(		"options.option_category_name != '色' ");
		sb.append("WHERE ");
		sb.append(		"item_name = ");
		sb.append(					"(");
		sb.append(						"SELECT ");
		sb.append(							"item_name ");		  	                                                       
		sb.append(						"FROM ");		  	                                                             
		sb.append(							"items ");		 	                                                            
		sb.append(						"WHERE ");		 	                                                            
		//パラメータをここで使う（1/2回目)
		sb.append(							"item_id = ?");		  	                                                     
		sb.append(					") ");		  	                                                     
		sb.append("AND ");
		sb.append(		"option_category_increment_id = ");                                                                   
		sb.append(										"(");                                                                   
		sb.append(											"SELECT ");                                                                   
		sb.append(												"option_category_increment_id ");                                                      
		sb.append(											"FROM ");
		sb.append(												"options ");
		sb.append(											"WHERE ");
		//パラメータをここで使う（2/2回目)
		sb.append(												"item_id = ? ");
		sb.append(											"AND ");
		sb.append(												"option_category_name != '色'"	);
		sb.append(										") ");
		sb.append("AND ");
		sb.append(		"items.item_delete_flg != 1 ");                                                                  
		//SQL文を文字列に変換
		final String SELECT_ITEM_IMAGE_LIST_SQL = sb.toString();
		
		//比較するための商品IDをリストに追加
		ArrayList<Object> params = new ArrayList<Object>();
		params.add(itemBean.getItemId());
		params.add(itemBean.getItemId());

		//取得した画像情報を保持するitemBeanListをnew
		ArrayList<ItemBean> itemBeanList = new ArrayList<ItemBean>();

		//データベース接続
		try (Connection conn = DatabaseConnection.getConnection();) {
			//同じ商品の色違い画像の取得
			try (ResultSet rs = GeneralDao.executeQuery(conn, SELECT_ITEM_IMAGE_LIST_SQL, params);) {
				
				while (rs.next()) {
					//取得した画像情報を保持するitemBeanをnew
					ItemBean ib = new ItemBean();
					ib.setItemId(rs.getInt("item_id"));
					ib.setImageFileName(rs.getString("file_name"));
					//画像情報を保持したitemBeanをitemBeanListに追加する
					itemBeanList.add(ib);
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
		//画像情報を保持したitemBeanListを返す
		return itemBeanList;
	}
}

