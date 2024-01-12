package controller.admin;

import java.io.IOException;
import java.util.ArrayList;

import bean.PurchaseBean;
import classes.ErrorHandling;
import classes.Purchase;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/purchaseHistory")
public class PurchaseHistoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public PurchaseHistoryServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// ヘッダーに表示する値を取得
		String disp = "/adminheader";
	    RequestDispatcher dispatch = request.getRequestDispatcher(disp);
	    dispatch.include(request, response);
		
		HttpSession as = request.getSession();
		String adminSession = (String)as.getAttribute("admin");
		
		// アドミンのセッションがnullの場合は管理者ログイン画面に遷移
		if (adminSession == null) {
			response.sendRedirect("adminSignin");
			return;
		}
		
		// 全ての受注情報を取得
		ArrayList<PurchaseBean> orderItemList = Purchase.getOrderItemList();
		
		if(orderItemList == null) {
			 //エラーページに遷移
	        ErrorHandling.transitionToErrorPage(request, response, "受注情報の取得に失敗しました","adminTopPage","管理者ページに");
			return;
		}
		
		if (orderItemList.size() > 0) {
			request.setAttribute("orderItemList", orderItemList);
		} else {
			request.setAttribute("message", "データが存在しません");
		}
		//遷移先
		String view = "/WEB-INF/views/admin/purchaseHistory.jsp";
        request.getRequestDispatcher(view).forward(request, response);
	}
}
