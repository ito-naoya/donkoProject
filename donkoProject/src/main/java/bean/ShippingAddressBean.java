package bean;

public class ShippingAddressBean {
	
	private int shippingAddressId;
	private int userId;
	private String postalCode;
	private String addressee;
	private String address;
	
	public ShippingAddressBean() {};
	
	public int getShippingAddressId() {
		return shippingAddressId;
	}
	
	public void setShippingAddressId(int shippingAddressId) {
		this.shippingAddressId = shippingAddressId;
	}
	
	public int getUserId() {
		return userId;
	}
	
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	public String getPostalCode() {
		return postalCode;
	}
	
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
	
	public String getAddressee() {
		return addressee;
	}
	
	public void setAddressee(String addressee) {
		this.addressee = addressee;
	}
	
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
}
