package controller.user;

import java.io.IOException;

import classes.user.CustomerUser;
import classes.user.User;
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

		//ログイン時の初期メッセージ
		request.setAttribute("errorMessage", "");
		String view = "/WEB-INF/views/user/userSignup.jsp";
		request.getRequestDispatcher(view).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//ユーザーIDとパスワードを受け取る
		String adminLoginId = (String) request.getParameter("adminLoginId");
		String adminLoginName = (String) request.getParameter("adminLoginName");
		String adminLoginPass = (String) request.getParameter("adminLoginPass");

		//値をセット（バリデーションでうまいことする）
		CustomerUser user = new CustomerUser();
		user.setUserId(Integer.parseInt(adminLoginId));
		user.setUserName(adminLoginName);
		user.setPassword(adminLoginPass);

		//仮のメソッド
		//ユーザー名とパスワードの重複がないか確認(確認方法に応じてStringで返す？)
		User.checkUserDuplicate(user);
		//存在するなら、結果に応じたエラーメッセージをつけてログインページへ転送
		request.setAttribute("errorMessage", "このIDは既に使用されています");
		request.setAttribute("errorMessage", "このパスワードは既に使用されています");
		request.setAttribute("errorMessage", "このID及びパスワードは既に使用されています");
		String view = "/WEB-INF/views/user/userSignup.jsp";
		request.getRequestDispatcher(view).forward(request, response);

		//問題なければ新規登録メソッド発動
		User.registerNewUser(user);


		//遷移先を決定
		//セッションを確認。
		HttpSession session = request.getSession();
		String admin = (String) session.getAttribute("admin");
		if (!admin.isEmpty()) {
			//セッションがない＝User側。ログイン画面にリダイレクト
			response.sendRedirect("userSignin");
		}

		//セッションがある=Admin側。管理者画面にリダイレクト
		response.sendRedirect("adminTopPage");

	}

}
