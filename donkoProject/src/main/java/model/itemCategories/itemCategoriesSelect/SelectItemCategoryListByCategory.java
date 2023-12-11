package model.itemCategories.itemCategoriesSelect;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import bean.ItemBean;
import bean.ItemCategoryBean;
import dao.DatabaseConnection;
import dao.GeneralDao;

public class SelectItemCategoryListByCategory {

	//カテゴリ指定してオプションを取得する
	public static ArrayList<ItemCategoryBean> selectItemCategoryListByCategory(ItemBean itemBean) {
		//カテゴリ指定してカテゴリ名とオプション名を抽出するSQL（衣服：色、衣服；衣類サイズ）
		StringBuilder sb = new StringBuilder();

		sb.append("SELECT "						);
		sb.append(	"item_category_name, "		);
		sb.append(	"option_category_name " 	);
		sb.append("FROM "						);
		sb.append(	"item_categories " 			);
		sb.append("WHERE "						);
		sb.append(	"item_category_name = ?;"	);

		String sql = sb.toString();

		//引数のItemBeanからカテゴリを取得
		String categoryName = itemBean.getItemCategoryName();


		ArrayList<ItemCategoryBean> itemCategories = new ArrayList<>();
		List<Object> params = Arrays.asList(categoryName);
		try (Connection conn = DatabaseConnection.getConnection()){
			try(ResultSet rs = GeneralDao.executeQuery(conn, sql, params)) {
	
	
					//ItemCategoryBeanに挿入
					while(rs.next()) {
						ItemCategoryBean itemCategory = new ItemCategoryBean();
				String category = rs.getString("item_category_name");
				itemCategory.setItemCategoryName(category);
				String option = rs.getString("option_category_name");
				itemCategory.setOptionCategoryName(option);
	
				itemCategories.add(itemCategory);
			}

				//ItemCategoryBeanを並び替えて、オプションが2つある場合に、必ず1つ目のオプションに"色"を含むものが入る様にする
				if (itemCategories.size() > 1) {
					for (int i = 0; i < itemCategories.size(); i++) {
						ItemCategoryBean itemCategory = itemCategories.get(i);
						if (itemCategory.getItemCategoryName().equals("色")) {
							itemCategories.remove(i);
							itemCategories.add(0, itemCategory);
							break;
						}
					}
				}

		} catch (SQLException e) {
			if(!conn.isClosed()) {
				conn.rollback();
					e.printStackTrace();
					return null;
			}
		}

		} catch (SQLException | ClassNotFoundException e) {
		e.printStackTrace();
		return null;
		}

		//カテゴリーで紐づいたオプションのリストを返す
		return itemCategories;
	};

}
