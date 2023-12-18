package classes.user;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

import hash.HashGenerator;
import model.users.usersSelect.SelectAdminFromAdmins;
import model.users.usersSelect.SelectDeletedUserListFromUsers;
import model.users.usersSelect.SelectUserListFromUsers;
import model.users.usersUpdate.UpdateUserInfoByAdmin;

public class AdminUser extends User {

	public AdminUser() {
	};

	//アドミンのログインId
	private String adminLoginId;
	//アドミンのパスワード
	private String password;

	public String getAdminLoginId() {
		return adminLoginId;
	}

	public void setAdminLoginId(String adminLoginId) {
		this.adminLoginId = adminLoginId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	//アドミン側でログインする
	public static boolean login(AdminUser adminUser) {
		//パスワードをハッシュ化し再挿入する
		try {
			adminUser.setPassword(HashGenerator.generateHash(adminUser.getPassword()));
		} catch (NoSuchAlgorithmException e) {
			return false;
		}
		return SelectAdminFromAdmins.selectAdminFromAdmins(adminUser);
	}

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
