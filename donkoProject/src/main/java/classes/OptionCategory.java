package classes;

import java.util.ArrayList;

import bean.ItemBean;
import bean.ItemCategoryBean;
import bean.OptionCategoryBean;
import model.optionCategories.optionCategoriesSelect.SelectOptionCategoryList;
import model.optionCategories.optionCategoriesSelect.SelectOptionCategoryListByCategory;

public class OptionCategory {

	//全てのオプションを取得する
	public static ArrayList<OptionCategoryBean> getOptionCategoryList() {
		return SelectOptionCategoryList.selectOptionCategoryList();
	};

	//オプションのカテゴリ名でオプションを取得する
	public static ArrayList<OptionCategoryBean> getOptionCategoryListByCategory(ItemCategoryBean itemCategoryBean) {
		return SelectOptionCategoryListByCategory.selectOptionCategoryListByCategory(itemCategoryBean);
	}
	
	//オプションのカテゴリ名で全てのオプションを取得する
	public static ArrayList<ArrayList<OptionCategoryBean>> getOptionCategoryListAllByCategory(ItemBean itemBean) {
		ArrayList<ArrayList<OptionCategoryBean>> ONValueListALL = new ArrayList<ArrayList<OptionCategoryBean>>();
		ArrayList<ItemCategoryBean> CNList = ItemCategory.getItemOptionCategoryNameListByCategory(itemBean);
		for (int i = 0; i < CNList.size(); i++) {
			ItemCategoryBean OCName = CNList.get(i);
			ArrayList<OptionCategoryBean> ONValueList = getOptionCategoryListByCategory(OCName);
			ONValueListALL.add(ONValueList);
		}
		return ONValueListALL;
	}
}
