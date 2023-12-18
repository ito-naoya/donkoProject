package controller.customer;

import java.io.IOException;

import classes.user.CustomerUser;
import classes.user.User;
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
		
		// SQL実行
		User.updateUserPassword(customerUser);
		
		// ホーム画面に遷移
		response.sendRedirect("home");
		/*
		 *  ホーム画面に遷移させていますが、ログイン画面ができていればログイン画面に遷移させようと考えています。
		 *  更新後の遷移先について、ホーム画面かログイン画面への遷移のどちらがいいか教えてください。
		 *  
		 * */
		
	}
}
