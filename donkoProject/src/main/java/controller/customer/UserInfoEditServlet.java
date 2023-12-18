package controller.customer;

import java.io.IOException;
import java.sql.Date;

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
			String view = "/WEB-INF/views/customer/home.jsp";
			request.getRequestDispatcher(view).forward(request, response);
		}
		
		// ユーザーIDをセット
		CustomerUser customerUser = new CustomerUser();
		customerUser.setUserId(userId);
		
		// ユーザー情報取得
		CustomerUser users = CustomerUser.getUserDetail(customerUser);
		request.setAttribute("users", users);
		
		// ユーザ情報編集画面に遷移
		String view = "/WEB-INF/views/customer/userInfoEdit.jsp";
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
		
		// インスタンス生成
		CustomerUser customerUser = new CustomerUser();
		
		// PostされたデータをBeanにセット
		customerUser.setUserId((int)session.getAttribute("user_id"));
		customerUser.setUserLoginId(request.getParameter("user_login_id"));
		customerUser.setUserName(request.getParameter("user_name"));
		customerUser.setGender(request.getParameter("gender"));
		customerUser.setBirthday(Date.valueOf(request.getParameter("birthday")));
		
		// 更新処理実行
		User.updateUserInfo(customerUser);
		
		// マイページに遷移
		response.sendRedirect("myPage");
	}
}
