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

	//カート一覧ページを表示する
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

		//ログインしているユーザーがカートに追加した商品を全て取得
		ArrayList<CartBean> cartBeanList = Cart.getItemListFromCart(loginedUser);
		
		if(cartBeanList != null) {
			
			request.setAttribute("cartBeanList", cartBeanList);
	
			//カート一覧ページを表示する
			String view = "/WEB-INF/views/customer/cart.jsp";
			request.getRequestDispatcher(view).forward(request, response);
		
		} else if(cartBeanList == null) {
			//トップ画面にリダイレクトする
			response.sendRedirect("home");
		}
	}

	//カート内の商品の数量を更新する
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//		HttpSession session = request.getSession();
		//		CustomerUser loginedUser = (CustomerUser)session.getAttribute("user");

		//テストコード
		CustomerUser loginedUser = new CustomerUser();
		loginedUser.setUserId(2);

		int itemId = Integer.parseInt(request.getParameter("itemId"));
		int quantity = Integer.parseInt(request.getParameter("quantity"));
		
		//カートにある商品情報を保持するcartBeanをnew
		CartBean cb = new CartBean();
		//cartBeanにユーザーIDをセットする
		cb.setUserId(loginedUser.getUserId());
		//cartBeanに商品IDをセットする
		cb.setItemId(itemId);
		//cartBeanに数量をセットする
		cb.setQuantity(quantity);
		
		 //カート内の商品の数量を更新する
		Cart.updateItemQuantityInCart(cb);
		
		//doGetメソッドを実行(カート一覧ページを表示)
		doGet(request, response);
	}

}
