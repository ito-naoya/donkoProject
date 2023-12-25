package test.model.items.itemsSelect;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import bean.ItemBean;
import model.items.itemsSelect.SelectItemDetailFromItems;

class TestSelectItemDetailFromItems {

	//存在しない商品IDの場合nullを返す
	@Test
	void doseNotExistIdTest() {
		ItemBean ib = new ItemBean();
		ib.setItemId(0);
		ItemBean result = SelectItemDetailFromItems.selectItemDetailFromItems(ib);
		assertEquals(null, result);
	}
}
