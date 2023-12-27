package model.itemCategories.itemCategoriesSelect;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import bean.ItemCategoryBean;
import dao.DatabaseConnection;
import dao.GeneralDao;

public class SelectItemCategoryList {

	//全ての商品カテゴリを取得する
	public static ArrayList<ItemCategoryBean> selectItemCategoryList() {
			//全ての商品カテゴリの一覧を取得するSQL[衣服]、[靴]、[本]・・・）
			StringBuilder sb = new StringBuilder();

				sb.append("SELECT DISTINCT "			);
				sb.append(	"item_category_name " 		);
				sb.append("FROM "						);
				sb.append(	"item_categories; "			);

				final String SQL = sb.toString();

				ArrayList<ItemCategoryBean> itemCategories = new ArrayList<>();
				List<Object> params = Arrays.asList();
				//SQL接続
				try (Connection conn = DatabaseConnection.getConnection()){
					try(ResultSet rs = GeneralDao.executeQuery(conn, SQL, params)) {

							//ItemCategoryBeanに挿入
							while(rs.next()) {
								ItemCategoryBean itemCategory = new ItemCategoryBean();
								String category = rs.getString("item_category_name");
								itemCategory.setItemCategoryName(category);

								itemCategories.add(itemCategory);
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
	}
}
