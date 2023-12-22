package classes.user;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

public class CustomerUser extends User {

	public CustomerUser() {
	};

	//ログインID
	@NotNull(message= "入力必須項目です")
	@Length(min= 5 , max= 10, message="{min}以上{max}以下で入力してください。" )
	private String userLoginId;
	//パスワード
	@Range(min=8, max=8, message="８文字で入力してください。" ) 
	@NotNull(message= "入力必須項目です")
	private String password;
	//ユーザー名
	@NotNull(message= "入力必須項目です")
	@Length(min=1, max= 25, message="{max}以内で入力してください。") 
	private String userName;
	//性別
	private String gender;
	//生年月日
	@Past(message= "不正な値が入力されています。")
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
