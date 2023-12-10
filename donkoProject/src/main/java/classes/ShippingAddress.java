package classes;

import java.util.ArrayList;

import bean.ShippingAddressBean;
import classes.user.CustomerUser;
import model.shippingAddresses.shippingAddressesInsert.InsertNewShippingAddress;
import model.shippingAddresses.shippingAddressesSelect.SelectDefaultShippingAddress;
import model.shippingAddresses.shippingAddressesSelect.SelectShippingAddressDetail;
import model.shippingAddresses.shippingAddressesSelect.SelectShippingAddressList;
import model.shippingAddresses.shippingAddressesUpdate.UpdateDefaultShippingAddress;
import model.shippingAddresses.shippingAddressesUpdate.UpdateShippingAddress;

public class ShippingAddress {
	
	//新規配送先を登録する
	public static void registerNewShippingAddress(ShippingAddressBean shippingAddressBean){
		InsertNewShippingAddress.insertNewShippingAddress(shippingAddressBean);
	};
	
	//デフォルトの配送先を更新する
	public static void updateDefaultShippingAddress(ShippingAddressBean shippingAddressBean){
		UpdateDefaultShippingAddress.updateDefaultShippingAddress(shippingAddressBean);
	};
	
	//デフォルトのお届け先を取得する
	public static ShippingAddressBean getDefaultShippingAddress(CustomerUser customerUser){
		return SelectDefaultShippingAddress.selectDefaultShippingAddress(customerUser);
	};
	
	//配送住所の詳細情報を取得する
	public static ShippingAddressBean getShippingAddressDetail(ShippingAddressBean shippingAddressBean){
		return SelectShippingAddressDetail.selectShippingAddressDetail(shippingAddressBean);
	};
	
	//配送先情報を更新する
	public static void updateShippingAddress(ShippingAddressBean shippingAddressBean){
		UpdateShippingAddress.updateShippingAddress(shippingAddressBean);
	};
	
	//配送住所の一覧を取得する
	public static ArrayList<ShippingAddressBean> getShippingAddressList(CustomerUser customerUser){
		return SelectShippingAddressList.selectShippingAddressList(customerUser);
	};
	

}
