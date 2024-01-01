package test.model.purchases.purchasesSelect;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import bean.PurchaseBean;
import model.purchases.purchasesSelect.SelectUnshippingItemListByDesc;

class TestSelectUnshippingItemListByDesc {

	//成功テスト
	@Test
	void testSuccess() {
		ArrayList<PurchaseBean> result = SelectUnshippingItemListByDesc.selectUnshippingItemListByDesc();
		assertTrue(result instanceof ArrayList<PurchaseBean>);
	}

	//失敗テスト
	@Test
	void testException() {
		ArrayList<PurchaseBean> result = SelectUnshippingItemListByDesc.selectUnshippingItemListByDesc();
		assertEquals(null, result);
	}

}
