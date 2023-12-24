package bean;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import interfaces.group.GroupA;
import interfaces.group.GroupB;
import interfaces.group.GroupC;

public class ItemBean {


	private int itemId;

	@Length(groups = {GroupA.class}, min=1 , max= 20, message="{min}文字以上{max}文字以下で入力してください。" )
	@NotNull(groups = {GroupA.class}, message = "カテゴリーを選択してください")
	private String itemCategoryName;

	@Length(groups = {GroupA.class}, min=1 , max= 30, message="{min}文字以上{max}文字以下で入力してください。" )
	private String itemName;

	@Length(groups = {GroupA.class}, min=1 , max= 100, message="{min}文字以上{max}文字以下で入力してください。" )
	private String itemDescription;

	@Min(groups = {GroupA.class}, value = 1, message = "¥1~10,000,000,000で入力してください。")
    @Max(groups = {GroupA.class}, value = 1000000000, message = "¥1~10,000,000,000で入力してください。")
	private int itemPrice;

	@Min(groups = {GroupA.class}, value = 0, message = "0~9で入力してください。")
    @Max(groups = {GroupA.class}, value = 9, message = "0~9で入力してください。")
    private int itemStock;

	private boolean isDeleted;

	@Length(groups = {GroupB.class,GroupC.class}, min=1 , max= 35,  message="写真を登録できません")
	private String imageFileName;

	@Length(groups = {GroupC.class}, min=1 , max= 20, message="正しい値を選択してください" )
	private String itemFirstOptionName;
	@Length(groups = {GroupB.class,GroupC.class}, min=1 , max= 20, message="正しい値を選択してください" )
	private String itemSecondOptionName;

	private String itemFirstOptionValue;
	private String itemSecondOptionValue;

	@Min(groups = {GroupB.class,GroupC.class}, value = 1, message = "正しい値を選択してください")
    @Max(groups = {GroupB.class,GroupC.class}, value = 1000000000, message = "正しい値を選択してください")
	private int itemFirstOptionIncrementId;
    private int itemSecondOptionIncrementId;

	public ItemBean(){};

	public int getItemId() {
		return itemId;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

	public String getItemCategoryName() {
		return itemCategoryName;
	}

	public void setItemCategoryName(String itemCategoryName) {
		this.itemCategoryName = itemCategoryName;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getItemDescription() {
		return itemDescription;
	}

	public void setItemDescription(String itemDescription) {
		this.itemDescription = itemDescription;
	}

	public int getItemPrice() {
		return itemPrice;
	}

	public void setItemPrice(int itemPrice) {
		this.itemPrice = itemPrice;
	}

	public int getItemStock() {
		return itemStock;
	}

	public void setItemStock(int itemStock) {
		this.itemStock = itemStock;
	}

	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public String getImageFileName() {
		return imageFileName;
	}

	public void setImageFileName(String imageFileName) {
		this.imageFileName = imageFileName;
	}

	public String getItemFirstOptionName() {
		return itemFirstOptionName;
	}

	public void setItemFirstOptionName(String itemFirstOptionName) {
		this.itemFirstOptionName = itemFirstOptionName;
	}

	public String getItemSecondOptionName() {
		return itemSecondOptionName;
	}

	public void setItemSecondOptionName(String itemDecondOptionName) {
		this.itemSecondOptionName = itemDecondOptionName;
	}

	public String getItemFirstOptionValue() {
		return itemFirstOptionValue;
	}

	public void setItemFirstOptionValue(String itemFirstOptionValue) {
		this.itemFirstOptionValue = itemFirstOptionValue;
	}

	public String getItemSecondOptionValue() {
		return itemSecondOptionValue;
	}

	public void setItemSecondOptionValue(String itemSecondOptionValue) {
		this.itemSecondOptionValue = itemSecondOptionValue;
	}

	public int getItemFirstOptionIncrementId() {
        return itemFirstOptionIncrementId;
	}

	public void setItemFirstOptionIncrementId(int itemFirstOptionIncrementId) {
	        this.itemFirstOptionIncrementId = itemFirstOptionIncrementId;
	}

	public int getItemSecondOptionIncrementId() {
	        return itemSecondOptionIncrementId;
	}

	public void setItemSecondOptionIncrementId(int itemSecondOptionIncrementId) {
	        this.itemSecondOptionIncrementId = itemSecondOptionIncrementId;
	}
}
