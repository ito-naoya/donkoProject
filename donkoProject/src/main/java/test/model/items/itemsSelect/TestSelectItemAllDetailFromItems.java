package test.model.items.itemsSelect;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import bean.ItemBean;
import model.items.itemsSelect.SelectItemAllDetailFromItems;

class TestSelectItemAllDetailFromItems {

	//成功テスト
	@Test
	void testSuccess() {
		ItemBean item  = new ItemBean();
		item.setItemId(1);
		ItemBean result = SelectItemAllDetailFromItems.selectItemAllDetailFromItems(item);
		assertTrue(result instanceof ItemBean);
	}

	//失敗テスト
	@Test
	void testException() {
		ItemBean item  = new ItemBean();
		item.setItemId(-1);
		ItemBean result = SelectItemAllDetailFromItems.selectItemAllDetailFromItems(item);
		assertEquals(null, result);
	}
}
