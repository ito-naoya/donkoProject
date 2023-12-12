package classes;

import java.util.ArrayList;

import bean.PurchaseBean;
import bean.PurchaseDetailBean;
import model.purchaseDetails.purchaseDetailsInsert.InsertPurchaseDetail;
import model.purchaseDetails.purchaseDetailsSelect.SelectPurchaseDetail;

public class PurchaseDetail {

	//購入の詳細情報を取得する
	public static ArrayList<PurchaseDetailBean> getPurchaseDetail(PurchaseDetailBean purchaseDetailBean) {
		return SelectPurchaseDetail.selectPurchaseDetail(purchaseDetailBean);
	};

	//購入詳細を追加する
	public static void addPurchaseDetail(PurchaseBean purchaseBean){
		InsertPurchaseDetail.insertPurchaseDetail(purchaseBean);
	};

}
