package test.model.users.usersUpdate;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import classes.user.CustomerUser;
import model.users.usersUpdate.UpdateUserInfoByAdmin;

class TestUpdateUserInfoByAdmin {

	//成功テスト
	@Test
	void testSuccess() {
		CustomerUser cu = new CustomerUser();
		cu.setUserLoginId("test123");
		cu.setUserName("テスト");	
		cu.setBirthday(null);
		cu.setGender(null);
		cu.setUserId(1);
		Boolean result = UpdateUserInfoByAdmin.updateUserInfoByAdmin(cu);
		assertTrue(result);
		}
	
	//失敗テスト
	@Test
	void testException() {
		CustomerUser cu = new CustomerUser();
		Boolean result = UpdateUserInfoByAdmin.updateUserInfoByAdmin(cu);
		assertFalse(result);
	}

}
