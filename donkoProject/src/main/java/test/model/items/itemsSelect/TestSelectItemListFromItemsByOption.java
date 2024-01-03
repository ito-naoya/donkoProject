package test.model.items.itemsSelect;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import bean.ItemBean;
import model.items.itemsSelect.SelectItemListFromItemsByOption;

class TestSelectItemListFromItemsByOption {
	
	// 成功テスト
	@Test
	void testSuccess() {
		String[] checkedOption = {"S","緑","黒"};
		String categoryName = "衣類";
		ArrayList<ItemBean> result = SelectItemListFromItemsByOption.selectItemListFromItemsByOption(checkedOption, categoryName);
		assertTrue(result instanceof ArrayList<ItemBean>);
	}
	
	// 失敗テスト
	@Test
	void testException() {
		String[] checkedOption = {};
		String categoryName = null;
		ArrayList<ItemBean> result = SelectItemListFromItemsByOption.selectItemListFromItemsByOption(checkedOption, categoryName);
		assertEquals(null, result);
	}
}
