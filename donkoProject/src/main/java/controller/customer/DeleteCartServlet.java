package controller.customer;

import java.io.IOException;
import java.util.ArrayList;

import bean.CartBean;
import classes.Cart;
import classes.user.CustomerUser;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


@WebServlet("/deleteCart")
public class DeleteCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public DeleteCartServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		CustomerUser user = (CustomerUser)session.getAttribute("user");
		
		user.setUserId(2);
		
		int itemId = Integer.parseInt(request.getParameter("itemId"));
//		int userId = user.getUserId();
		int userId = user.getUserId();
		
		CartBean cartBean = new CartBean();
		cartBean.setUserId(userId);
		cartBean.setItemId(itemId);
		
		Cart.deleteItemFromCart(cartBean);
		ArrayList<CartBean> cartList = Cart.getItemListFromCart(user);
		request.setAttribute("cartList", cartList);
		
		String view = "/WEB-INF/views/customer/cart.jsp";
		request.getRequestDispatcher(view).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		CustomerUser user = (CustomerUser)session.getAttribute("user");
		
//		int userId = user.getUserId();
		user.setUserId(2);
		
		Cart.deleteAllItemFromCart(user);
		ArrayList<CartBean> cartList = Cart.getItemListFromCart(user);
		request.setAttribute("cartList", cartList);
		
		String view = "/WEB-INF/views/customer/cart.jsp";
		request.getRequestDispatcher(view).forward(request, response);
		
	}

}
