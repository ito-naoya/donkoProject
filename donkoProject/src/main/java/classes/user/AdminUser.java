package classes.user;

import java.util.ArrayList;

import model.users.usersSelect.SelectDeletedUserListFromUsers;
import model.users.usersSelect.SelectUserListFromUsers;
import model.users.usersUpdate.UpdateUserInfoByAdmin;

public class AdminUser extends User {

	public AdminUser() {
	};

	//ユーザーを削除する
	public static void updateUserInfoByAdmin(CustomerUser customerUser) {
		UpdateUserInfoByAdmin.updateUserInfoByAdmin(customerUser);
	};

	//削除したユーザー一覧を取得する
	public static ArrayList<CustomerUser> getDeletedUserList() {
		return SelectDeletedUserListFromUsers.selectDeletedUserListFromUsers();
	};

	//全てのユーザー一覧を取得する
	public static ArrayList<CustomerUser> getUserList() {
		return SelectUserListFromUsers.selectUserListFromUsers();
	};

}
