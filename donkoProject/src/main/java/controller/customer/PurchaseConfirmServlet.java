package controller.customer;

import java.io.IOException;
import java.util.ArrayList;

import bean.CartBean;
import bean.PurchaseBean;
import bean.ShippingAddressBean;
import classes.Cart;
import classes.ErrorHandling;
import classes.Purchase;
import classes.ShippingAddress;
import classes.user.CustomerUser;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/purchaseConfirm")
public class PurchaseConfirmServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public PurchaseConfirmServlet() {
        super();
    }

    //購入確認をする
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
//		HttpSession session = request.getSession();
//		CustomerUser loginedUser = (CustomerUser)session.getAttribute("customerUser");
//		
//		if(loginedUser == null) {
//			response.sendRedirect("login");
//			return;
//		}
		
		//テストコード
		CustomerUser loginedUser = new CustomerUser(); 
		loginedUser.setUserId(2);
	
		//ログインしているユーザーのメインの配送先を取得
		ShippingAddressBean shippingAddress = ShippingAddress.getMainShippingAddress(loginedUser);
		//ログインしているユーザーがカートに追加した商品を全て取得
		ArrayList<CartBean> cartBeanList = Cart.getItemListFromCart(loginedUser);
		
		//データベースから取得できなかった時
		if(cartBeanList == null || shippingAddress == null) {
			// エラー画面に遷移
			ErrorHandling.transitionToErrorPage(request,response,"購入情報の取得時に問題が発生しました。","home","ホームに");
			return;
		}
		
		//カート内の商品の合計金額をtotalPriceに代入
		Integer totalPrice= cartBeanList.stream()
				.map(cb -> cb.getItemPrice() * cb.getQuantity())
				.mapToInt( i -> i )
				.sum();
		
		request.setAttribute("shippingAddress", shippingAddress);
		request.setAttribute("cartBeanList", cartBeanList);
		request.setAttribute("totalPrice", totalPrice);
		
		//購入確認画面を返す
		String view = "/WEB-INF/views/customer/purchaseConfirm.jsp";
		request.getRequestDispatcher(view).forward(request, response);
	}

	//購入処理をする
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
//		HttpSession session = request.getSession();
//		CustomerUser loginedUser = (CustomerUser)session.getAttribute("customerUser");
//		
//		if(loginedUser == null) {
//			response.sendRedirect("login");
//			return;
//		}

		//テストコード
		CustomerUser loginedUser = new CustomerUser(); 
		loginedUser.setUserId(2);

		Integer totalPrice = Integer.valueOf(request.getParameter("totalPrice"));
		Integer shippingAddressId = Integer.valueOf(request.getParameter("shippingAddressId"));

		//購入情報を保持するpurchaseBeanをnew
		PurchaseBean pb = new PurchaseBean();
		//purchaseBeanにユーザーIDをセット
		pb.setUserId(loginedUser.getUserId());
		//purchaseBeanに合計金額をセット
		pb.setTotalAmount(totalPrice);
		//purchaseBeanに配送先IDをセット
		pb.setShippingAddressId(shippingAddressId);

		//商品を購入する
		Boolean isCommit = Purchase.purchaseItem(pb);
		
		//商品を購入できなかった時
		if(!isCommit) {			
			// エラー画面に遷移
			ErrorHandling.transitionToErrorPage(request,response,"商品購入処理中に問題が発生しました。","purchaseConfirm","購入確認画面に");
			return;
		}
		
		//購入完了画面を返す
		String view = "/WEB-INF/views/customer/purchaseComplete.jsp";
		request.getRequestDispatcher(view).forward(request, response);
	}

}
