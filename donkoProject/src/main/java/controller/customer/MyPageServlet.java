package controller.customer;

import java.io.IOException;
import java.util.ArrayList;

import bean.PurchaseBean;
import classes.ErrorHandling;
import classes.Purchase;
import classes.user.CustomerUser;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/myPage")
public class MyPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public MyPageServlet() {
		super();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// インスタンス生成
		CustomerUser customerUser = new CustomerUser();
		
		// セッションの確認
		HttpSession session = request.getSession(false);
		if(session == null) {
			// セッションがなければホーム画面に遷移
			response.sendRedirect("home");
			return;
		} else {
			// TODO: testUserIdはテストコードなのでログイン機能が追加され次第削除
			int testUserId = 2;
			session.setAttribute("user_id", testUserId);
			// セッションがあればuser_idを取得する
			customerUser.setUserId((int)session.getAttribute("user_id"));
		} 
		
			// 購入履歴の一覧を取得する
			ArrayList<PurchaseBean> purchaseList = Purchase.getMyPurchaseHistory(customerUser);
			
			// Valueチェック
			if(purchaseList == null) {	
				// エラー画面に遷移
				ErrorHandling.transitionToErrorPage(request,response,"マイページへのアクセスが失敗しました","userSignin","ログイン画面に");
				return;
			} else {
			// purchaseListに値をセット
			request.setAttribute("purchaseList", purchaseList);
			}
			
			// 以下追加
			// ユーザー情報取得
			CustomerUser users = CustomerUser.getUserDetail(customerUser);
			request.setAttribute("users", users);
			request.setAttribute("user_id", customerUser.getUserId());
			// ここまで
			
			// マイページに画面遷移
			String view = "/WEB-INF/views/customer/myPage.jsp";
			request.getRequestDispatcher(view).forward(request, response);
	}
}
