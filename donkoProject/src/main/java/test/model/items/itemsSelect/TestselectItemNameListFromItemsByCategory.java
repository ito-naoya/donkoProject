package test.model.items.itemsSelect;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import bean.ItemBean;
import model.items.itemsSelect.SelectItemNameListFromItemsByCategory;

class TestselectItemNameListFromItemsByCategory {

	//成功テスト
	@Test
	void testSuccess() {
		ItemBean item  = new ItemBean();
		item.setItemCategoryName("衣類");
		ArrayList<ItemBean> result = SelectItemNameListFromItemsByCategory.selectItemNameListFromItemsByCategory(item);
		assertTrue(result instanceof ArrayList<ItemBean>);
	}

	//失敗テスト
	@Test
	void testException() {
		ItemBean item  = new ItemBean();
		item.setItemCategoryName("");
		ArrayList<ItemBean> result = SelectItemNameListFromItemsByCategory.selectItemNameListFromItemsByCategory(item);
		assertEquals(null, result);
	}
}
