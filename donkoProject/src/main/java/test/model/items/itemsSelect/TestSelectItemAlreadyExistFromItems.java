package test.model.items.itemsSelect;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import bean.ItemBean;
import model.items.itemsSelect.SelectItemAlreadyExistFromItems;

class TestSelectItemAlreadyExistFromItems {

//	成功テスト
//	配列がnullの場合でも正常に動作するのでテストは実施なし
	@Test
	void testSuccess() {
		ItemBean item = new ItemBean();
		item.setItemName("hoge");
		String[] itemSecondOptionIncrementIds = {"1", "2", "3"};
		ArrayList<Integer> result = SelectItemAlreadyExistFromItems.selectItemAlreadyExistFromItems(item,itemSecondOptionIncrementIds);
		assertTrue(result instanceof ArrayList<Integer>);
	}
//	//失敗テスト
//	@Test
//	void testException() {
//		ItemBean item = new ItemBean();
//		item.setItemName("hoge");
//		String[] itemSecondOptionIncrementIds = {"1", "2", "3"};
//		ArrayList<Integer> result = SelectItemAlreadyExistFromItems.selectItemAlreadyExistFromItems(item,itemSecondOptionIncrementIds);
//		assertEquals(null, result);
//	}

}
