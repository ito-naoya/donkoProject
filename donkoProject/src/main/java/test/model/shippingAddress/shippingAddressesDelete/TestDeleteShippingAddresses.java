package test.model.shippingAddress.shippingAddressesDelete;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import bean.ShippingAddressBean;
import model.shippingAddresses.shippingAddressesDelete.DeleteIteShippingAddresses;

class TestDeleteShippingAddresses {

	/*
	 * 配送先削除 TEST
	 * */
	
	// 成功
	@Test
	void testSuccess() {
		ShippingAddressBean shippingAddressBean = new ShippingAddressBean();
		shippingAddressBean.setUserId(4);
		shippingAddressBean.setShippingAddressId(8);
		Boolean status = DeleteIteShippingAddresses.deleteUpdateShippingAddress(shippingAddressBean);
		assertTrue(status);
	}
	
	// 失敗
	@Test
	void testException() {
		ShippingAddressBean shippingAddressBean = new ShippingAddressBean();
		shippingAddressBean.setUserId(0);
		shippingAddressBean.setShippingAddressId(0);
		Boolean status  = DeleteIteShippingAddresses.deleteUpdateShippingAddress(shippingAddressBean);
		assertFalse(status);
	}
}
