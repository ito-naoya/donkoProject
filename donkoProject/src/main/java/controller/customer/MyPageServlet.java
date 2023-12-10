package controller.customer;

import java.io.IOException;
import java.util.ArrayList;

import bean.PurchaseBean;
import classes.Purchase;
import classes.user.CustomerUser;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/myPage")
public class MyPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public MyPageServlet() {
		super();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		CustomerUser customerUser = new CustomerUser();
		// TODO:実装が問題なければこのコードは削除予定
		customerUser.setUserLoginId(Integer.parseInt("2"));
		
		ArrayList<PurchaseBean> purchaseList = Purchase.getMyPurchaseHistory(customerUser);
		request.setAttribute("purchaseList", purchaseList);
		System.out.println(purchaseList);
		
		String view = "/WEB-INF/views/customer/myPage.jsp";
		request.getRequestDispatcher(view).forward(request, response);
	}
}