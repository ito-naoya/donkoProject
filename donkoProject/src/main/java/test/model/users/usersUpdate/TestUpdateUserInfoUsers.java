package test.model.users.usersUpdate;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Date;

import org.junit.jupiter.api.Test;

import classes.user.CustomerUser;
import model.users.usersUpdate.UpdateUserInfoInUsers;

class TestUpdateUserInfoUsers {
	
	/*
	 * ユーザー情報を更新する TEST
	 * */
	
	// 成功ケース
	@Test
	void testSuccess() {
		CustomerUser customerUser = new CustomerUser();
		customerUser.setUserLoginId("Tana_aya");
		customerUser.setUserName("田中 あや");
		customerUser.setBirthday(Date.valueOf("1991-03-02"));
		customerUser.setGender("女性");
		customerUser.setUserId(4);
		Boolean result = UpdateUserInfoInUsers.updateUserInfoInUsers(customerUser);
		assertTrue(result);
	}
	
	// 失敗ケース
	/*
	 * int型なのでどう実装しようか検討中で一旦保留
	 * */
	
	void testException() {
		CustomerUser customerUser = new CustomerUser();
		customerUser.setUserLoginId(null);
		customerUser.setUserName(null);
		customerUser.setBirthday(null);
		customerUser.setGender(null);
		customerUser.setUserId(0);
		Boolean result = UpdateUserInfoInUsers.updateUserInfoInUsers(customerUser);
		assertFalse(result);
	}

}
