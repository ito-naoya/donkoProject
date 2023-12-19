package controller.admin;

import java.io.IOException;
import java.util.ArrayList;

import bean.PurchaseBean;
import classes.Purchase;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/adminTopPage")
public class AdminTopPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AdminTopPageServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 未発送の購入情報を一覧表示
		ArrayList<PurchaseBean> unshippingedItemList = Purchase.getUnshippingedItemListByDesc();
		if (unshippingedItemList.size() > 0) {
			request.setAttribute("unshippingedItemList", unshippingedItemList);
		} else {
			request.setAttribute("message", "現在未発送の商品はありません");
		}
		// 遷移先
		String view = "/WEB-INF/views/admin/adminTopPage.jsp";
        request.getRequestDispatcher(view).forward(request, response);
	}
}
