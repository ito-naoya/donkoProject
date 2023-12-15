package bean;

import java.sql.Timestamp;

public class PurchaseBean {
	
	private int purchaseId;
	private int userId;
	private int shippingAddressId;
	private String shippingAddress;
	private int shippingId;;
	private String shippingStatus;
	private int totalAmount;
	private Timestamp purchaseDate;
	
	public PurchaseBean(){};
	
	public int getPurchaseId() {
		return purchaseId;
	}
	
	public void setPurchaseId(int purchaseId) {
		this.purchaseId = purchaseId;
	}
	
	public int getUserId() {
		return userId;
	}
	
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	public int getShippingAddressId() {
		return shippingAddressId;
	}
	
	public void setShippingAddressId(int shippingAddressId) {
		this.shippingAddressId = shippingAddressId;
	}
	
	public String getShippingAddress() {
		return shippingAddress;
	}
	
	public void setShippingAddress(String shippingAddress) {
		this.shippingAddress = shippingAddress;
	}
	
	public int getShippingId() {
		return shippingId;
	}
	
	public void setShippingId(int shippingId) {
		this.shippingId = shippingId;
	}
	
	public String getShippingStatus() {
		return shippingStatus;
	}
	
	public void setShippingStatus(String shippingStatus) {
		this.shippingStatus = shippingStatus;
	}
	
	public int getTotalAmount() {
		return totalAmount;
	}
	
	public void setTotalAmount(int totalAmount) {
		this.totalAmount = totalAmount;
	}
	
	public Timestamp getPurchaseDate() {
		return purchaseDate;
	}
	
	public void setPurchaseDate(Timestamp purchaseDate) {
		this.purchaseDate = purchaseDate;
	}
	
	

}
