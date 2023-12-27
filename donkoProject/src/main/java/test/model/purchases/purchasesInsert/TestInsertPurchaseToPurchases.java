package test.model.purchases.purchasesInsert;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import bean.PurchaseBean;
import model.purchases.purchasesInsert.InsertPurchaseToPurchases;

class TestInsertPurchaseToPurchases {

	//成功テスト
	@Test
	void testSuccess() {
		PurchaseBean pb = new PurchaseBean();
		pb.setUserId(3);
		pb.setTotalAmount(200000);
		pb.setPostalCode("1234567");
		pb.setAddress("たけのこノ國");
		pb.setAddressee("たけのこ");
		Boolean result = InsertPurchaseToPurchases.insertPurchaseToPurchases(pb);
		assertTrue(result);
	}
	
	//失敗テスト
	@Test
	void testException() {
		PurchaseBean pb = new PurchaseBean();
		pb.setUserId(0);
		pb.setTotalAmount(0);
		pb.setPostalCode("0");
		pb.setAddress("");
		pb.setAddressee("");
		Boolean result = InsertPurchaseToPurchases.insertPurchaseToPurchases(pb);
		assertFalse(result);
	}

}
