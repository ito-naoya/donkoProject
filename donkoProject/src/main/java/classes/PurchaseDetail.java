package classes;

import java.util.ArrayList;

import bean.PurchaseDetailBean;
import model.purchaseDetails.purchaseDetailsSelect.SelectPurchaseDetail;

public class PurchaseDetail {

	//購入の詳細情報を取得する
	public static ArrayList<PurchaseDetailBean> getPurchaseDetail(PurchaseDetailBean purchaseDetailBean) {
		return SelectPurchaseDetail.selectPurchaseDetail(purchaseDetailBean);
	};

}
