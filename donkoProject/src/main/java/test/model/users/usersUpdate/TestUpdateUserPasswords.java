package test.model.users.usersUpdate;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import classes.user.CustomerUser;
import model.users.usersUpdate.UpdateUserPasswords;

class TestUpdateUserPasswords {
	
	/*
	 * ユーザのパスワード情報を更新する TEST
	 * */
	
	// 成功ケース
	@Test
	void testSuccess() {
		CustomerUser customerUser = new CustomerUser();
		customerUser.setUserLoginId("Tana_aya");
		customerUser.setPassword("testtanaAya");
		Boolean result = UpdateUserPasswords.updateUserPasswords(customerUser);
		assertTrue(result);
	}
	
	// 失敗ケース
	@Test
	void testException() {
		CustomerUser customerUser = new CustomerUser();
		customerUser.setUserLoginId(null);
		customerUser.setPassword("test"); // ハッシュ化でエラーになるので一旦値を入れる
		Boolean result = UpdateUserPasswords.updateUserPasswords(customerUser);
		assertFalse(result);
	}

}
