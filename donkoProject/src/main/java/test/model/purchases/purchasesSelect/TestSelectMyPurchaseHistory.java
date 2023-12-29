package test.model.purchases.purchasesSelect;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import bean.PurchaseBean;
import classes.user.CustomerUser;
import model.purchases.purchasesSelect.SelectMyPurchaseHistory;

class TestSelectMyPurchaseHistory {
	/*
	 * ユーザ毎の購入履歴の一覧を取得するメソッド TEST
	 * */
	
	// 成功
	@Test
	void testSuccess() {
		CustomerUser customerUser = new CustomerUser();
		customerUser.setUserId(4);
		ArrayList<PurchaseBean> result = SelectMyPurchaseHistory.selectMyPurchaseHistory(customerUser);
		
		assertTrue(result instanceof ArrayList<PurchaseBean>);
	}
	// 失敗
	@Test
	void testException() {
		CustomerUser customerUser = new CustomerUser();
		ArrayList<PurchaseBean> result = SelectMyPurchaseHistory.selectMyPurchaseHistory(customerUser);
		boolean status;
		if(result.size() > 1) {
			status = true;
		} else {
			status = false;
		}
		assertTrue(status);
	}

}
