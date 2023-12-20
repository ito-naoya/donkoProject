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
		
		Integer itemId = Integer.valueOf(request.getParameter("itemId"));
		
		//削除する商品の情報を保持するcartBeanをnew
		CartBean cb = new CartBean();
		//ユーザーIDをcartBeanにセット
		cb.setUserId(user.getUserId());
		//商品IDをcartBeanにセット
		cb.setItemId(itemId);
		
		 //対象の商品をカートから削除
		Boolean isCommit = Cart.deleteItemFromCart(cb);
		
		//カートから商品削除に失敗した時
		if(!isCommit) {
			//エラーメッセージ
			request.setAttribute("errorMessage", "カート処理中に問題が発生しました。");
			//エラーページからの遷移先
			request.setAttribute("url", "cart");
			//エラーページ表示
			String view = "/WEB-INF/views/component/message.jsp";
			request.getRequestDispatcher(view).forward(request, response);
			return;
		}
		
		//カートページにリダイレクト
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
		Boolean isCommit = Cart.deleteAllItemFromCart(user);
		
		//カートから商品削除に失敗した時
		if(!isCommit) {
			//エラーメッセージ
			request.setAttribute("errorMessage", "カート処理中に問題が発生しました。");
			//エラーページからの遷移先
			request.setAttribute("url", "cart");
			//エラーページ表示
			String view = "/WEB-INF/views/component/message.jsp";
			request.getRequestDispatcher(view).forward(request, response);
			return;
		}
		
		//カートページにリダイレクトする
		response.sendRedirect("cart");
		
	}

}
