package controller.customer;

import java.io.IOException;

import bean.CartBean;
import classes.Cart;
import classes.user.CustomerUser;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet("/deleteCart")
public class DeleteCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public DeleteCartServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
//		HttpSession session = request.getSession();
//		CustomerUser user = (CustomerUser)session.getAttribute("user");
		
		CustomerUser user = new CustomerUser();
		user.setUserId(2);
		
		int itemId = Integer.parseInt(request.getParameter("itemId"));
//		int userId = user.getUserId();
		
		CartBean cartBean = new CartBean();
		cartBean.setUserId(user.getUserId());
		cartBean.setItemId(itemId);
		
		Cart.deleteItemFromCart(cartBean);
		
		response.sendRedirect("cart");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
//		HttpSession session = request.getSession();
//		CustomerUser user = (CustomerUser)session.getAttribute("user");
		
//		int userId = user.getUserId();
		CustomerUser user = new CustomerUser();
		user.setUserId(2);
		
		Cart.deleteAllItemFromCart(user);
		
		response.sendRedirect("cart");
		
	}

}
