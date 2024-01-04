package model.optionCategories.optionCategoriesSelect;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import bean.OptionCategoryBean;
import dao.DatabaseConnection;
import dao.GeneralDao;

public class SelectOptionCategoryList {

	//すべてのオプションを取得する
	public static ArrayList<OptionCategoryBean> selectOptionCategoryList(){
		StringBuilder sb = new StringBuilder();

		sb.append("SELECT "							);
		sb.append(	"option_category_name "	);
		sb.append("FROM "							);
		sb.append(	"option_category_names;"			);

		final String SELECT_OPTION_CATEFORY_SQL = sb.toString();

		ArrayList<OptionCategoryBean> optionCategories = new ArrayList<>();
		List<Object> params = Arrays.asList();
		try (Connection conn = DatabaseConnection.getConnection()){
			try(ResultSet rs = GeneralDao.executeQuery(conn, SELECT_OPTION_CATEFORY_SQL, params)) {

				if (!rs.isBeforeFirst())throw new SQLException();

					//OptionCategoryBeanに挿入
					while(rs.next()) {
						OptionCategoryBean optionCategory = new OptionCategoryBean();

						String optionCategoryName = rs.getString("option_category_name");
						optionCategory.setOptionCategoryName(optionCategoryName);

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
