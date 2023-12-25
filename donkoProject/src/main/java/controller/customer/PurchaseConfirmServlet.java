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
import jakarta.servlet.http.HttpSession;

@WebServlet("/purchaseConfirm")
public class PurchaseConfirmServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public PurchaseConfirmServlet() {
        super();
    }

    //購入確認をする
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		Object loginedUserId = session.getAttribute("user_id");
		
		if(loginedUserId == null) {
			response.sendRedirect("userSignin");
			return;
		}
		
		CustomerUser customerUser = new CustomerUser();
		customerUser.setUserId(Integer.parseInt(loginedUserId.toString()));
	
		//ログインしているユーザーのメインの配送先を取得
		ShippingAddressBean shippingAddress = ShippingAddress.getMainShippingAddress(customerUser);
		
		//メイン配送先を取得できなかった場合
		if(shippingAddress == null) {
			// エラー画面に遷移
			ErrorHandling.transitionToErrorPage(request,response,"配送先情報の取得時に問題が発生しました。","shippingAddressIndex","配送先一覧画面に");
			return;
		}
		
		//ログインしているユーザーがカートに追加した商品を全て取得
		ArrayList<CartBean> cartBeanList = Cart.getItemListFromCart(customerUser);
		
		//カートの中身を取得できなかった場合
		if(cartBeanList == null) {
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
		
		HttpSession session = request.getSession();
		Object loginedUserId = session.getAttribute("user_id");
		
		if(loginedUserId == null) {
			response.sendRedirect("userSignin");
			return;
		}

		CustomerUser customerUser = new CustomerUser(); 
		customerUser.setUserId(Integer.parseInt(loginedUserId.toString()));

		Integer totalPrice = Integer.valueOf(request.getParameter("totalPrice"));
		String address = request.getParameter("address");
		String postalCode = request.getParameter("postalCode");
		String addressee = request.getParameter("addressee");

		//購入情報を保持するpurchaseBeanをnew
		PurchaseBean pb = new PurchaseBean();
		//purchaseBeanにユーザーIDをセット
		pb.setUserId(Integer.parseInt(loginedUserId.toString()));
		//purchaseBeanに合計金額をセット
		pb.setTotalAmount(totalPrice);
		//purchaseBeanに住所をセット
		pb.setAddress(address);
		//purchaseBeanに宛名をセット
		pb.setAddressee(addressee);
		//purchaseBeanに郵便番号をセット
		pb.setPostalCode(postalCode);
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
