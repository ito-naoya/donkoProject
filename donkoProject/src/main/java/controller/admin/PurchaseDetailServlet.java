package controller.admin;

import java.io.IOException;
import java.util.ArrayList;

import bean.PurchaseBean;
import bean.PurchaseDetailBean;
import classes.ErrorHandling;
import classes.Purchase;
import classes.PurchaseDetail;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/purchaseDetail")
public class PurchaseDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public PurchaseDetailServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession as = request.getSession();
		String adminSession = (String)as.getAttribute("admin");
		
		// アドミンのセッションがnullの場合は管理者ログイン画面に遷移
		if (adminSession == null) {
			response.sendRedirect("adminSignin");
			return;
		}
		
		// purchaseIdがリクエストパラメータに存在しない場合はアドミンのホーム画面に遷移
		if (request.getParameter("purchaseId") == null) {
			response.sendRedirect("adminTopPage");
			return;
		}
		
		int purchaseId = Integer.parseInt(request.getParameter("purchaseId"));
		
		// Beanに値をセット
		PurchaseBean purchaseBean = new PurchaseBean();
		PurchaseDetailBean purchaseDetailBean = new PurchaseDetailBean();
		purchaseBean.setPurchaseId(purchaseId);
		purchaseDetailBean.setPurchaseId(purchaseId);
		
		// 購入IDをもとに購入情報を取得
		PurchaseBean purchaseInfo = Purchase.getPurchaseInfo(purchaseBean);
		
		if (purchaseInfo == null) {
	        //エラーページに遷移
	        ErrorHandling.transitionToErrorPage(request, response, "購入情報の取得に失敗しました","adminTopPage","管理者ページに");
			return;
		}
		
		// 購入IDをもとに購入詳細情報を取得
		ArrayList<PurchaseDetailBean> purchaseDetailList = PurchaseDetail.getPurchaseDetail(purchaseDetailBean);
		
		if (purchaseDetailList == null) {
	        //エラーページに遷移
	        ErrorHandling.transitionToErrorPage(request, response, "購入詳細情報の取得に失敗しました","adminTopPage","管理者ページに");
			return;
		}
		
		request.setAttribute("purchaseInfo", purchaseInfo);
		request.setAttribute("purchaseDetailList", purchaseDetailList);

		String view = "/WEB-INF/views/admin/purchaseDetail.jsp";
        request.getRequestDispatcher(view).forward(request, response);
	}
}
