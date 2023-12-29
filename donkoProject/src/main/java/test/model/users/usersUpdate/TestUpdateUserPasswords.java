package test.model.users.usersUpdate;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
	/*
	 * ハッシュ化でエラー
	 * 後ほど対応
	 * */
	
	@Test
	void testException() {
		CustomerUser customerUser = new CustomerUser();
		customerUser.setUserLoginId(null);
		customerUser.setPassword(null);
		Boolean result = UpdateUserPasswords.updateUserPasswords(customerUser);
		assertFalse(result);
	}

}
