package test.model.options.optionsDelete;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import bean.ItemCategoryBean;
import model.itemCategories.itemCategoriesDelete.DeleteItemCategories;

class TestDeleteItemOptionValue {

	//成功テスト
	@Test
	void testSuccess() {
		ItemCategoryBean category = new ItemCategoryBean();
		category.setItemCategoryName("hoge");
		Integer result = DeleteItemCategories.deleteItemCategories(category);
		assertEquals(1,result);
	}

	//失敗テスト
	@Test
	void testException1() {
		ItemCategoryBean category = new ItemCategoryBean();
		category.setItemCategoryName("衣類");
		Integer result = DeleteItemCategories.deleteItemCategories(category);
		assertEquals(0,result);
	}

//	//失敗テスト
//	@Test
//	void testException2() {
//		ItemCategoryBean category = new ItemCategoryBean();
//		category.setItemCategoryName("衣類");
//		Integer result = DeleteItemCategories.deleteItemCategories(category);
//		assertEquals(null,result);
//	}
}
