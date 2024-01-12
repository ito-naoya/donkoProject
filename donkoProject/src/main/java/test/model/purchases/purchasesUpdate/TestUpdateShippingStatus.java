package test.model.purchases.purchasesUpdate;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import bean.PurchaseBean;
import model.purchases.purchasesUpdate.UpdateShippingStatus;

class TestUpdateShippingStatus {

	// 成功テスト
	@Test
	void testSuccess() {
		PurchaseBean purchaseBean = new PurchaseBean();
		purchaseBean.setShippingId(2);
		purchaseBean.setPurchaseId(52);
		Boolean result = UpdateShippingStatus.updateShippingStatus(purchaseBean);
		assertTrue(result);
	}
	
	// 失敗テスト
	@Test
	void testException() {
		PurchaseBean purchaseBean = new PurchaseBean();
		purchaseBean.setShippingId(2);
		purchaseBean.setPurchaseId(100);
		Boolean result = UpdateShippingStatus.updateShippingStatus(purchaseBean);
		assertFalse(result);
	}

}
