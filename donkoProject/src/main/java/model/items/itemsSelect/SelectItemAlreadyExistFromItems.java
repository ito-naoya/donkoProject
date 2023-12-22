package model.items.itemsSelect;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import bean.ItemBean;
import dao.DatabaseConnection;
import dao.GeneralDao;

public class SelectItemAlreadyExistFromItems {
	public static ArrayList<Integer> selectItemAlreadyExistFromItems(ItemBean itemBean, String[] itemSecondOptionIncrementIds) {
	//登録したい商品が既にDBじょうに存在するかどうかを確認する。
			StringBuilder sb = new StringBuilder();
			sb.append("SELECT "																	);
			sb.append(		"i.item_id,o.option_category_name, o.option_category_increment_id "	);
			sb.append("FROM "																	);
			sb.append(		"items i "															);
			sb.append("INNER JOIN "																);
			sb.append(		"options o "														);
			sb.append("ON "																		);
			sb.append(		"i.item_id = o.item_id "											);
			sb.append("WHERE "																	);
			sb.append(		"i.item_name = ? "													);
			sb.append("ORDER BY "																);
			sb.append(		"item_id, "															);
			sb.append("CASE "																	);
			sb.append(		"WHEN o.option_category_name = '色' "								);
			sb.append(		"THEN 1 ELSE 2 END;"												);

			final String SELECT_ITEM_EXIST = sb.toString();

			//比較用リストをセット
	        HashMap<Integer, Integer> tempMap = new HashMap<>();
	        int firstIncrementId = itemBean.getItemFirstOptionIncrementId();
	        int secondIncrementId = itemBean.getItemSecondOptionIncrementId();

	        //重複してしまったitem_idを格納
	        ArrayList<Integer> matchingItemIds = new ArrayList<>();

	        ArrayList<Object> params = new ArrayList<>();
	        params.add(itemBean.getItemName());

	        try (Connection conn = DatabaseConnection.getConnection();
	             ResultSet rs = GeneralDao.executeQuery(conn, SELECT_ITEM_EXIST, params)) {

	            while (rs.next()) {
	                int currentItemId = rs.getInt("item_id");
	                int currentOptionId = rs.getInt("option_category_increment_id");

	                // 現在のitem_idに関連するoptionIdをtempMapに保存
	                tempMap.putIfAbsent(currentItemId, currentOptionId);
	            }

	            // itemSecondOptionIncrementIds配列をループ処理
	            for (String optionIdStr : itemSecondOptionIncrementIds) {
	                int optionId = Integer.parseInt(optionIdStr); // 文字列を整数に変換

	                // tempMapでチェック
	                for (Map.Entry<Integer, Integer> entry : tempMap.entrySet()) {
	                    if (entry.getValue() == firstIncrementId && optionId == secondIncrementId) {
	                        matchingItemIds.add(entry.getKey());
	                    }
	                }
	            }
	        } catch (SQLException | ClassNotFoundException e) {
	            e.printStackTrace();
	            return null;
	        }

	        return matchingItemIds;
	    }
}