package controller.customer;

import java.io.IOException;
import java.util.ArrayList;

import bean.CartBean;
import bean.ItemBean;
import classes.Cart;
import classes.Item;
import classes.user.CustomerUser;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/cart")
public class CartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public CartServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//		HttpSession session = request.getSession();
		//		CustomerUser loginedUser = (CustomerUser)session.getAttribute("user");
		//		
		//		if(loginedUser == null) {
		//			response.sendRedirect("login");
		//			return;
		//		}

		//テストコード
		CustomerUser loginedUser = new CustomerUser();
		loginedUser.setUserId(2);

		ArrayList<CartBean> cartList = Cart.getItemListFromCart(loginedUser);
		
		cartList.forEach(cb -> {
			ItemBean ib = new ItemBean();
			ib.setItemId(cb.getItemId());
			ItemBean itemOptionDetail =  Item.getItemDetailOption(ib);
			cb.setItemOptionDetail(itemOptionDetail.getItemFirstOptionValue());
		});
		
		request.setAttribute("cartList", cartList);

		String view = "/WEB-INF/views/customer/cart.jsp";
		request.getRequestDispatcher(view).forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//		HttpSession session = request.getSession();
		//		CustomerUser loginedUser = (CustomerUser)session.getAttribute("user");

		//テストコード
		CustomerUser loginedUser = new CustomerUser();
		loginedUser.setUserId(2);

		int itemId = Integer.parseInt(request.getParameter("itemId"));
		int quantity = Integer.parseInt(request.getParameter("quantity"));
		CartBean cb = new CartBean();
		cb.setUserId(loginedUser.getUserId());
		cb.setItemId(itemId);
		cb.setQuantity(quantity);
		
		Cart.updateItemQuantityInCart(cb);
		ArrayList<CartBean> cartList = Cart.getItemListFromCart(loginedUser);
		request.setAttribute("cartList", cartList);
		
		String view = "/WEB-INF/views/customer/cart.jsp";
		request.getRequestDispatcher(view).forward(request, response);
	}

}
