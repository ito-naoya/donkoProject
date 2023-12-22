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
//
//				HttpSession session = request.getSession();
//				Object userId = session.getAttribute("user_id");
//				
//				if(userId == null) {
//					response.sendRedirect("login");
//					return;
//				}

		//テストコード
		CustomerUser loginedUser = new CustomerUser();
		loginedUser.setUserId(2);

		//ログインしているユーザーがカートに追加した商品を全て取得
		ArrayList<CartBean> cartBeanList = Cart.getItemListFromCart(loginedUser);
		
		//データベースから取得できなかった時
		if(cartBeanList == null) {
			//エラーメッセージ
			request.setAttribute("errorMessage", "カート情報の取得時に問題が発生しました。");
			//エラーページからの遷移先
			request.setAttribute("url", "home");
			//エラーページ表示
			String view = "/WEB-INF/views/component/message.jsp";
			request.getRequestDispatcher(view).forward(request, response);
			return;
		}
		
		//カート内の商品の合計金額をtotalPriceに代入
		Integer totalPrice= cartBeanList.stream()
				.map(cb -> cb.getItemPrice() * cb.getQuantity())
				.mapToInt( i -> i )
				.sum();
	
		request.setAttribute("totalPrice", totalPrice);
		request.setAttribute("cartBeanList", cartBeanList);

		//カートページを表示する
		String view = "/WEB-INF/views/customer/cart.jsp";
		request.getRequestDispatcher(view).forward(request, response);
		
		
	}

	//カート内の商品の数量を更新する
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//		HttpSession session = request.getSession();
		//		CustomerUser loginedUser = (CustomerUser)session.getAttribute("user");

		//テストコード
		CustomerUser loginedUser = new CustomerUser();
		loginedUser.setUserId(2);

		Integer itemId = Integer.valueOf(request.getParameter("itemId"));
		Integer quantity = Integer.valueOf(request.getParameter("quantity"));
		
		//カートにある商品情報を保持するcartBeanをnew
		CartBean cb = new CartBean();
		//cartBeanにユーザーIDをセットする
		cb.setUserId(loginedUser.getUserId());
		//cartBeanに商品IDをセットする
		cb.setItemId(itemId);
		//cartBeanに数量をセットする
		cb.setQuantity(quantity);
		
		 //カート内の商品の数量を更新する
		Boolean isCommit = Cart.updateItemQuantityInCart(cb);
		
		//数量更新に失敗したとき
		if(!isCommit) {
			//エラーメッセージ
			request.setAttribute("errorMessage", "カート数量更新処理中に問題が発生しました。");
			//エラーページからの遷移先
			request.setAttribute("url", "cart");
			//エラーページ表示
			String view = "/WEB-INF/views/component/message.jsp";
			request.getRequestDispatcher(view).forward(request, response);
			return;
		}
		
		//doGetメソッドを実行(カートページを表示)
		doGet(request, response);
	}

}
