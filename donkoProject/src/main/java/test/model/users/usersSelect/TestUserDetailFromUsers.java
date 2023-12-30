package test.model.users.usersSelect;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import classes.user.CustomerUser;
import model.users.usersSelect.SelectUserDetailFromUsers;

class TestUserDetailFromUsers {
	
	/*
	 * ユーザーの詳細情報を取得する TEST
	 * */
	
	// 成功ケース
	@Test
	void testSuccess() {
		CustomerUser customerUser = new CustomerUser();
		customerUser.setUserId(1);
		CustomerUser result = SelectUserDetailFromUsers.selectUserDetailFromUsers(customerUser);
		assertTrue(result instanceof CustomerUser);
	}
	
	// 失敗ケース
	@Test
	void testException() {
		CustomerUser customerUser  = new CustomerUser();
		customerUser.setUserId(0);
		CustomerUser result = SelectUserDetailFromUsers.selectUserDetailFromUsers(customerUser);
		assertNull(result);
	}

}
