package classes.user;

import java.security.NoSuchAlgorithmException;

import hash.HashGenerator;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import model.users.usersInsert.InsertNewUserToUsers;
import model.users.usersSelect.SelectUserDetailFromUsers;
import model.users.usersSelect.SelectUserDuplicate;
import model.users.usersSelect.SelectUserFromUsers;
import model.users.usersUpdate.UpdateUserDeleteFlag;
import model.users.usersUpdate.UpdateUserInfoInUsers;
import model.users.usersUpdate.UpdateUserPasswords;

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
	public static Boolean registerNewUser(CustomerUser customerUser) {
		try {
			customerUser.setPassword(HashGenerator.generateHash(customerUser.getPassword()));
		} catch (NoSuchAlgorithmException e) {
			return false;
		}
		return InsertNewUserToUsers.insertNewUserToUsers(customerUser);
	};

	//ユーザー情報の重複をチェックする
	public static Integer checkUserDuplicate(CustomerUser customerUser){
		return SelectUserDuplicate.selectUserDuplicate(customerUser);
	};

	//ユーザー情報を更新する
	public static Boolean updateUserInfo(CustomerUser customerUser) {
		return UpdateUserInfoInUsers.updateUserInfoInUsers(customerUser);
	};

	//ユーザーの詳細を取得する
	public static CustomerUser getUserDetail(CustomerUser customerUser) {
		return SelectUserDetailFromUsers.selectUserDetailFromUsers(customerUser);
	};

	//ユーザーのパスワードを更新する
	public static Boolean updateUserPassword(CustomerUser customerUser) {
		return UpdateUserPasswords.updateUserPasswords(customerUser);
	};

	//ユーザーを論理削除する
	public static Boolean updateUserDeleteFlag(CustomerUser customerUser) {
		return UpdateUserDeleteFlag.updateUserDeleteFlags(customerUser);
	};

	//ログアウトする
	//TODO セッション破棄のメソッドを記述する
	public static String logout(HttpServletRequest request) {
	HttpSession session = request.getSession(false);
		// セッションの確認
		Object userId = session.getAttribute("user_id");
		Object admin = session.getAttribute("admin");
		
		// ユーザー側のログインかどうかを確認
		if (userId != null) {
			// ユーザー側のセッションがあれば破棄
			session.removeAttribute("user_id");
			session.invalidate();
			// 画面遷移先のリンクはホーム画面
			return "home";
		}	// 管理者側のログインかどうかを確認
			else if (admin != null) {
			// 管理者側のセッションがあれば破棄
			session.removeAttribute("admin");
			session.invalidate();
			// 画面遷移先のリンクは管理者のログイン画面
			return "adminSignin";
		}
		return null;
	};

	//ログインする
	public static CustomerUser login(CustomerUser customerUser) {
		return SelectUserFromUsers.selectUserFromUsers(customerUser);
	};

}
