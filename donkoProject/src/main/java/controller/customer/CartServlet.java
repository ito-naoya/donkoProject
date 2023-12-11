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


@WebServlet("/cart")
public class CartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public CartServlet() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 
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
		request.setAttribute("cartList", cartList);
		
		String view = "/WEB-INF/views/customer/cart.jsp";
		request.getRequestDispatcher(view).forward(request, response);
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
