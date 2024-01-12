package test.model.purchases.purchasesSelect;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import bean.PurchaseBean;
import model.purchases.purchasesSelect.SelectOrderItemList;

class TestSelectOrderItemList {

	//成功テスト
	@Test
	void testSuccess() {
		ArrayList<PurchaseBean> result = SelectOrderItemList.selectOrderItemList();
		assertTrue(result instanceof ArrayList<PurchaseBean>);
	}

	//失敗テスト
	@Test
	void testException() {
		ArrayList<PurchaseBean> result = SelectOrderItemList.selectOrderItemList();
		assertEquals(null, result);
	}

}
