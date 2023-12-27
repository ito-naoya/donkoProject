package test.model.items.itemsSelect;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import bean.ItemCategoryBean;
import model.itemCategories.itemCategoriesSelect.SelectItemCategoryList;

class TestselectItemCategoryList {

//	//成功テスト
//	@Test
//	void testSuccess() {
//		ArrayList<ItemCategoryBean> result = SelectItemCategoryList.selectItemCategoryList();
//		assertTrue(result instanceof ArrayList<ItemCategoryBean>);
//	}

	//失敗テスト
	@Test
	void testException() {
		ArrayList<ItemCategoryBean> result = SelectItemCategoryList.selectItemCategoryList();
		assertEquals(null, result);
	}
}
