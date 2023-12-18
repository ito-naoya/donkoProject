package classes;

import java.util.ArrayList;

import bean.PurchaseBean;
import classes.user.CustomerUser;
import model.purchases.purchasesInsert.InsertPurchaseToPurchases;
import model.purchases.purchasesSelect.SelectMyPurchaseHistory;
import model.purchases.purchasesSelect.SelectOrderItemList;
import model.purchases.purchasesSelect.SelectPurchaseInfo;
import model.purchases.purchasesSelect.SelectUnshippingItemListByDesc;
import model.purchases.purchasesUpdate.UpdateShippingStatus;

public class Purchase {
	
	//未発送の商品一覧を降順で取得する
	public static ArrayList<PurchaseBean> getUnshippingedItemListByDesc(){
		return SelectUnshippingItemListByDesc.selectUnshippingItemListByDesc();
	};
	
	//受注一覧を取得する
	public static ArrayList<PurchaseBean> getOrderItemList(){
		return SelectOrderItemList.selectOrderItemList();
	};
	
	//一件の購入情報を取得する
	public static PurchaseBean getPurchaseInfo(PurchaseBean purchaseBean){
		return SelectPurchaseInfo.selectPurchaseInfo(purchaseBean);
	};
	
	//購入履歴の一覧を取得する
	public static ArrayList<PurchaseBean> getMyPurchaseHistory(CustomerUser customerUser){
		return SelectMyPurchaseHistory.selectMyPurchaseHistory(customerUser);
	};
	
	//商品を購入する
	public static void purchaseItem(PurchaseBean purchaseBean){
		InsertPurchaseToPurchases.insertPurchaseToPurchases(purchaseBean);
	};
	
	//商品の発送処理をする
	public static void sendItems(PurchaseBean purchaseBean){
		UpdateShippingStatus.updateShippingStatus(purchaseBean);
	};

}
