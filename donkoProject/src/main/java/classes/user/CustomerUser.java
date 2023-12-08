package classes.user;

import java.util.Date;

public class CustomerUser extends User {

	public CustomerUser() {
	};

	//ログインID
	private int userLoginId;
	//パスワード
	private String password;
	//ユーザー名
	private String userName;
	//性別
	private String gender;
	//生年月日
	private Date birthday;
	//削除フラグ
	private boolean isDeleted;

	public int getUserLoginId() {
		return userLoginId;
	}

	public void setUserLoginId(int userLoginId) {
		this.userLoginId = userLoginId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

}
