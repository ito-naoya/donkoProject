package model.items.itemsUpdate;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

import dao.DatabaseConnection;
import dao.GeneralDao;

public class UpdateItemDeleteFromItems {

	//商品を削除する（論理削除）
	public static Boolean deleteItemFromItems(String[] itemStatus){

		StringBuilder sb = new StringBuilder();
			//削除フラグ（item_delete_flg)の0と1を切り替えるSQL
			sb.append("UPDATE "											);
			sb.append(		"items "									);
			sb.append("SET "											);
			sb.append(		"item_delete_flg = "						);
			sb.append(		"CASE "										);
			sb.append(		"WHEN "										);
			sb.append(			"item_delete_flg = 1 "  				);
			sb.append(		"THEN 0 "									);
			sb.append(		"ELSE 1  "									);
			sb.append(		"END "										);
			sb.append("WHERE "											);
			sb.append(		"item_id "									);
			sb.append(		"IN (" + questionNum(itemStatus) + ")"	);

			// SQLを文字列化
			final String UPDATE_ITEM_DELETE_FLG_SQL = sb.toString();

			//？に入れる文字列を、item_idの数だけ挿入する
			ArrayList<Object> param = new ArrayList<>(Arrays.asList(itemStatus));

			//データベースに接続
			try (Connection conn = DatabaseConnection.getConnection();) {
				try {
					//チェックが入っているitem_idの削除フラグを全て切り替える
					int updatedRows = GeneralDao.executeUpdate(conn, UPDATE_ITEM_DELETE_FLG_SQL, param);

					//更新件数が0件の場合
					if(updatedRows == 0) throw new SQLException();

					//sqlをコミット
					conn.commit();
				} catch (SQLException e) {
					if (!conn.isClosed()) {
						//一つでも処理が失敗したらロールバック
						conn.rollback();
					}
					e.printStackTrace();
					return false;
				}
			} catch (SQLException | ClassNotFoundException e) {
				e.printStackTrace();
				return false;
			}
			return true;
	};
		// SQL文の?を検索キーワードの数だけ用意する
		private static String questionNum(String[] itemStatus) {
		    if (itemStatus.length == 0) {
		        // 何も返さない
		        return "";
		    }
		    String str = "?";
		    for (int index = 1; index < itemStatus.length; index++) {
		        str += ",?";
		    }
		    return str;
		}
}