package controller.customer;

import java.io.IOException;
import java.util.ArrayList;

import bean.CartBean;
import bean.ItemCategoryBean;
import bean.ShippingAddressBean;
import classes.Cart;
import classes.ItemCategory;
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
		if (session != null) {
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
		// カテゴリ名を取得
		ArrayList<ItemCategoryBean> categoryNameList = ItemCategory.getItemCategoryList();
		request.setAttribute("categoryNameList", categoryNameList);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// セッションからユーザIDを取得
		HttpSession session = request.getSession(false);
		if (session != null) {
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
		// カテゴリ名を取得
		ArrayList<ItemCategoryBean> categoryNameList = ItemCategory.getItemCategoryList();
		request.setAttribute("categoryNameList", categoryNameList);
	}
}
