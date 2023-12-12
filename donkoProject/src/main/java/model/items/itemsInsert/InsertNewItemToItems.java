package model.items.itemsInsert;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import bean.ItemBean;
import dao.DatabaseConnection;
import dao.GeneralDao;

public class InsertNewItemToItems {

    // itemsテーブルに商品を新規登録する
    public static void insertNewItemToItems(ItemBean itemBean, int selectBoxCount) {
        //itemsテーブルに商品を登録
        StringBuilder sb1 = new StringBuilder();
        sb1.append("INSERT INTO items "										);
        sb1.append(		"(item_category_name, item_name, item_description, ");
        sb1.append(		"price, stock, file_name, item_delete_flg) "		);
        sb1.append("VALUES "												);
        sb1.append(		"(?, ?, ?, ?, ?, ?, 0)"								);

        String sql1 = sb1.toString();

        //新規に登録したitem_idを利用(itemテーブルをideでdesc)して、optionsテーブルにオプションの値を登録
        StringBuilder sb2 = new StringBuilder();
        sb2.append("INSERT INTO "														);
        sb2.append(		"options "														);
        sb2.append(		"(item_id, option_category_name, option_category_increment_id) " );
        sb2.append("VALUES ("															);
        sb2.append(		"(SELECT "														);
        sb2.append(			"item_id "													);
        sb2.append(		"FROM "															);
        sb2.append(			"items "													);
        sb2.append(		"ORDER BY "														);
        sb2.append(			"item_id "													);
        sb2.append(		"DESC "															);
        sb2.append(		"LIMIT 1), "													);
        sb2.append(		"?, ?);"														);

        String sql2 = sb2.toString();


        //paramに値を格納
        ArrayList<Object> params1 = new ArrayList<Object>();
        params1.add(itemBean.getItemCategoryName());
        params1.add(itemBean.getItemName());
        params1.add(itemBean.getItemDescription());
        params1.add(itemBean.getItemPrice());
        params1.add(itemBean.getItemStock());
        params1.add(itemBean.getImageFileName());

        //セレクトボックスが1つの時
        ArrayList<Object> params2 = new ArrayList<Object>();
        params2.add(itemBean.getItemFirstOptionName());
        params2.add(itemBean.getItemFirstOptionIncrementId());

        //セレクトボックスが2つの時
        ArrayList<Object> params3 = new ArrayList<Object>();
        if(selectBoxCount == 2) {
            params3.add(itemBean.getItemSecondOptionName());
            params3.add(itemBean.getItemSecondOptionIncrementId());
        }

        try (Connection conn = DatabaseConnection.getConnection()) {
            try {
            	GeneralDao.executeUpdate(conn, sql1, params1);
            	GeneralDao.executeUpdate(conn, sql2, params2);

            	if(selectBoxCount == 2) {
            		GeneralDao.executeUpdate(conn, sql2, params3);
                }

            	conn.commit();  //コミットする
            } catch (SQLException e) {
		    	if(!conn.isClosed()) {
			        conn.rollback();
			        e.printStackTrace();
		    	}
		    }

		} catch (SQLException | ClassNotFoundException e) {
		  e.printStackTrace();
		}
	}
}
