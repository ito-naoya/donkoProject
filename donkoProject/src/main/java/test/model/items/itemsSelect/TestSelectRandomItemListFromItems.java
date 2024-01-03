package test.model.items.itemsSelect;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import bean.ItemBean;
import model.items.itemsSelect.SelectRandomItemListFromItems;

class TestSelectRandomItemListFromItems {

	//成功テスト
	@Test
	void testSuccess() {
		ArrayList<ItemBean> result = SelectRandomItemListFromItems.selectItemListFromItems();
		assertTrue(result instanceof ArrayList<ItemBean>);
	}

	//失敗テスト
	@Test
	void testException() {
		ArrayList<ItemBean> result = SelectRandomItemListFromItems.selectItemListFromItems();
		assertEquals(null, result);
	}

}
