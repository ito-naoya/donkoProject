package controller.customer;

import java.io.IOException;

import classes.user.CustomerUser;
import classes.user.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/userInfoPage")
public class UserInfoPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public UserInfoPageServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// セッション確認
		HttpSession session = request.getSession(false);
		int userId = (int) session.getAttribute("user_id");
		if (userId == 0) {
			String view = "/WEB-INF/views/customer/home.jsp";
			request.getRequestDispatcher(view).forward(request, response);
		}
		
		// ユーザーIDをセット
		CustomerUser customerUser = new CustomerUser();
		customerUser.setUserId(userId);
		
		// ユーザー情報取得
		CustomerUser users = CustomerUser.getUserDetail(customerUser);
		request.setAttribute("users", users);
		request.setAttribute("user_id", userId);
		
		// ユーザ情報編集画面に遷移
		String view = "/WEB-INF/views/customer/userInfoPage.jsp";
		request.getRequestDispatcher(view).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// セッションを確認
		HttpSession session = request.getSession(false);
		int userId = (int) session.getAttribute("user_id");
		if (userId == 0) {
			String view = "/WEB-INF/views/customer/home.jsp";
			request.getRequestDispatcher(view).forward(request, response);
		}
		
		// ユーザーIDをセット
		CustomerUser customerUser = new CustomerUser();
		customerUser.setUserId(userId);
		
		// 削除フラグを設定するSQL
		Boolean deleteFlagStatus = User.updateUserDeleteFlag(customerUser);
		
		// 削除フラグの処理結果を判定
		if(deleteFlagStatus) {
			// セッションがある場合
			if (session != null) {
				// セッションを破棄
				session.invalidate();
			}
			// エラーメッセージ
			request.setAttribute("errorMessage", "退会処理が完了しました。"+ "<br>" +"ご利用ありがとうございました。");
			
			// ホーム画面に誘導
			request.setAttribute("url", "home");
			
			// エラー画面に遷移
			String view = "/WEB-INF/views/component/message.jsp";
			request.getRequestDispatcher(view).forward(request, response);
		} else {
			
			// エラーメッセージ
			request.setAttribute("errorMessage", "退会処理が失敗しました");
			
			// ユーザー情報の画面に誘導
			request.setAttribute("url", "userInfoPage");
			
			// エラー画面に遷移
			String view = "/WEB-INF/views/component/message.jsp";
			request.getRequestDispatcher(view).forward(request, response);
		}
	}
}
