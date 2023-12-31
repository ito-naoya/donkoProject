package model.items.itemsUpdate;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import bean.ItemBean;
import dao.DatabaseConnection;
import dao.GeneralDao;

public class UpdateItemInfoInItems {

	//商品の情報を更新する
	public static Boolean updateItemInfoInItems(ItemBean itemBean, int selectBoxCount){
		//itemsテーブルの商品を更新
        StringBuilder sb1 = new StringBuilder();
        sb1.append("UPDATE items "														);
        sb1.append("SET "																);
        sb1.append(		"item_category_name =?, item_name = ?, item_description = ?, "	);
        sb1.append(		"price = ?, stock = ?, file_name = ? "							);
        sb1.append("WHERE "																);
        sb1.append("item_id = ?;"														);

        String sql1 = sb1.toString();

        //optionテーブルのオプションを更新
        StringBuilder sb2 = new StringBuilder();
        sb2.append("UPDATE ");
        sb2.append(		"options ");
        sb2.append("SET ");
        sb2.append(		"option_category_increment_id = ? ");
        sb2.append("WHERE ");
        sb2.append(		"option_category_name =? ");
        sb2.append("AND ");
        sb2.append(		"item_id = ?;");

        String sql2 = sb2.toString();

        //paramに値を格納
        ArrayList<Object> params1 = new ArrayList<Object>();
        params1.add(itemBean.getItemCategoryName());
        params1.add(itemBean.getItemName());
        params1.add(itemBean.getItemDescription());
        params1.add(itemBean.getItemPrice());
        params1.add(itemBean.getItemStock());
        params1.add(itemBean.getImageFileName());
        params1.add(itemBean.getItemId());

        //セレクトボックスが1つの時
        ArrayList<Object> params2 = new ArrayList<Object>();
        params2.add(itemBean.getItemFirstOptionIncrementId());
        params2.add(itemBean.getItemFirstOptionName());
        params2.add(itemBean.getItemId());

        //セレクトボックスが2つの時
        ArrayList<Object> params3 = new ArrayList<Object>();
        if(selectBoxCount == 2) {
        	params3.add(itemBean.getItemSecondOptionIncrementId());
            params3.add(itemBean.getItemSecondOptionName());
            params3.add(itemBean.getItemId());
        }
        int totalUpdatedRows = 0;
        try (Connection conn = DatabaseConnection.getConnection()) {
            try {
            	// itemsテーブルを更新
            	totalUpdatedRows += GeneralDao.executeUpdate(conn, sql1, params1);
            	totalUpdatedRows += GeneralDao.executeUpdate(conn, sql2, params2);

                // optionが2つある場合の重複チェックと更新
                if (selectBoxCount == 2) {
                	totalUpdatedRows += GeneralDao.executeUpdate(conn, sql2, params3);
                }

              //更新件数が0件の場合
				if(totalUpdatedRows < 2) throw new SQLException();
            	conn.commit();  //コミットする
            } catch (SQLException e) {
		    	if(!conn.isClosed()) {
			        conn.rollback();
			        e.printStackTrace();
			        return false;
		    	}
		    }

		} catch (SQLException | ClassNotFoundException e) {
		  e.printStackTrace();
		  return false;
		}
        return true;
	};

}
