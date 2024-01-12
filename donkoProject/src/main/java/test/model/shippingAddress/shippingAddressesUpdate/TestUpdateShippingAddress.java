package test.model.shippingAddress.shippingAddressesUpdate;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import bean.ShippingAddressBean;
import model.shippingAddresses.shippingAddressesUpdate.UpdateShippingAddress;

class TestUpdateShippingAddress {

	//成功テスト
	@Test
	void testSuccess() {
		ShippingAddressBean shippingAddressBean = new ShippingAddressBean();
		shippingAddressBean.setPostalCode("7600000");
		shippingAddressBean.setAddressee("香川県坂出市yyy町9-8-7");
		shippingAddressBean.setAddress("何処 徹子");
		shippingAddressBean.setUserId(4);
		shippingAddressBean.setShippingAddressId(8);
		Boolean result = UpdateShippingAddress.updateShippingAddress(shippingAddressBean);
		assertTrue(result);
	}
	
	//失敗テスト
	@Test
	void testException() {
		ShippingAddressBean shippingAddressBean = new ShippingAddressBean();
		shippingAddressBean.setPostalCode(null);
		shippingAddressBean.setAddressee(null);
		shippingAddressBean.setAddress(null);
		shippingAddressBean.setUserId(0);
		shippingAddressBean.setShippingAddressId(0);
		Boolean result = UpdateShippingAddress.updateShippingAddress(shippingAddressBean);
		assertFalse(result);
	}
}
