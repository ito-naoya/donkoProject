package classes.user;

import java.util.Date;

public class CustomerUser extends User {

	public CustomerUser() {
	};

	//ログインID
	private static String userLoginId;
	//パスワード
	private static String password;
	//ユーザー名
	private static String userName;
	//性別
	private static String gender;
	//生年月日
	private static Date birthday;
	//削除フラグ
	private boolean isDeleted;
/*
 * TODO：あとで	戻す
 * //ログインID
 * 	private String userLoginId;
 * 	//パスワード
 * 	private String password;
 * //ユーザー名
 * 	private String userName;
 * 	//性別
 * 	private String gender;
 * 	//生年月日
 * 	private Date birthday;
 * */


	public String getUserLoginId() {
		return userLoginId;
	}

	public static void setUserLoginId(String userLoginId) {
		CustomerUser.userLoginId = userLoginId;
	}
//	TODO:あとで戻す
//	public void setUserLoginId(String userLoginId) {
//		this.userLoginId = userLoginId;
//	}

	public String getPassword() {
		return password;
	}

	public static void setPassword(String password) {
		CustomerUser.password = password;
	}
//	TODO:あとで戻す
//	public void setPassword(String password) {
//		this.password = password;
//	}

	public String getUserName() {
		return userName;
	}

	public static void setUserName(String userName) {
		CustomerUser.userName = userName;
	}
//	TODO:あとで戻す
//	public void setUserName(String userName) {
//		this.userName = userName;
//	}

	public String getGender() {
		return gender;
	}

	public static void setGender(String gender) {
		CustomerUser.gender = gender;
	}
//	TODO:あとで戻す
//	public void setGender(String gender) {
//		this.gender = gender;
//	}

	public Date getBirthday() {
		return birthday;
	}

	public static void setBirthday(Date birthday) {
		CustomerUser.birthday = birthday;
	}
//	TODO:あとで戻す
//	public void setBirthday(Date birthday) {
//		this.birthday = birthday;
//	}

	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

}
