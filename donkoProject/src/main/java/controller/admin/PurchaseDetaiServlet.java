package controller.admin;

import java.io.IOException;
import java.util.ArrayList;

import bean.PurchaseBean;
import bean.PurchaseDetailBean;
import classes.Purchase;
import classes.PurchaseDetail;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/purchaseDetail")
public class PurchaseDetaiServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public PurchaseDetaiServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		int purchaseId = Integer.parseInt(request.getParameter("purchaseId"));
		PurchaseDetailBean purchaseDetailBean = new PurchaseDetailBean();
		PurchaseBean purchaseBean = new PurchaseBean();
		purchaseDetailBean.setPurchaseId(4);
		purchaseBean.setPurchaseId(4);
		
		// 購入IDをもとに購入情報を取得
		ArrayList<PurchaseBean> purchaseInfo = Purchase.getPurchaseInfo(purchaseBean);
		// 購入IDをもとに購入詳細情報を取得
		ArrayList<PurchaseDetailBean> purchaseDetailList =PurchaseDetail.getPurchaseDetail(purchaseDetailBean);
		
		request.setAttribute("purchaseInfo", purchaseInfo);
		request.setAttribute("purchaseDetailList", purchaseDetailList);

		String view = "/WEB-INF/views/admin/purchaseDetail.jsp";
        request.getRequestDispatcher(view).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 発送する機能をつける
		doGet(request, response);
	}

}
