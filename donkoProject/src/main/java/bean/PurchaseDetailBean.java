package bean;

public class PurchaseDetailBean {
	
	private int purchaseDetailId;
	private String userName;
	private int purchaseId;
	private int itemId;
	private String itemName;
	private int purchaseAmount;
	private int quantity;
	private String imageFileName;
	private String optionCategoryValue;
	private int price;
	
	public PurchaseDetailBean() {};
	
	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	
	public String getOptionCategoryValue() {
		return optionCategoryValue;
	}

	public void setOptionCategoryValue(String optionCategoryValue) {
		this.optionCategoryValue = optionCategoryValue;
	}

	public int getPurchaseDetailId() {
		return purchaseDetailId;
	}
	
	public void setPurchaseDetailId(int purchaseDetailId) {
		this.purchaseDetailId = purchaseDetailId;
	}
	
	public String getUserName() {
		return userName;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public int getPurchaseId() {
		return purchaseId;
	}
	
	public void setPurchaseId(int purchaseId) {
		this.purchaseId = purchaseId;
	}
	
	public int getItemId() {
		return itemId;
	}
	
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
	
	public String getItemName() {
		return itemName;
	}
	
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	
	public int getPurchaseAmount() {
		return purchaseAmount;
	}
	
	public void setPurchaseAmount(int purchaseAmount) {
		this.purchaseAmount = purchaseAmount;
	}
	
	public int getQuantity() {
		return quantity;
	}
	
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public String getImageFileName() {
		return imageFileName;
	}
	
	public void setImageFileName(String imageFileName) {
		this.imageFileName = imageFileName;
	}
}
