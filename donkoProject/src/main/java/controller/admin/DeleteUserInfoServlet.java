package controller.admin;

import java.io.IOException;
import java.util.ArrayList;

import classes.ErrorHandling;
import classes.user.AdminUser;
import classes.user.CustomerUser;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/deleteUserInfoIndex")
public class DeleteUserInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public DeleteUserInfoServlet() {
		super();
	}

	//削除済みユーザー一覧を取得する
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String admin = (String) session.getAttribute("admin");
		if (admin == null) {
			response.sendRedirect("adminSignin");
			return;
		}

		// ヘッダーに表示する値を取得
		String disp = "/adminheader";
	    RequestDispatcher dispatch = request.getRequestDispatcher(disp);
	    dispatch.include(request, response);
	    
	    String status = request.getParameter("status");
	    
	    ArrayList<CustomerUser> userList = null;
	    
		if(status == null) {
			userList = AdminUser.getDeletedUserList();
			request.setAttribute("toIndicate", "delete");
			
		}else if(status != null) {
			//全てのユーザー一覧を取得
			if(status.equals("notDelete")) {
				userList = AdminUser.getUserList();
				request.setAttribute("toIndicate", "notDelete");
			}
			//削除済みのユーザー一覧を取得
			if(status.equals("delete")) {
				userList = AdminUser.getDeletedUserList();
				request.setAttribute("toIndicate", "delete");
			}
		}
		
		//データベースから取得できなかった時
		if(userList == null) {
			//エラーページに遷移する
			ErrorHandling.transitionToErrorPage(request, response,  "ユーザー情報の取得時に問題が発生しました。", "adminTopPage", "管理者ページに");
			return;
		}

		request.setAttribute("userList", userList);

		String view = "/WEB-INF/views/admin/deleteUserInfoIndex.jsp";
		request.getRequestDispatcher(view).forward(request, response);
	}
}
