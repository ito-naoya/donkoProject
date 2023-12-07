package bean;

public class ItemBean {
	
	private int itemId;
	private String itemCategoryName;
	private String itemName;
	private String itemDescription;
	private int itemPrice;
	private int itemStock;
	private boolean isDeleted;
	private String imageFileName;
	private String itemFirstOptionName;
	private String itemDecondOptionName;
	private String itemFirstOptionValue;
	private String itemSecondOptionValue;
	
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
	
	public String getItemDecondOptionName() {
		return itemDecondOptionName;
	}
	
	public void setItemDecondOptionName(String itemDecondOptionName) {
		this.itemDecondOptionName = itemDecondOptionName;
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
	
}
