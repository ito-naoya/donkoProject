package test.model.shippingAddress.shippingAddressesSelect;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import bean.ShippingAddressBean;
import classes.user.CustomerUser;
import model.shippingAddresses.shippingAddressesSelect.SelectMainShippingAddressSort;

class TestSelectMainShippingAddressSort {

	//成功テスト
	@Test
	void testSuccess() {
		CustomerUser customerUser = new CustomerUser();
		customerUser.setUserId(4);
		ArrayList<ShippingAddressBean> result = SelectMainShippingAddressSort.selectMainShippingAddressSort(customerUser);
		assertTrue(result instanceof ArrayList<ShippingAddressBean>);
	}
	
	//失敗テスト
	@Test
	void testException() {
		CustomerUser customerUser = new CustomerUser();
		customerUser.setUserId(0);
		ArrayList<ShippingAddressBean> result = SelectMainShippingAddressSort.selectMainShippingAddressSort(customerUser);
		assertNull(result);
	}

}
