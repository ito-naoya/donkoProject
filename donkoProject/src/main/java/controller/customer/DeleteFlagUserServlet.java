package controller.customer;

import java.io.IOException;
import java.sql.Date;

import classes.user.AdminUser;
import classes.user.CustomerUser;
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
		
		// 
//		Date date = Date.valueOf(request.getParameter("birthday"));
		// Timestamp timestamp = new Timestamp(date);
		
		// PostされたデータをBeanにセット
		customerUser.setUserId((int)session.getAttribute("user_id"));
		customerUser.setUserLoginId(request.getParameter("user_login_id"));
		customerUser.setUserName(request.getParameter("user_name"));
		customerUser.setGender(request.getParameter("gender"));
		Date date = Date.valueOf(request.getParameter("birthday"));
		
		// 更新処理実行
		Boolean deleteFlag = AdminUser.updateUserInfoByAdmin(customerUser);
		if(deleteFlag) {
			// 処理が成功すればにホーム画面に遷移
			response.sendRedirect("home");
		} else {
			
			// エラーメッセージ
			request.setAttribute("errorMessage", "退会処理が失敗しました");
			
			// ユーザー情報の画面に遷移
			request.setAttribute("url", "userInfoPage");
			
			// エラー画面に遷移
			String view = "/WEB-INF/views/component/message.jsp";
	        request.getRequestDispatcher(view).forward(request, response);
		}
	}
}
