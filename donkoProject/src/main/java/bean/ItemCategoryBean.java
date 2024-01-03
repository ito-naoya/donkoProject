package bean;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import interfaces.group.GroupA;

public class ItemCategoryBean {

	@Length(groups = {GroupA.class}, min=1 , max= 20, message="{min}文字以上{max}文字以下で入力してください。" )
	private String itemCategoryName;
	@NotNull(groups = {GroupA.class}, message = "オプションを選択してください")
	private String optionCategoryName;

	public ItemCategoryBean() {};

	public String getItemCategoryName() {
		return itemCategoryName;
	}

	public void setItemCategoryName(String itemCategoryName) {
		this.itemCategoryName = itemCategoryName;
	}

	public String getOptionCategoryName() {
		return optionCategoryName;
	}

	public void setOptionCategoryName(String optionCategoryName) {
		this.optionCategoryName = optionCategoryName;
	}

}
