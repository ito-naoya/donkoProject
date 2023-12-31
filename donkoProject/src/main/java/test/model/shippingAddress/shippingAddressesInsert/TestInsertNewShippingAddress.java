package test.model.shippingAddress.shippingAddressesInsert;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import bean.ShippingAddressBean;
import model.shippingAddresses.shippingAddressesInsert.InsertNewShippingAddress;

class TestInsertNewShippingAddress {
	/*
	 * 新規配送先を登録 TEST
	 * */
	
	// 成功
	@Test
	void testSuccess() {
		ShippingAddressBean shippingAddressBean = new ShippingAddressBean();
		shippingAddressBean.setUserId(4);
		shippingAddressBean.setPostalCode("1234567");
		shippingAddressBean.setAddress("香川県高松市xxx町1-2-3");
		shippingAddressBean.setMainShippingAddress(0);
		shippingAddressBean.setAddressee("高松魚");
		Boolean insertStatus  = InsertNewShippingAddress.insertNewShippingAddress(shippingAddressBean);
		assertTrue(insertStatus);
	}
	
	// 失敗
		@Test
		void testException() {
			ShippingAddressBean shippingAddressBean = new ShippingAddressBean();
			shippingAddressBean.setUserId(-1);
			shippingAddressBean.setPostalCode(null);
			shippingAddressBean.setAddress(null);
			shippingAddressBean.setMainShippingAddress(-1);
			shippingAddressBean.setAddressee(null);
			Boolean insertStatus  = InsertNewShippingAddress.insertNewShippingAddress(shippingAddressBean);
			assertFalse(insertStatus);
		}

}
