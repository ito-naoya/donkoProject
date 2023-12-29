package test.model.shippingAddress.shippingAddressesSelect;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import bean.ShippingAddressBean;
import model.shippingAddresses.shippingAddressesSelect.SelectShippingAddressDetail;

class TestSelectShippingAddressList {

	//成功テスト
	@Test
	void testSuccess() {
		ShippingAddressBean shippingAddressBean = new ShippingAddressBean();
		shippingAddressBean.setUserId(6);
		ShippingAddressBean result = SelectShippingAddressDetail.selectShippingAddressDetail(shippingAddressBean);
		assertTrue(result instanceof ShippingAddressBean);
	}
	
	//失敗テスト
	@Test
	void testException() {
		ShippingAddressBean shippingAddressBean = new ShippingAddressBean();
		shippingAddressBean.setUserId(-1);
		ShippingAddressBean result = SelectShippingAddressDetail.selectShippingAddressDetail(shippingAddressBean);
		assertNull(result instanceof ShippingAddressBean);
	}

}
