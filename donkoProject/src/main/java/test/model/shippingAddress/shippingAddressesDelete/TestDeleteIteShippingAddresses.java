package test.model.shippingAddress.shippingAddressesDelete;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import bean.ShippingAddressBean;
import model.shippingAddresses.shippingAddressesDelete.DeleteIteShippingAddresses;

class TestDeleteIteShippingAddresses {

	/*
	 * 配送先削除 TEST
	 * */
	
	// 成功
	@Test
	void testSuccess() {
		ShippingAddressBean shippingAddressBean = new ShippingAddressBean();
		shippingAddressBean.setUserId(13);
		shippingAddressBean.setShippingAddressId(24);
		Boolean status  = DeleteIteShippingAddresses.deleteUpdateShippingAddress(shippingAddressBean);
		assertTrue(status);
	}
	
	// 失敗
	@Test
	void testException() {
		ShippingAddressBean shippingAddressBean = new ShippingAddressBean();
		Boolean status  = DeleteIteShippingAddresses.deleteUpdateShippingAddress(shippingAddressBean);
		assertFalse(status);
	}
}
