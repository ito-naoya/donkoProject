package test.model.users.usersSelect;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import classes.user.CustomerUser;
import model.users.usersSelect.SelectDeletedUserListFromUsers;

class TestSelectDeletedUserListFromUsers {

	//成功テスト
	@Test
	void testSuccess() {
		ArrayList<CustomerUser> result = SelectDeletedUserListFromUsers.selectDeletedUserListFromUsers();
		assertTrue(result instanceof ArrayList<CustomerUser>);
	}
	
	//失敗テスト(SQL書き換え必要)
	@Test 
	void testException() {
		ArrayList<CustomerUser> result = SelectDeletedUserListFromUsers.selectDeletedUserListFromUsers();
		assertNull(result);
	}

}
