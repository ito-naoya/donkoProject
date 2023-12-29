package test.model.shippingAddress.shippingAddressesSelect;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import bean.ShippingAddressBean;
import model.shippingAddresses.shippingAddressesSelect.SelectShippingAddressDetail;

class TestSelectShippingAddressDetail {

	//成功テスト
	@Test
	void testSuccess() {
		ShippingAddressBean shippingAddressBean = new ShippingAddressBean();
		shippingAddressBean.setUserId(6);
		shippingAddressBean.setShippingAddressId(6);
		ShippingAddressBean result = SelectShippingAddressDetail.selectShippingAddressDetail(shippingAddressBean);
		assertTrue(result instanceof ShippingAddressBean);
	}
	
	//失敗テスト
	@Test
	void testException() {
		ShippingAddressBean shippingAddressBean = new ShippingAddressBean();
		shippingAddressBean.setUserId(0);
		shippingAddressBean.setShippingAddressId(0);
		ShippingAddressBean result = SelectShippingAddressDetail.selectShippingAddressDetail(shippingAddressBean);
		assertNull(result);
	}

}
