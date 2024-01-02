package bean;

import org.hibernate.validator.constraints.Length;

import interfaces.group.GroupA;
import interfaces.group.GroupB;

public class OptionCategoryBean {

	@Length(groups = {GroupA.class}, min=1 , max= 20, message="{min}文字以上{max}文字以下で入力してください。" )
	private String optionCategoryName;
	@Length(groups = {GroupB.class}, min=1 , max= 20, message="{min}文字以上{max}文字以下で入力してください。" )
	private String optionCategoryValue;
	private int optionCategoryId;

	public OptionCategoryBean() {};

	public String getOptionCategoryName() {
		return optionCategoryName;
	}

	public void setOptionCategoryName(String optionCategoryName) {
		this.optionCategoryName = optionCategoryName;
	}

	public String getOptionCategoryValue() {
		return optionCategoryValue;
	}

	public void setOptionCategoryValue(String optionCategoryValue) {
		this.optionCategoryValue = optionCategoryValue;
	}

	public int getOptionCategoryId() {
		return optionCategoryId;
	}

	public void setOptionCategoryId(int optionCategoryId) {
		this.optionCategoryId = optionCategoryId;
	}

}
