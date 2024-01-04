package controller.user;

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
import jakarta.servlet.http.HttpSession;

@WebServlet("/userSignup")
public class UserSignupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public UserSignupServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// セッションを確認
		// セッションの有無でjsp側の表示ボタンが変わる
 		HttpSession session = request.getSession();
		String admin = (String) session.getAttribute("admin");
		if (admin != null) {
			request.setAttribute("admin",admin);
		}
		CustomerUser users = new CustomerUser();
		request.setAttribute("users", users);
		//ログイン時の初期メッセージ
		request.setAttribute("errorMessage", "");
		String view = "/WEB-INF/views/user/userSignup.jsp";
		request.getRequestDispatcher(view).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		//値をセット
		CustomerUser users = new CustomerUser();
		users.setUserLoginId(request.getParameter("userLoginId"));
		users.setUserName(request.getParameter("userLoginName"));
		users.setPassword(request.getParameter("userLoginPass"));

		//入力文字チェック。入力内容に不備があった場合、元の画面にリダイレクト
		if(BeanValidation.validate(request, "users", users, GroupC.class)) {
			String view = "/WEB-INF/views/user/userSignup.jsp";
			request.getRequestDispatcher(view).forward(request, response);
			return;
		}

		//ユーザ-ログインIDの重複がないか確認
		Integer checkResult = User.checkUserDuplicate(users);
		if (checkResult == null) {
		    // checkResult が null の場合の処理
		    ErrorHandling.transitionToErrorPage(request, response, "新規登録に失敗しました","home","トップページに");
		    return;
		} else if (checkResult > 0) {
		    // checkResult が 0 より大きい場合の処理
			String admin = (String) session.getAttribute("admin");
			if (admin != null) {
				request.setAttribute("admin",admin);
			}
			request.setAttribute("users", users);
		    request.setAttribute("errorMessage", "このIDは既に使用されています");
		    String view = "/WEB-INF/views/user/userSignup.jsp";
		    request.getRequestDispatcher(view).forward(request, response);
		    return;
		}

		//問題なければ新規登録メソッド発動
		if(!User.registerNewUser(users)) {
			//失敗した場合
			ErrorHandling.transitionToErrorPage(request,response,"新規登録に失敗しました","home","トップページに");
			return;
		}

		//遷移先を決定
		//セッションを確認
		String admin = (String) session.getAttribute("admin");
		if (admin != null) {
			//セッションがある=Admin側。管理者画面にリダイレクト
			response.sendRedirect("adminTopPage");
			return;
		}
		//セッションがない＝User側。ログイン画面にリダイレクト
		response.sendRedirect("userSignin");
	}

}
