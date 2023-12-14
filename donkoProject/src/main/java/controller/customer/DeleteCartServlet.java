package controller.customer;

import java.io.IOException;

import bean.CartBean;
import classes.Cart;
import classes.user.CustomerUser;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet("/deleteCart")
public class DeleteCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public DeleteCartServlet() {
        super();
    }

    //カート内の商品を一つだけ削除する
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
//		HttpSession session = request.getSession();
//		CustomerUser user = (CustomerUser)session.getAttribute("user");
		
		CustomerUser user = new CustomerUser();
		user.setUserId(2);
		
		int itemId = Integer.parseInt(request.getParameter("itemId"));
//		int userId = user.getUserId();
		
		//削除する商品の情報を保持するcartBeanをnew
		CartBean cb = new CartBean();
		//ユーザーIDをcartBeanにセット
		cb.setUserId(user.getUserId());
		//商品IDをcartBeanにセット
		cb.setItemId(itemId);
		
		 //対象の商品をカートから削除
		Cart.deleteItemFromCart(cb);
		
		//カート一覧ページにリダイレクト
		response.sendRedirect("cart");
	}

	//カート内の商品をすべて削除する
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
//		HttpSession session = request.getSession();
//		CustomerUser user = (CustomerUser)session.getAttribute("user");
		
//		int userId = user.getUserId();
		CustomerUser user = new CustomerUser();
		user.setUserId(2);
		
		//カートから全ての商品を削除
		Cart.deleteAllItemFromCart(user);
		
		//カート一覧ページにリダイレクトする
		response.sendRedirect("cart");
		
	}

}
