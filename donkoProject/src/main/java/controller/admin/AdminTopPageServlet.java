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

@WebServlet("/adminTopPage")
public class AdminTopPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AdminTopPageServlet() {
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
		
		// 未発送の購入情報を一覧表示
		ArrayList<PurchaseBean> unshippingedItemList = Purchase.getUnshippingedItemListByDesc();

		if (unshippingedItemList == null) {
	        ErrorHandling.transitionToErrorPage(request, response, "データの取得に失敗しました","adminTopPage","管理者ページに");
			return;
		} else if (unshippingedItemList.size() > 0) {
			request.setAttribute("unshippingedItemList", unshippingedItemList);
		} else {
			request.setAttribute("message", "現在未発送の商品はありません");
		}

		// 遷移先
		String view = "/WEB-INF/views/admin/adminTopPage.jsp";
        request.getRequestDispatcher(view).forward(request, response);
	}
}
