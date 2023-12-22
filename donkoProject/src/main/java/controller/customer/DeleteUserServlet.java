package controller.customer;

import java.io.IOException;

import classes.ErrorHandling;
import classes.user.CustomerUser;
import classes.user.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/deleteUser")
public class DeleteUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public DeleteUserServlet() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// セッションを確認
		HttpSession session = request.getSession(false);
		Object userId = (String) session.getAttribute("user_id");
		
		// userIdがなければホーム画面に戻す
		if (userId == null) {
			response.sendRedirect("home");
			return;
		}
		
		// ユーザーIDをセット
		CustomerUser customerUser = new CustomerUser();
		customerUser.setUserId((int) userId);
		
		// 削除フラグを設定するSQL
		Boolean deleteFlagStatus = User.updateUserDeleteFlag(customerUser);
		
		// 削除フラグの処理結果を判定
		if(deleteFlagStatus) {
			// セッションがある場合
			if (session != null) {
				// セッションを破棄
				session.invalidate();
			}
			// エラー画面に遷移
			ErrorHandling.transitionToErrorPage(request,response,"退会処理が完了しました。"+ "<br>" +"ご利用ありがとうございました。","home","ホームに");
			return;
		} else {
			// エラー画面に遷移
			ErrorHandling.transitionToErrorPage(request,response,"退会処理が失敗しました","userInfoPage","ユーザー情報確認画面に");
			return;
		}
	}
}
