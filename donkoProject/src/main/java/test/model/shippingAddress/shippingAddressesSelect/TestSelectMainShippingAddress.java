package test.model.shippingAddress.shippingAddressesSelect;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import bean.ShippingAddressBean;
import classes.user.CustomerUser;
import model.shippingAddresses.shippingAddressesSelect.SelectMainShippingAddress;

class TestSelectMainShippingAddress {

	//成功テスト
	@Test
	void testSuccess() {
		CustomerUser cu = new CustomerUser();
		cu.setUserId(2);
		ShippingAddressBean result = SelectMainShippingAddress.selectMainShippingAddress(cu);
		assertTrue(result instanceof ShippingAddressBean);
	}
	
	//失敗テスト
	@Test
	void testException() {
		CustomerUser cu = new CustomerUser();
		ShippingAddressBean result = SelectMainShippingAddress.selectMainShippingAddress(cu);
		assertNull(result);
	}

}
