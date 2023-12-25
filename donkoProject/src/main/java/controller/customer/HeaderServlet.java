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
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// セッションからユーザIDを取得
		HttpSession session = request.getSession(false);
		Object ui = (Object) session.getAttribute("user_id");
		int userId = ((Integer) ui).intValue();
		Integer user_id = Integer.valueOf(userId);
		if (user_id != null) {
			CustomerUser customerUser = new CustomerUser();
			customerUser.setUserId(userId);
			
			// デフォルト住所を取得
			ShippingAddressBean mainShippingAddress = ShippingAddress.getMainShippingAddress(customerUser);
			
			// カート一覧の取得
			ArrayList<CartBean> cartBeanList = Cart.getItemListFromCart(customerUser);
			int cartItemNum = cartBeanList.size();
			
			// 値をセット
			request.setAttribute("mainShippingAddress", mainShippingAddress);
			request.setAttribute("cartItemNum", cartItemNum);
		}
	}
}
