package test.model.users.usersSelect;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import classes.user.AdminUser;
import model.users.usersSelect.SelectAdminFromAdmins;

class TestSelectAdminFromAdmins {

	//成功テスト
	@Test
	void testSuccess() {
		AdminUser user  = new AdminUser();
		user.setAdminLoginId("hoge");
		user.setPassword("hoge");
		Boolean result = SelectAdminFromAdmins.selectAdminFromAdmins(user);
		assertTrue(result);
	}

	//失敗テスト
	@Test
	void testException() {
		AdminUser user  = new AdminUser();
		user.setAdminLoginId(null);
		user.setPassword(null);
		Boolean result = SelectAdminFromAdmins.selectAdminFromAdmins(user);
		assertFalse(result);
	}
}
