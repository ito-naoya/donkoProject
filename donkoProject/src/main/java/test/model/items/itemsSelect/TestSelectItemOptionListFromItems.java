package test.model.items.itemsSelect;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import bean.ItemBean;
import model.items.itemsSelect.SelectItemOptionListFromItems;

class TestSelectItemOptionListFromItems {

	//成功テスト
	@Test
	void testSuccess() {
		ItemBean ib = new ItemBean();
		ib.setItemId(1);
		ArrayList<ItemBean> result = SelectItemOptionListFromItems.selectItemOptionListFromItems(ib);
		assertTrue(result instanceof ArrayList<ItemBean>);
	}
	
	//失敗テスト(SQL書き換え必要)
	@Test
	void testException() {
		ItemBean ib = new ItemBean();
		ArrayList<ItemBean> result = SelectItemOptionListFromItems.selectItemOptionListFromItems(ib);
		assertNull(result);
	}

}
