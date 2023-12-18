package controller.customer;

import java.io.IOException;
import java.util.ArrayList;

import bean.ItemBean;
import bean.PurchaseBean;
import classes.Item;
import classes.Purchase;
import classes.user.CustomerUser;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/myPage")
public class MyPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public MyPageServlet() {
		super();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession(false);
		if(session == null) {
			ArrayList<ItemBean> itemList = Item.getItemList();
			request.setAttribute("itemList", itemList);
			String view = "/WEB-INF/views/customer/home.jsp";
	        request.getRequestDispatcher(view).forward(request, response);
		}
		
		CustomerUser customerUser = new CustomerUser();
		// TODO: testUserIdはテストコードです
		int testUserId = 2;
		session.setAttribute("user_id", testUserId);
		int userId = (int) session.getAttribute("user_id");
		customerUser.setUserId(userId);
		// TODO:あとで書き換え↓
		// customerUser.setUserId((int)session.getAttribute("user_id"));
		
		ArrayList<PurchaseBean> purchaseList = Purchase.getMyPurchaseHistory(customerUser);
		request.setAttribute("purchaseList", purchaseList);
		
		String view = "/WEB-INF/views/customer/myPage.jsp";
		request.getRequestDispatcher(view).forward(request, response);
	}
}