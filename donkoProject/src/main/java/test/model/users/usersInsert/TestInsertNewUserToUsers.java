package test.model.users.usersInsert;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import classes.user.CustomerUser;
import model.users.usersInsert.InsertNewUserToUsers;

class TestInsertNewUserToUsers {

	//成功テスト
	@Test
	void testSuccess() {
		CustomerUser customerUser = new CustomerUser();
		customerUser.setUserLoginId("hoge");
		customerUser.setPassword("hoge");
		customerUser.setUserName("hoge");
		Boolean result = InsertNewUserToUsers.insertNewUserToUsers(customerUser);
		assertTrue(result);
	}
	//失敗テスト
	@Test
	void testException() {
		CustomerUser customerUser = new CustomerUser();
		customerUser.setUserLoginId(null);
		customerUser.setPassword(null);
		customerUser.setUserName(null);
		Boolean result = InsertNewUserToUsers.insertNewUserToUsers(customerUser);
		assertFalse(result);
	}
}
