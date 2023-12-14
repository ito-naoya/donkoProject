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
		ArrayList<ArrayList<OptionCategoryBean>> optionCategoryValueListAll = new ArrayList<ArrayList<OptionCategoryBean>>();
		// カテゴリー名を指定 : 付随した全てのオプションカテゴリー名を取得
		ArrayList<ItemCategoryBean> optionCategoryNameList = ItemCategory.getItemOptionCategoryNameListByCategory(itemBean);
		
		// オプションカテゴリー名を指定 : 付随した全てのオプションカテゴリー値を取得
		for (int i = 0; i < optionCategoryNameList.size(); i++) {
			ItemCategoryBean optionCategoryName = optionCategoryNameList.get(i);
			ArrayList<OptionCategoryBean> optionCategoryValueList = getOptionCategoryListByCategory(optionCategoryName);
			optionCategoryValueListAll.add(optionCategoryValueList);
		}
		
		return optionCategoryValueListAll;
	}
}
