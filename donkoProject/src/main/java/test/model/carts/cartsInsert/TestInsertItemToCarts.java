package test.model.carts.cartsInsert;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import bean.CartBean;
import model.carts.cartsInsert.InsertItemToCarts;

class TestInsertItemToCarts {

	//成功テスト
//	@Test
//	void testSuccess() {
//		CartBean cb = new CartBean();
//		cb.setUserId(1);
//		cb.setItemId(1);
//		cb.setQuantity(1);
//		Boolean result = InsertItemToCarts.insertItemToCarts(cb);
//		assertTrue(result);
//	}
	
	//追加件数が0件の場合
	@Test
	void testInsertIsFalse() {
		CartBean cb = new CartBean();
		cb.setUserId(999999999);
		cb.setItemId(999999999);
		cb.setQuantity(-99-999-999-9);
		Boolean result = InsertItemToCarts.insertItemToCarts(cb);
		assertFalse(result);
	}

}
