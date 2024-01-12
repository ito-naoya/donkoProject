package classes.user;

import java.util.Date;

import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import interfaces.group.GroupA;
import interfaces.group.GroupB;
import interfaces.group.GroupC;
import interfaces.group.GroupD;
public class CustomerUser extends User {

	public CustomerUser() {
	};

	//ログインID
	@Length(groups = {GroupA.class, GroupB.class, GroupC.class, GroupD.class}, min=5 , max= 10, message="{min}文字以上{max}文字以内で入力してください。" )
	private String userLoginId;
	//パスワード
	@Length(groups = {GroupC.class}, min=8, max=16, message="{min}文字以上{max}文字以内で入力してください。" )
	@Pattern(regexp = "(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]*", groups = {GroupA.class, GroupC.class}, message = "パスワードは文字と数字を含む必要があります。")
	private String password;
	//ユーザー名
	@Length(groups = {GroupA.class, GroupB.class, GroupC.class}, min=1, max= 25, message="{min}文字以上{max}文字以内で入力してください。")
	private String userName;
	//性別
	private String gender;
	//生年月日
	@Past(groups = {GroupA.class}, message= "無効な値が入力されています。")
	private Date birthday;
	//削除フラグ
	private boolean isDeleted;

	public String getUserLoginId() {
		return userLoginId;
	}

	public void setUserLoginId(String userLoginId) {
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
