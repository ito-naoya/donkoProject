package test.model.carts.cartsSelect;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import bean.CartBean;
import classes.user.CustomerUser;
import model.carts.cartsSelect.SelectItemListFromCarts;

class TestSelectItemListFromCarts {

	//成功テスト
	@Test
	void testSuccess() {
		CustomerUser cu = new CustomerUser();
		cu.setUserId(3);
		ArrayList<CartBean> result = SelectItemListFromCarts.selectItemListFromCarts(cu);
		assertTrue(result instanceof ArrayList<CartBean>);
	}
	
	//失敗テスト(SQL書き換え必要)
	@Test
	void testException() {
		CustomerUser cu = new CustomerUser();
		ArrayList<CartBean> result = SelectItemListFromCarts.selectItemListFromCarts(cu);
		assertNull(result);
	}
	


}
