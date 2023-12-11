package model.optionCategories.optionCategoriesSelect;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import bean.ItemCategoryBean;
import bean.OptionCategoryBean;
import dao.DatabaseConnection;
import dao.GeneralDao;

public class SelectOptionCategoryListByCategory {

	//カテゴリ名でオプション詳細を取得する
	public static ArrayList<OptionCategoryBean> selectOptionCategoryListByCategory(ItemCategoryBean itemCategoryBean) {
		//カテゴリ名でオプション詳細を取得するSQL（?=色：[1,緑][2,白][3,黒])
		StringBuilder sb = new StringBuilder();

		sb.append("SELECT "							);
		sb.append(	"option_category_increment_id,"	);
		sb.append(	"option_category_value " 		);
		sb.append("FROM "							);
		sb.append(	"option_categories "			);
		sb.append("WHERE "							);
		sb.append(	"option_category_name = ?;"	);

		String sql = sb.toString();

		//引数のItemBeanからカテゴリを取得
		String categoryName = itemCategoryBean.getOptionCategoryName();

		ArrayList<OptionCategoryBean> optionCategories = new ArrayList<>();
		List<Object> params = Arrays.asList(categoryName);
		try (Connection conn = DatabaseConnection.getConnection()){
			try(ResultSet rs = GeneralDao.executeQuery(conn, sql, params)) {

					//OptionCategoryBeanに挿入
					while(rs.next()) {
						OptionCategoryBean optionCategory = new OptionCategoryBean();
				int optionId = rs.getInt("option_category_increment_id");
				optionCategory.setOptionCategoryId(optionId);
				String optionName = rs.getString("option_category_value");
				optionCategory.setOptionCategoryValue(optionName);

				optionCategories.add(optionCategory);
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
		return optionCategories;
	}
}