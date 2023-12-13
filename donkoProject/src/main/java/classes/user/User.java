package classes.user;

import model.users.usersInsert.InsertNewUserToUsers;
import model.users.usersSelect.SelectUserDetailFromUsers;
import model.users.usersSelect.SelectUserDuplicate;
import model.users.usersSelect.SelectUserFromUsers;
import model.users.usersUpdate.UpdateUserInfoInUsers;

public class User {

	//ユーザーID
	protected int userId;

	public User() {
	};

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	//ユーザー新規登録する
	public static void registerNewUser(CustomerUser customerUser) {
		InsertNewUserToUsers.insertNewUserToUsers(customerUser);
	};
	
	//ユーザー情報の重複をチェックする
	public void checkUserDuplicate(CustomerUser customerUser){
		SelectUserDuplicate.selectUserDuplicate(customerUser);
	};

	//ユーザー情報を更新する
	public void updateUserInfo(CustomerUser customerUser) {
		UpdateUserInfoInUsers.updateUserInfoInUsers(customerUser);
	};

	//ユーザーの詳細を取得する
	public static CustomerUser getUserDetail(CustomerUser customerUser) {
		return SelectUserDetailFromUsers.selectUserDetailFromUsers(customerUser);
	};

	//ログアウトする
	//TODO セッション破棄のメソッドを記述する
	public void logout() {
	};

	//ログインする
	public static void login(CustomerUser customerUser) {
		SelectUserFromUsers.selectUserFromUsers(customerUser);
	};

}
