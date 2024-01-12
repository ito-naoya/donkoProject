package controller.admin;

import java.io.IOException;

import classes.user.AdminUser;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/adminSignin")
public class AdminSigninServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AdminSigninServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//ログイン時の初期メッセージ
		request.setAttribute("errorMessage", "");
		String view = "/WEB-INF/views/admin/adminSignin.jsp";
		request.getRequestDispatcher(view).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//ユーザーIDとパスワードを受け取る
		String adminLoginId = (String) request.getParameter("adminLoginId");
		String adminLoginPass = (String) request.getParameter("adminLoginPass");

		//値をセット（万が一nullでもSQL発行時に弾く）
		AdminUser userClass = new AdminUser();
		userClass.setAdminLoginId(adminLoginId);
		userClass.setPassword(adminLoginPass);

		//ログイン処理を呼び出し
		boolean adminId = AdminUser.login(userClass);

		//遷移先を決定
		if(!adminId) {
			//存在しないなら、エラーメッセージをつけてログインページへ転送
			request.setAttribute("errorMessage", "IDまたはパスワードが異なります");
			String view = "/WEB-INF/views/admin/adminSignin.jsp";
			request.getRequestDispatcher(view).forward(request, response);
		} else {
			//存在していたら、adminIdをセッションに登録してマイページへリダイレクト
			HttpSession adminSession = request.getSession();
			adminSession.setAttribute("admin", adminLoginId);

			response.sendRedirect("adminTopPage");
		}
	}
}
