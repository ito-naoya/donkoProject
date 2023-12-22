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

@WebServlet("/deleteFlagUserServlet")
public class DeleteFlagUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public DeleteFlagUserServlet() {
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
		request.setAttribute("user_id", userId);
		
		// ユーザ情報編集画面に遷移
		String view = "/WEB-INF/views/customer/userInfoPage.jsp";
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
		

		//Date date = Date.valueOf(request.getParameter("birthday"));
		// Timestamp timestamp = new Timestamp(date);
		
		//削除するユーザーのIDをセッションから取得
		customerUser.setUserId((int)session.getAttribute("user_id"));
		
		// ユーザー削除処理実行（論理削除）
		Boolean deleteFlag = User.updateUserDeleteFlag(customerUser);
		
		if(!deleteFlag) {
	      //エラーページに遷移
			ErrorHandling.transitionToErrorPage(request,response,"退会処理が失敗しました","userInfoPage","ユーザー情報画面に");
			return;
		}
		
		response.sendRedirect("home");
	}
}
