package controller.customer;

import java.io.IOException;
import java.util.ArrayList;

import bean.PurchaseBean;
import bean.PurchaseDetailBean;
import classes.ErrorHandling;
import classes.Purchase;
import classes.PurchaseDetail;
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
		
		// インスタンス化
		CustomerUser customerUser = new CustomerUser();
		PurchaseBean purchaseBean = new PurchaseBean();
		
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
		
		// 購入IDをセット
		purchaseBean.setPurchaseId(purchase_id);
		
		// 1件の購入情報を取得
		PurchaseBean purchaseInfo = Purchase.getPurchaseInfo(purchaseBean);
		
		// PurchaseDetailBeanにセット
		PurchaseDetailBean purchaseDetailBean = new PurchaseDetailBean();
		purchaseDetailBean.setPurchaseId(purchase_id);
		
		// 購入情報詳細取得
		ArrayList<PurchaseDetailBean> purchaseDetailList = PurchaseDetail.getPurchaseDetail(purchaseDetailBean);
		
		if(purchaseInfo == null || purchaseDetailList == null) {
			// エラー画面に遷移
			ErrorHandling.transitionToErrorPage(request,response,"購入情報の取得に失敗しました。","home","ホームに");
			return;
		}
		
		request.setAttribute("purchase_id", purchase_id);
		request.setAttribute("purchaseInfo", purchaseInfo);
		request.setAttribute("purchaseDetailList", purchaseDetailList);
		
		// 購入詳細画面表示
		String view = "/WEB-INF/views/customer/orderDetail.jsp";
		request.getRequestDispatcher(view).forward(request, response);
		
	}
}
