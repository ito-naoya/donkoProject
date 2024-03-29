package controller.customer;

import java.io.IOException;
import java.util.ArrayList;

import bean.CartBean;
import bean.ItemCategoryBean;
import classes.Cart;
import classes.ErrorHandling;
import classes.ItemCategory;
import classes.user.CustomerUser;
import classes.user.User;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/cart")
public class CartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public CartServlet() {
		super();
	}

	//カート一覧ページを表示する
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		Object loginedUserId = session.getAttribute("user_id");
		
		if(loginedUserId == null) {
			response.sendRedirect("userSignin");
			return;
		}

		String disp = "/header";
	    RequestDispatcher dispatch = request.getRequestDispatcher(disp);
	    dispatch.include(request, response);
		


		CustomerUser customerUser = new CustomerUser();
		customerUser.setUserId(Integer.parseInt(loginedUserId.toString()));

		//ログインしているユーザーがカートに追加した商品を全て取得
		ArrayList<CartBean> cartBeanList = Cart.getItemListFromCart(customerUser);
		
		//データベースから取得できなかった時
		if(cartBeanList == null) {
			//エラーページに遷移
			ErrorHandling.transitionToErrorPage(request,response,"カート情報の取得時に問題が発生しました。","home","ホームに");
			return;
		}
		
		//ログインしているユーザー情報を取得
		CustomerUser cu = User.getUserDetail(customerUser);
		
		//データベースから取得できなかった時
		if(cu == null) {			
			//エラーページに遷移
			ErrorHandling.transitionToErrorPage(request,response,"ユーザー情報の取得時に問題が発生しました。","home","ホームに");
			return;
		}
		
		//カート内の商品の合計金額をtotalPriceに代入
		Integer totalPrice= cartBeanList.stream()
				.map(cb -> cb.getItemPrice() * cb.getQuantity())
				.mapToInt( i -> i )
				.sum();
		
		// カテゴリ名を取得
		ArrayList<ItemCategoryBean> categoryNameList = ItemCategory.getItemCategoryList();

		request.setAttribute("userName", cu.getUserName());
		request.setAttribute("totalPrice", totalPrice);
		request.setAttribute("cartBeanList", cartBeanList);
		request.setAttribute("categoryNameList", categoryNameList);

		//カートページを表示する
		String view = "/WEB-INF/views/customer/cart.jsp";
		request.getRequestDispatcher(view).forward(request, response);
		
		
	}

	//カート内の商品の数量を更新する
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession(false);
		Object loginedUserId = session.getAttribute("user_id");
		
		if(loginedUserId == null) {
			response.sendRedirect("userSignin");
			return;
		}
		
		String disp = "/header";
	    RequestDispatcher dispatch = request.getRequestDispatcher(disp);
	    dispatch.include(request, response);

		Integer itemId = Integer.valueOf(request.getParameter("itemId"));
		Integer quantity = Integer.valueOf(request.getParameter("quantity"));
		
		//カートにある商品情報を保持するcar)tBeanをnew
		CartBean cb = new CartBean();
		//cartBeanにユーザーIDをセットする
		cb.setUserId(Integer.parseInt(loginedUserId.toString()));
		//cartBeanに商品IDをセットする
		cb.setItemId(itemId);
		//cartBeanに数量をセットする
		cb.setQuantity(quantity);
		
		 //カート内の商品の数量を更新する
		Boolean isCommit = Cart.updateItemQuantityInCart(cb);
		
		//数量更新に失敗したとき
		if(!isCommit) {
			//エラーページに遷移
			ErrorHandling.transitionToErrorPage(request,response,"カート数量更新処理中に問題が発生しました。","cart","カートに");
			return;
		}
		
		//doGetメソッドを実行(カートページを表示)
		doGet(request, response);
	}

}
