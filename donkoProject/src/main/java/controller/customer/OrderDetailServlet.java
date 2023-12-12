package controller.customer;

import java.io.IOException;
import java.util.ArrayList;

import bean.ItemBean;
import bean.PurchaseDetailBean;
import classes.Item;
import classes.PurchaseDetail;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/orderDetail")
public class OrderDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public OrderDetailServlet() {
		super();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 購入ID取得 
		int purchase_id = Integer.parseInt(request.getParameter("purchase_id"));
		request.setAttribute("purchase_id", purchase_id);
		
		// 購入情報詳細取得
		PurchaseDetailBean purchaseDetailBean = new PurchaseDetailBean();
		purchaseDetailBean.setPurchaseId(purchase_id);
		
		ArrayList<PurchaseDetailBean> purchaseDetailList = PurchaseDetail.getPurchaseDetail(purchaseDetailBean);
		request.setAttribute("purchaseDetailList", purchaseDetailList);
		
		// 商品画像取得処理
		ArrayList<ItemBean> itemImageList = null;
		for (PurchaseDetailBean itemIds : purchaseDetailList) {
			// item_idを取得してセット
			int item_id = itemIds.getItemId();
			request.setAttribute("item_id", item_id);
			
			// ItemBeanにて画像名を取得
			ItemBean itemBean = new ItemBean();
			itemBean.setItemId(item_id);
			itemImageList = Item.getItemImageList(itemBean);
			System.out.println(itemImageList);
			// itemBean.setImageFileName(cachedAllowHeaderValue);
			String imageFileName = itemBean.getImageFileName();
			System.out.println(imageFileName);
//			itemImageList.add(imageFileName);
		}
		// 画像名
		request.setAttribute("itemImageList", itemImageList);
		
		// 購入う詳細画面表示
		String view = "/WEB-INF/views/customer/orderDetail.jsp";
		request.getRequestDispatcher(view).forward(request, response);
		
	}
}
