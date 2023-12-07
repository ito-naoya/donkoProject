package classes;

import java.util.ArrayList;

import bean.ItemBean;
import bean.OptionCategoryBean;
import model.optionCategories.optionCategoriesSelect.SelectOptionCategoryList;
import model.optionCategories.optionCategoriesSelect.SelectOptionCategoryListByCategory;

public class OptionCategory {
	
	//カテゴリ指定してオプションを取得する
	public static ArrayList<OptionCategoryBean> getOptionListByCategory(ItemBean itemBean){
		return SelectOptionCategoryListByCategory.selectOptionCategoryListByCategory(itemBean);
	};
	
	//全てのオプションを取得する
	public static ArrayList<OptionCategoryBean>getOptionList(){
		return SelectOptionCategoryList.selectOptionCategoryList();
	};

}
