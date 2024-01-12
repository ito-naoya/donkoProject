package test.model.purchaseDetails.purchaseDetailsSelect;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import bean.PurchaseDetailBean;
import model.purchaseDetails.purchaseDetailsSelect.SelectPurchaseDetail;

class TestSelectPurchaseDetail {
	
	// 成功テスト
	@Test
	void testSuccees() {
		PurchaseDetailBean purchaseDetailBean = new PurchaseDetailBean();
		purchaseDetailBean.setPurchaseId(16);
		ArrayList<PurchaseDetailBean> result = SelectPurchaseDetail.selectPurchaseDetail(purchaseDetailBean);
		assertTrue(result instanceof ArrayList<PurchaseDetailBean> );
	}
	
	// 失敗テスト
	@Test
	void testException() {
		PurchaseDetailBean purchaseDetailBean = new PurchaseDetailBean();
		purchaseDetailBean.setPurchaseId(0);
		ArrayList<PurchaseDetailBean> result = SelectPurchaseDetail.selectPurchaseDetail(purchaseDetailBean);
		assertNull(result);
	}
}
