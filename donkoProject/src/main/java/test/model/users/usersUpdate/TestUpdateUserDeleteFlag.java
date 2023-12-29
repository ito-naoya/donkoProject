package test.model.users.usersUpdate;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import classes.user.CustomerUser;
import model.users.usersUpdate.UpdateUserDeleteFlag;

class TestUpdateUserDeleteFlag {
	
	/*
	 * ユーザを論理削除するメソッド TEST
	 * */
	
	// 成功ケース
	@Test
	void testSuccess() {
		CustomerUser customerUser = new CustomerUser();
		customerUser.setUserId(4);
		Boolean result = UpdateUserDeleteFlag.updateUserDeleteFlags(customerUser);
		assertTrue(result);
	}
	
	// 失敗ケース
	@Test
	void testException() {
		CustomerUser customerUser = new CustomerUser();
		Boolean result = UpdateUserDeleteFlag.updateUserDeleteFlags(customerUser);
		assertTrue(result);
	}

}
