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

	protected int getUserId() {
		return userId;
	}

	protected void setUserId(int userId) {
		this.userId = userId;
	}

	//ユーザー新規登録する
	protected static void registerNewUser(CustomerUser customerUser) {
		InsertNewUserToUsers.insertNewUserToUsers(customerUser);
	};
	
	//ユーザー情報の重複をチェックする
	protected void checkUserDuplicate(CustomerUser customerUser){
		SelectUserDuplicate.selectUserDuplicate(customerUser);
	};

	//ユーザー情報を更新する
	protected void updateUserInfo(CustomerUser customerUser) {
		UpdateUserInfoInUsers.updateUserInfoInUsers(customerUser);
	};

	//ユーザーの詳細を取得する
	protected CustomerUser getUserDetail(CustomerUser customerUser) {
		return SelectUserDetailFromUsers.selectUserDetailFromUsers(customerUser);
	};

	//ログアウトする
	//TODO セッション破棄のメソッドを記述する
	protected void logout() {
	};

	//ログインする
	protected static void login(CustomerUser customerUser) {
		SelectUserFromUsers.selectUserFromUsers(customerUser);
	};

}
