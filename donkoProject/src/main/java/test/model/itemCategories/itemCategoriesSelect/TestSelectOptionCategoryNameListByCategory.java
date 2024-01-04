package test.model.itemCategories.itemCategoriesSelect;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import bean.ItemBean;
import bean.ItemCategoryBean;
import model.itemCategories.itemCategoriesSelect.SelectOptionCategoryNameListByCategory;

class TestSelectOptionCategoryNameListByCategory {

	//成功テスト
	@Test
	void testSuccess() {
		ItemBean item  = new ItemBean();
		item.setItemCategoryName("衣類");
		ArrayList<ItemCategoryBean> result = SelectOptionCategoryNameListByCategory.selectOptionCategoryNameListByCategory(item);
		assertTrue(result instanceof ArrayList<ItemCategoryBean>);
	}

//	//失敗テスト
//	@Test
//	void testException() {
//		ItemBean item  = new ItemBean();
//		item.setItemCategoryName("");
//		ArrayList<ItemCategoryBean> result = SelectOptionCategoryNameListByCategory.selectOptionCategoryNameListByCategory(item);
//		assertEquals(null, result);
//	}
}
