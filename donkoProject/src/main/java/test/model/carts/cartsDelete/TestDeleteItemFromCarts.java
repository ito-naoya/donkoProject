package test.model.carts.cartsDelete;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import bean.CartBean;
import model.carts.cartsDelete.DeleteItemFromCarts;

class TestDeleteItemFromCarts {

	//成功テスト
	@Test
	void testSuccess() {
		CartBean cb = new CartBean();
		cb.setUserId(3);
		cb.setItemId(21);
		Boolean result = DeleteItemFromCarts.deleteItemFromCarts(cb);
		assertTrue(result);
	}
	
	//失敗テスト
	@Test
	void testException() {
		CartBean cb = new CartBean();
		cb.setUserId(0);
		cb.setItemId(0);
		Boolean result = DeleteItemFromCarts.deleteItemFromCarts(cb);
		assertFalse(result);
	}

}
