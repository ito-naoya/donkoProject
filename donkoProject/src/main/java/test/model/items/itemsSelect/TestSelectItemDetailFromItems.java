package test.model.items.itemsSelect;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import bean.ItemBean;
import model.items.itemsSelect.SelectItemDetailFromItems;

class TestSelectItemDetailFromItems {

	//成功テスト
	@Test
	void testSuccess() {
		ItemBean ib = new ItemBean();
		ib.setItemId(1);
		ItemBean result = SelectItemDetailFromItems.selectItemDetailFromItems(ib);
		assertTrue(result instanceof ItemBean);
	}
	
	//取得件数が０件の場合
	@Test
	void testSelectIsNull() {
		ItemBean ib = new ItemBean();
		ib.setItemId(0);
		ItemBean result = SelectItemDetailFromItems.selectItemDetailFromItems(ib);
		assertEquals(null, result);
	}
	
}
