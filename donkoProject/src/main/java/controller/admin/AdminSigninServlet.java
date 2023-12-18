package controller.admin;

import java.io.IOException;

import classes.user.CustomerUser;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

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

		//存在するか確認
		//伊藤くんへ：AdminUserClassにログインIDとパスワードのフィールドがないのと、
		//アドミン側のログインメソッドが無いけど、どうしましょうかね・・・？
		//とりあえず、CostomerUserClassに入れた形にしてます。メソッドはまだ作って無い。
		//あと、今のロジックだと、User.loginの戻り値はadmin_idを返す様にしてます・・・なんかいい方法あるかなぁ。
		CustomerUser userClass = new CustomerUser();
		userClass.setUserLoginId(adminLoginId);
		userClass.setPassword(adminLoginPass);

		// ログイン処理を呼び出し
////		Integer adminId = User.login(userClass);
//
//		//遷移先を決定
//		String view;
//		if(adminId == null) {
//			//存在しないなら、エラーメッセージをつけてトップページへ転送
//			request.setAttribute("errorMessage", "IDまたはパスワードが異なります");
//			view = "/WEB-INF/views/admin/adminSignin.jsp";
//		} else {
//			//存在していたら、adminIdをせッションに登録してマイページへ転送
//			HttpSession adminSession = request.getSession();
//			adminSession.setAttribute("admin", adminId);
//
//			view = "/WEB-INF/views/admin/adminTopPage.jsp";
//		}
//		request.getRequestDispatcher(view).forward(request, response);
 	}
}
