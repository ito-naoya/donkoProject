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
import jakarta.servlet.http.HttpSession;

@WebServlet("/adminheader")
public class AdminHeaderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AdminHeaderServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession as = request.getSession();
		String adminSession = (String)as.getAttribute("admin");
		if (adminSession != null) {
			// 未発送の購入情報を一覧表示
			ArrayList<PurchaseBean> unshippingedItemList = Purchase.getUnshippingedItemListByDesc();
			request.setAttribute("unshippingnum", unshippingedItemList);
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession as = request.getSession();
		String adminSession = (String)as.getAttribute("admin");
		if (adminSession != null) {
			// 未発送の購入情報を一覧表示
			ArrayList<PurchaseBean> unshippingedItemList = Purchase.getUnshippingedItemListByDesc();
			request.setAttribute("unshippingnum", unshippingedItemList);
		}
	}
}
