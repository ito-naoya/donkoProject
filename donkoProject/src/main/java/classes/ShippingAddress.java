package classes;

import java.util.ArrayList;

import bean.ShippingAddressBean;
import classes.user.CustomerUser;
import model.shippingAddresses.shippingAddressesDelete.DeleteIteShippingAddresses;
import model.shippingAddresses.shippingAddressesInsert.InsertNewShippingAddress;
import model.shippingAddresses.shippingAddressesSelect.SelectMainShippingAddress;
import model.shippingAddresses.shippingAddressesSelect.SelectMainShippingAddressCount;
import model.shippingAddresses.shippingAddressesSelect.SelectMainShippingAddressSort;
import model.shippingAddresses.shippingAddressesSelect.SelectShippingAddressDetail;
import model.shippingAddresses.shippingAddressesSelect.SelectShippingAddressList;
import model.shippingAddresses.shippingAddressesUpdate.UpdateMainShippingAddress;
import model.shippingAddresses.shippingAddressesUpdate.UpdateShippingAddress;

public class ShippingAddress {
	
	//新規配送先を登録する
	public static boolean registerNewShippingAddress(ShippingAddressBean shippingAddressBean){
		return InsertNewShippingAddress.insertNewShippingAddress(shippingAddressBean);
	};
	
	//メインの配送先を更新する
	public static Boolean updateMainShippingAddress(ShippingAddressBean shippingAddressBean){
		return UpdateMainShippingAddress.updateMainShippingAddress(shippingAddressBean);
	};
	
	//メインのお届け先を取得する
	public static ShippingAddressBean getMainShippingAddress(CustomerUser customerUser){
		return SelectMainShippingAddress.selectMainShippingAddress(customerUser);
	};
	
	//配送住所の詳細情報を取得する
	public static ShippingAddressBean getShippingAddressDetail(ShippingAddressBean shippingAddressBean){
		return SelectShippingAddressDetail.selectShippingAddressDetail(shippingAddressBean);
	};
	
	//配送先情報を更新する
	public static Boolean updateShippingAddress(ShippingAddressBean shippingAddressBean){
		return UpdateShippingAddress.updateShippingAddress(shippingAddressBean);
	};
	
	//配送住所の一覧を取得する
	public static ArrayList<ShippingAddressBean> getShippingAddressList(CustomerUser customerUser){
		return SelectShippingAddressList.selectShippingAddressList(customerUser);
	};
	
	//配送先一覧をメインの配送先順にソートして取得する
	public static ArrayList<ShippingAddressBean> getMainShippingAddressSort(CustomerUser customerUser){
		return SelectMainShippingAddressSort.selectMainShippingAddressSort(customerUser);
	};
	
	//配送先を削除
	public static Boolean deleteShippingAddresses(ShippingAddressBean shippingAddressBean){
		return DeleteIteShippingAddresses.deleteUpdateShippingAddress(shippingAddressBean);
	};
	
	// メイン配送先の件数をカウント
	public static Boolean selectMainShippingAddressCount(CustomerUser customerUser){
		return SelectMainShippingAddressCount.selectMainShippingAddressCounts(customerUser);
	};
}
