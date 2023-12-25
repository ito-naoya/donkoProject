package test.model.carts.cartsUpdate;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import bean.CartBean;
import model.carts.cartsUpdate.UpdateItemQuantityInCarts;

class TestUpdateItemQuantityInCarts {

	//数量更新成功時のテスト
//	@Test
//	void success() {
//		CartBean cb = new CartBean();
//		cb.setUserId(2);
//		cb.setItemId(1);
//		cb.setQuantity(3);
//		Boolean result = UpdateItemQuantityInCarts.updateItemQuantityInCarts(cb);
//		assertTrue(result);
//	}
	
	//以下、falseを返すテスト
	
	//userIdが存在しなかった場合は
//	@Test
//	void doesNotExitstUserId() {
//		CartBean cb = new CartBean();
//		cb.setUserId(0);
//		cb.setItemId(1);
//		cb.setQuantity(3);
//		Boolean result = UpdateItemQuantityInCarts.updateItemQuantityInCarts(cb);
//		assertFalse(result);
//	}
	
	//itemIdが存在しなかった場合
	@Test
	void doesNotExitstItemId() {
		CartBean cb = new CartBean();
		cb.setUserId(2);
		cb.setItemId(124534734);
		cb.setQuantity(3);
		Boolean result = UpdateItemQuantityInCarts.updateItemQuantityInCarts(cb);
		assertFalse(result);
	}

}
