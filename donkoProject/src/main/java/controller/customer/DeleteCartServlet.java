package controller.customer;

import java.io.IOException;

import bean.CartBean;
import classes.Cart;
import classes.ErrorHandling;
import classes.user.CustomerUser;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


@WebServlet("/deleteCart")
public class DeleteCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public DeleteCartServlet() {
        super();
    }

    //カート内の商品を一つだけ削除する
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession(false);
		
		if(session == null) {
			response.sendRedirect("userSignin");
			return;
		}
		
		String disp = "/header";
		RequestDispatcher dispatch = request.getRequestDispatcher(disp);
		dispatch.include(request, response);
		
		Object loginedUserId = session.getAttribute("user_id");
		
		Integer itemId = Integer.valueOf(request.getParameter("itemId"));
		
		//削除する商品の情報を保持するcartBeanをnew
		CartBean cb = new CartBean();
		//ユーザーIDをcartBeanにセット
		cb.setUserId(Integer.parseInt(loginedUserId.toString()));
		//商品IDをcartBeanにセット
		cb.setItemId(itemId);
		
		 //対象の商品をカートから削除
		Boolean isCommit = Cart.deleteItemFromCart(cb);
		
		//カートから商品削除に失敗した時
		if(!isCommit) {
			//エラーページに遷移
			ErrorHandling.transitionToErrorPage(request,response,"カート処理中に問題が発生しました。","cart","カートに");
			return;
		}
		
		//カートページにリダイレクト
		response.sendRedirect("cart");
	}

	//カート内の商品をすべて削除する
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		if(session == null) {
			response.sendRedirect("userSignin");
			return;
		}
		
		String disp = "/header";
		RequestDispatcher dispatch = request.getRequestDispatcher(disp);
		dispatch.include(request, response);
		
		Object loginedUserId = session.getAttribute("user_id");
		
		CustomerUser customerUser = new CustomerUser();
		customerUser.setUserId(Integer.parseInt(loginedUserId.toString()));
		
		//カートから全ての商品を削除
		Boolean isCommit = Cart.deleteAllItemFromCart(customerUser);
		
		//カートから商品削除に失敗した時
		if(!isCommit) {
			//エラーページに遷移
			ErrorHandling.transitionToErrorPage(request,response,"カート処理中に問題が発生しました。","cart","カートに");
			return;
		}
		
		//カートページにリダイレクトする
		response.sendRedirect("cart");
		
	}

}
