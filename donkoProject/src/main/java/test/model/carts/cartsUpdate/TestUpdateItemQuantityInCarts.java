package test.model.carts.cartsUpdate;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import bean.CartBean;
import model.carts.cartsUpdate.UpdateItemQuantityInCarts;

class TestUpdateItemQuantityInCarts {

	//成功テスト
	@Test
	void testSuccess() {
		CartBean cb = new CartBean();
		cb.setUserId(2);
		cb.setItemId(1);
		cb.setQuantity(3);
		Boolean result = UpdateItemQuantityInCarts.updateItemQuantityInCarts(cb);
		assertTrue(result);
	}
	
	
	//失敗テスト
	@Test
	void testException() {
		CartBean cb = new CartBean();
		cb.setUserId(0);
		cb.setItemId(0);
		cb.setQuantity(0);
		Boolean result = UpdateItemQuantityInCarts.updateItemQuantityInCarts(cb);
		assertFalse(result);
	}
	
}
