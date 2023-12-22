package controller.customer;

import java.io.IOException;
import java.sql.Date;

import classes.ErrorHandling;
import classes.user.CustomerUser;
import classes.user.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/userInfoEdit")
public class UserInfoEditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public UserInfoEditServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// セッション確認
		HttpSession session = request.getSession(false);
		int userId = (int) session.getAttribute("user_id");
		if (userId == 0) {
			response.sendRedirect("home");
		}
		
		// ユーザーIDをセット
		CustomerUser customerUser = new CustomerUser();
		customerUser.setUserId(userId);
		
		// ユーザ情報を取得
		CustomerUser users = CustomerUser.getUserDetail(customerUser);
		
		// データの取得結果を判定
		if(users == null) {
			// エラー画面に遷移
			ErrorHandling.transitionToErrorPage(request,response,"ユーザ編集画面へのアクセスに失敗しました","userInfoPage","ユーザ情報確認画面に");
			return;
		} else {
			// 値取得が成功した場合はユーザー情報の値をセット
			request.setAttribute("users", users);
			// ユーザ情報編集画面に遷移
			String view = "/WEB-INF/views/customer/userInfoEdit.jsp";
			request.getRequestDispatcher(view).forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// セッションを確認
		HttpSession session = request.getSession(false);
		int userId = (int) session.getAttribute("user_id");
		if (userId == 0) {
			String view = "/WEB-INF/views/customer/home.jsp";
			request.getRequestDispatcher(view).forward(request, response);
		}
		
		// インスタンス生成
		CustomerUser customerUser = new CustomerUser();
		
		// CustomerBeanに値をセットする
		customerUser.setUserId((int)session.getAttribute("user_id"));
		customerUser.setUserLoginId(request.getParameter("user_login_id"));
		customerUser.setUserName(request.getParameter("user_name"));
		customerUser.setGender(request.getParameter("gender"));
		customerUser.setBirthday(Date.valueOf(request.getParameter("birthday")));
		
		/*
		 *  TODO:バリテーションチェック
		 *  こんがらがってきたので、一旦あとで
		 * */
		
		// 更新処理実行
		Boolean updateStatus = User.updateUserInfo(customerUser);
		
		// 更新結果を判定
		if (updateStatus) {
			// マイページに遷移
			response.sendRedirect("userInfoPage");
		} else {
			// エラー画面に遷移
			ErrorHandling.transitionToErrorPage(request,response,"編集処理に失敗しました","userInfoEdit","ユーザ情報編集画面に");
			return;
		}
	}
}
