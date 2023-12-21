package controller.customer;

import java.io.IOException;
import java.util.ArrayList;

import bean.PurchaseBean;
import bean.PurchaseDetailBean;
import bean.ShippingAddressBean;
import classes.Purchase;
import classes.PurchaseDetail;
import classes.ShippingAddress;
import classes.user.CustomerUser;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/orderDetail")
public class OrderDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public OrderDetailServlet() {
		super();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// ユーザーID取得のためにインスタンス化
		CustomerUser customerUser = new CustomerUser();
		PurchaseBean purchaseBean = new PurchaseBean();
		ShippingAddressBean shippingAddressBean = new ShippingAddressBean();
		
		// セッション確認
		HttpSession session = request.getSession(false);
		if(session == null) {
			response.sendRedirect("home");
			return;
		} else {
			// ユーザーIDを取得してセット
			customerUser.setUserId((int)session.getAttribute("user_id"));
		}
		
		// 購入ID取得 
		int purchase_id = Integer.parseInt(request.getParameter("purchase_id"));
		request.setAttribute("purchase_id", purchase_id);
		
		// 購入IDをセット
		purchaseBean.setPurchaseId(purchase_id);
		
		// 1件の購入情報を取得
		PurchaseBean purchaseInfo = Purchase.getPurchaseInfo(purchaseBean);
		request.setAttribute("purchaseInfo", purchaseInfo);
		
		// ShippingAddressBeanにセット
		shippingAddressBean.setUserId(customerUser.getUserId());
		shippingAddressBean.setShippingAddressId(purchaseInfo.getShippingAddressId());
		
		// 配送先詳細情報取得
		ShippingAddressBean shippingAddressInfo = ShippingAddress.getShippingAddressDetail(shippingAddressBean);
		request.setAttribute("shippingAddressInfo", shippingAddressInfo);
		
		// PurchaseDetailBeanにセット
		PurchaseDetailBean purchaseDetailBean = new PurchaseDetailBean();
		purchaseDetailBean.setPurchaseId(purchase_id);
		
		// 購入情報詳細取得
		ArrayList<PurchaseDetailBean> purchaseDetailList = PurchaseDetail.getPurchaseDetail(purchaseDetailBean);
		request.setAttribute("purchaseDetailList", purchaseDetailList);
		
		// 購入詳細画面表示
		String view = "/WEB-INF/views/customer/orderDetail.jsp";
		request.getRequestDispatcher(view).forward(request, response);
		
	}
}
