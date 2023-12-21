package controller.user;

import java.io.IOException;

import classes.user.AdminUser;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/userSignin")
public class UserSigninServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public UserSigninServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
		//ログイン時の初期メッセージ
				request.setAttribute("errorMessage", "");
				String view = "/WEB-INF/views/admin/userSignin.jsp";
				request.getRequestDispatcher(view).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
		//入力フォームからユーザーIDとパスワードを受け取る
				String user_login_id = request.getParameter("user_login_id");
				String password = request.getParameter("password");
				
				//CustumerUserにuserが送信した情報をセット
				CustumerUser userClass = new CustumerUser();
				userClass.setAdminLoginId(user_login_id);
				userClass.setPassword(password);
				
				//ログイン処理を呼び出し
				boolean user_login_id = AdminUser.login(userClass);
				
				if (!user_login_id) {
				    // 存在しないなら、エラーメッセージをつけてログインページへ転送
				    request.setAttribute("errorMessage", "IDまたはパスワードが異なります");
				    String view = "/WEB-INF/views/admin/userSignin.jsp";
				    request.getRequestDispatcher(view).forward(request, response);
				} else {
				    // 存在していたら、userIdをセッションに登録してマイページへリダイレクト
				    HttpSession adminSession = request.getSession();
				    adminSession.setAttribute("user", user_login_id);
				    response.sendRedirect("adminTopPage");
	}

}
}
