package classes;

import java.util.ArrayList;

import model.users.usersDelete.DeleteUserFromUsers;
import model.users.usersSelect.SelectDeletedUserListFromUsers;
import model.users.usersSelect.SelectUserListFromUsers;

public class AdminUser extends User {

	public AdminUser() {
	};

	//ユーザーを削除する
	public void deleteUser(CustomerUser customerUser) {
		DeleteUserFromUsers.deleteUserFromUsers(customerUser);
	};

	//削除したユーザー一覧を取得する
	public ArrayList<CustomerUser> getDeletedUserList() {
		return SelectDeletedUserListFromUsers.selectDeletedUserListFromUsers();
	};

	//全てのユーザー一覧を取得する
	public ArrayList<CustomerUser> getUserList() {
		return SelectUserListFromUsers.selectUserListFromUsers();
	};

}
