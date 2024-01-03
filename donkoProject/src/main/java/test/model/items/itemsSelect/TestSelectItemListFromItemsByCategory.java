package test.model.items.itemsSelect;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import bean.ItemBean;
import model.items.itemsSelect.SelectItemListFromItemsByCategory;

class TestSelectItemListFromItemsByCategory {
	
	// 成功テスト
	@Test
	void testSuccess() {
		ItemBean ib = new ItemBean();
		ib.setItemCategoryName("衣類");
		ArrayList<ItemBean> result = SelectItemListFromItemsByCategory.selectItemListFromItemsByCategory(ib);
		assertTrue(result instanceof ArrayList<ItemBean>);
	}
	
	// 失敗テスト
	@Test
	void testException() {
		ItemBean ib = new ItemBean();
		ib.setItemCategoryName(null);
		ArrayList<ItemBean> result = SelectItemListFromItemsByCategory.selectItemListFromItemsByCategory(ib);
		assertNull(result);
	}
}
