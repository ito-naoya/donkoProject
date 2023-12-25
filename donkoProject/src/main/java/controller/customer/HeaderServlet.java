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
import jakarta.servlet.http.HttpSession;

@WebServlet("/header")
public class HeaderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
    public HeaderServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// セッションからユーザIDを取得
		HttpSession session = request.getSession(false);
		Object user_id = session.getAttribute("user_id");
		
		if (user_id != null) {
			
			CustomerUser customerUser = new CustomerUser();
			
			 customerUser.setUserId(Integer.parseInt(user_id.toString()));
			
			// デフォルト住所を取得
			ShippingAddressBean mainShippingAddress = ShippingAddress.getMainShippingAddress(customerUser);
			
			// カート一覧の取得
			ArrayList<CartBean> cartBeanList = Cart.getItemListFromCart(customerUser);
			
			int cartItemNum = cartBeanList.stream()
					.map(cb -> cb.getQuantity())
					.mapToInt(i -> i)
					.sum();			
			// 値をセット
			request.setAttribute("mainShippingAddress", mainShippingAddress);
			request.setAttribute("cartItemNum", cartItemNum);
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
