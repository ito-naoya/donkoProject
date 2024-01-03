package test.model.purchases.purchasesSelect;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import bean.PurchaseBean;
import model.purchases.purchasesSelect.SelectPurchaseInfo;

class TestSelectPurchaseInfo {

	// 成功テスト
	@Test
	void testSuccess() {
		PurchaseBean purchaseBean = new PurchaseBean();
		purchaseBean.setPurchaseId(52);
		PurchaseBean result = SelectPurchaseInfo.selectPurchaseInfo(purchaseBean);
		assertTrue(result instanceof PurchaseBean);
	}
	
	// 失敗テスト
	@Test
	void testException() {
		PurchaseBean purchaseBean = new PurchaseBean();
		purchaseBean.setPurchaseId(1);
		PurchaseBean result = SelectPurchaseInfo.selectPurchaseInfo(purchaseBean);
		assertFalse(result == null);
	}

}
