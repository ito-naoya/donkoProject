package test.model.shippingAddress.shippingAddressesUpdate;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import bean.ShippingAddressBean;
import model.shippingAddresses.shippingAddressesUpdate.UpdateMainShippingAddress;

class TestUpdateMainShippingAddress {

	//成功テスト
	@Test
	void testSuccess() {
		ShippingAddressBean shippingAddressBean = new ShippingAddressBean();
		shippingAddressBean.setUserId(4);
		shippingAddressBean.setShippingAddressId(6);
		Boolean result = UpdateMainShippingAddress.updateMainShippingAddress(shippingAddressBean);
		assertTrue(result);
	}
	
	//失敗テスト
	@Test
	void testException() {
		ShippingAddressBean shippingAddressBean = new ShippingAddressBean();
		shippingAddressBean.setUserId(0);
		shippingAddressBean.setShippingAddressId(0);
		Boolean result = UpdateMainShippingAddress.updateMainShippingAddress(shippingAddressBean);
		assertFalse(result);
	}
}
