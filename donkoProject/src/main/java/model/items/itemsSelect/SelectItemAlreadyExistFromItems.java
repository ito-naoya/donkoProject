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

			// item_idごとに関連するoption_category_increment_idを格納するマップ
		    HashMap<Integer, ArrayList<Integer>> itemToOptionsMap = new HashMap<>();
		    ArrayList<Integer> matchingItemIds = new ArrayList<>();

		    ArrayList<Object> params = new ArrayList<>();
		    params.add(itemBean.getItemName());

		    try (Connection conn = DatabaseConnection.getConnection();){
		         try(ResultSet rs = GeneralDao.executeQuery(conn, SELECT_ITEM_EXIST, params)) {

		        while (rs.next()) {
		            int currentItemId = rs.getInt("item_id");
		            int currentOptionId = rs.getInt("option_category_increment_id");

		            // item_idに対応するリストにoption_category_increment_idを追加
		            itemToOptionsMap.computeIfAbsent(currentItemId, k -> new ArrayList<>()).add(currentOptionId);
		        }

		        if(itemSecondOptionIncrementIds == null) {
		        	// optionが1つの時
	                for (Map.Entry<Integer, ArrayList<Integer>> entry : itemToOptionsMap.entrySet()) {
	                    ArrayList<Integer> options = entry.getValue();

                    	int firstOptionId = options.get(0); // option1
                    	//DBから取得したoptionと今回のoptionを比較
                        if (firstOptionId == itemBean.getItemFirstOptionIncrementId()) {
                            matchingItemIds.add(entry.getKey());
                        }
	                }
		        } else {
		        // optionが2つの時
		        // itemSecondOptionIncrementIds配列をループ処理
	            for (String optionIdStr : itemSecondOptionIncrementIds) {
	                int optionId = Integer.parseInt(optionIdStr);

	                // 各item_idのリストをチェック
	                for (Map.Entry<Integer, ArrayList<Integer>> entry : itemToOptionsMap.entrySet()) {
	                    ArrayList<Integer> options = entry.getValue();

                        int firstOptionId = options.get(0); // option1
                        int secondOptionId = options.get(1); // option2

                        //DBから取得したoptionと今回のoptionを比較
                        if (firstOptionId == itemBean.getItemFirstOptionIncrementId() && secondOptionId == optionId) {
                            matchingItemIds.add(entry.getKey());
                        }
	                }
	            }
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
		return matchingItemIds;
	}
}
