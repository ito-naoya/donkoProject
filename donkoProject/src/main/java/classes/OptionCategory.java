package classes;

import java.util.ArrayList;

import bean.ItemCategoryBean;
import bean.OptionCategoryBean;
import model.optionCategories.optionCategoriesSelect.SelectOptionCategoryList;
import model.optionCategories.optionCategoriesSelect.SelectOptionCategoryListByCategory;

public class OptionCategory {

	//全てのオプションを取得する
	public static ArrayList<OptionCategoryBean> getOptionCategoryList() {
		return SelectOptionCategoryList.selectOptionCategoryList();
	};

	//カテゴリ名でオプションを取得する
	public static ArrayList<OptionCategoryBean> getOptionCategoryListByCategory(ItemCategoryBean itemCategoryBean) {
		return SelectOptionCategoryListByCategory.selectOptionCategoryListByCategory(itemCategoryBean);
	}

}
