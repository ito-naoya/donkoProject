package test.model.itemCategories.itemCategoriesInsert;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import bean.ItemCategoryBean;
import model.itemCategories.itemCategoriesInsert.InsertItemCategories;

class TestInsertItemCategories {

	//成功テスト
	@Test
	void testSuccess() {
		ItemCategoryBean category = new ItemCategoryBean();
		String[] selectedOptions = new String[2];
		category.setItemCategoryName("hoge");
		selectedOptions[0] = "fuga";
		Integer result = InsertItemCategories.insertItemCategories(category,selectedOptions);
		assertEquals(1,result);
	}

	//失敗テスト(値の重複)
	@Test
	void testException1() {
		ItemCategoryBean category = new ItemCategoryBean();
		String[] selectedOptions = new String[2];
		category.setItemCategoryName("衣類");
		selectedOptions[0] = "fuga";
		Integer result = InsertItemCategories.insertItemCategories(category,selectedOptions);
		assertEquals(0,result);
	}
//
//	//失敗テスト(sb1のインジェクション)
//	@Test
//	void testException2() {
//		ItemCategoryBean category = new ItemCategoryBean();
//		String[] selectedOptions = new String[2];
//		category.setItemCategoryName("hoge");
//		selectedOptions[0] = "fuga";
//		Integer result = InsertItemCategories.insertItemCategories(category,selectedOptions);
//		assertEquals(null,result);
//	}
}
