package classes;

import java.util.ArrayList;

import bean.ItemBean;
import bean.ItemCategoryBean;
import model.itemCategories.itemCategoriesSelect.SelectItemCategoryList;
import model.itemCategories.itemCategoriesSelect.SelectOptionCategoryNameListByCategory;

public class ItemCategory {

	//全ての商品カテゴリを取得する
	public static ArrayList<ItemCategoryBean> getItemCategoryList() {
		return SelectItemCategoryList.selectItemCategoryList();
	};

	//カテゴリ名からオプションカテゴリ名を取得
	public static ArrayList<ItemCategoryBean> getItemOptionCategoryNameListByCategory(ItemBean itemBean) {
		return SelectOptionCategoryNameListByCategory.selectOptionCategoryNameListByCategory(itemBean);
	};

}
