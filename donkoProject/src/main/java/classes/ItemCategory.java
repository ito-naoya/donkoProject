package classes;

import java.util.ArrayList;

import bean.ItemCategoryBean;
import model.itemCategories.itemCategoriesSelect.SelectItemCategoryList;

public class ItemCategory {
	
	//全ての商品カテゴリを取得する
	public static ArrayList<ItemCategoryBean> getItemCategoryList(){
		return SelectItemCategoryList.selectItemCategoryList();
	};

}
