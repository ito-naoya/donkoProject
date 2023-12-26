package test.model.carts.cartsDelete;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import classes.user.CustomerUser;
import model.carts.cartsDelete.DeleteItemAllFromCarts;

class TestDeleteItemAllFromCarts {

	//成功テスト
	@Test
	void testSuccess() {
		CustomerUser cu = new CustomerUser();
		cu.setUserId(2);
		Boolean result = DeleteItemAllFromCarts.deleteItemAllFromCarts(cu);
		assertTrue(result);
	}
	
	//失敗テスト
	@Test
	void testException() {
		CustomerUser cu = new CustomerUser();
		cu.setUserId(0);
		Boolean result = DeleteItemAllFromCarts.deleteItemAllFromCarts(cu);
		assertFalse(result);
	}

}
