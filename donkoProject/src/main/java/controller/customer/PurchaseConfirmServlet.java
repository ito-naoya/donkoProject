package controller.customer;

import java.io.IOException;
import java.util.ArrayList;

import bean.CartBean;
import bean.ShippingAddressBean;
import classes.Cart;
import classes.ShippingAddress;
import classes.user.CustomerUser;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/purchaseConfirm")
public class PurchaseConfirmServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public PurchaseConfirmServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
//		HttpSession session = request.getSession();
//		CustomerUser loginedUser = (CustomerUser)session.getAttribute("customerUser");
//		
//		if(loginedUser == null) {
//			response.sendRedirect("login");
//			return;
//		}

		CustomerUser loginedUser = new CustomerUser();
		loginedUser.setUserId(2);
		
		ShippingAddressBean shippingAddress = ShippingAddress.getMainShippingAddress(loginedUser);
		ArrayList<CartBean> cartBeanList = Cart.getItemListFromCart(loginedUser);
		
		request.setAttribute("shippingAddress", shippingAddress);
		request.setAttribute("cartBeanList", cartBeanList);
		
		String view = "/WEB-INF/views/customer/purchaseConfirm.jsp";
		request.getRequestDispatcher(view).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String view = "/WEB-INF/views/customer/purchaseComplete.jsp";
		request.getRequestDispatcher(view).forward(request, response);
	}

}
