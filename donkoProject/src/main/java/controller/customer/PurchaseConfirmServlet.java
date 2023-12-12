package controller.customer;

import java.io.IOException;
import java.util.ArrayList;

import bean.CartBean;
import bean.PurchaseBean;
import bean.ShippingAddressBean;
import classes.Cart;
import classes.Purchase;
import classes.PurchaseDetail;
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
		
		//テストコード
		CustomerUser loginedUser = new CustomerUser(); 
		loginedUser.setUserId(2);
		
		ShippingAddressBean shippingAddress = ShippingAddress.getMainShippingAddress(loginedUser);
		ArrayList<CartBean> cartList = Cart.getItemListFromCart(loginedUser);
		
		ArrayList<Integer> priceList = new ArrayList<Integer>();
		cartList.forEach(cb -> {
			priceList.add(cb.getQuantity() * cb.getItemPrice());		
		});
		Integer totalPrice = priceList.stream().mapToInt( i -> i ).sum();
		
		request.setAttribute("shippingAddress", shippingAddress);
		request.setAttribute("cartList", cartList);
		request.setAttribute("totalPrice", totalPrice);
		
		String view = "/WEB-INF/views/customer/purchaseConfirm.jsp";
		request.getRequestDispatcher(view).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
//		HttpSession session = request.getSession();
//		CustomerUser loginedUser = (CustomerUser)session.getAttribute("customerUser");
//		
//		if(loginedUser == null) {
//			response.sendRedirect("login");
//			return;
//		}

		//テストコード
		CustomerUser loginedUser = new CustomerUser(); 
		loginedUser.setUserId(2);

		int totalPrice = Integer.parseInt(request.getParameter("totalPrice"));
		int shippingAddressId = Integer.parseInt(request.getParameter("shippingAddressId"));

		PurchaseBean pb = new PurchaseBean();
		pb.setUserId(loginedUser.getUserId());
		pb.setTotalAmount(totalPrice);
		pb.setShippingAddressId(shippingAddressId);

		Purchase.purchaseItem(pb);
		PurchaseDetail.addPurchaseDetail(pb);
		Cart.deleteAllItemFromCart(loginedUser);
		
		String view = "/WEB-INF/views/customer/purchaseComplete.jsp";
		request.getRequestDispatcher(view).forward(request, response);
	}

}
