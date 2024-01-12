package controller.customer;

import java.io.IOException;

import classes.BeanValidation;
import classes.ErrorHandling;
import classes.user.CustomerUser;
import classes.user.User;
import interfaces.group.GroupC;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/updateUserPassword")
public class UpdateUserPasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public UpdateUserPasswordServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// ユーザパスワード変更画面に遷移
		String view = "/WEB-INF/views/customer/userPasswordUpdate.jsp";
		request.getRequestDispatcher(view).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// パラメーターをセット
		CustomerUser customerUser = new CustomerUser();
		customerUser.setUserLoginId(request.getParameter("user_login_id"));
		customerUser.setPassword(request.getParameter("password"));
		
		// 入力チェック
		Boolean isIncomplete = BeanValidation.validate(request, "users", customerUser, GroupC.class);
		
		//入力内容に不備があった場合
		if(isIncomplete) {
			String view = "/WEB-INF/views/customer/userPasswordUpdate.jsp";
			request.getRequestDispatcher(view).forward(request, response);
			return;
		}
		
		// パスワード更新処理
		Boolean updateStatus = User.updateUserPassword(customerUser);
		
		if(!updateStatus) {
			// エラー画面に遷移
			ErrorHandling.transitionToErrorPage(request,response,"パスワードの更新に失敗しました。","userPasswordUpdate","パスワード変更画面に");
			return;
		}
		
		// ログイン画面に遷移
		response.sendRedirect("userSignin");
	}
}
