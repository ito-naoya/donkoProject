package controller.admin;

import java.io.IOException;
import java.util.ArrayList;

import classes.user.AdminUser;
import classes.user.CustomerUser;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/deleteUserInfoIndex")
public class DeleteUserInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public DeleteUserInfoServlet() {
		super();
	}

	//削除済みユーザー一覧を取得する
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//削除済みのユーザー一覧を取得
		ArrayList<CustomerUser> userList = AdminUser.getDeletedUserList();
		
		//データベースから取得できなかった時
		if(userList == null) {
			
			//エラーメッセージ
			request.setAttribute("errorMessage", "ユーザー情報の取得時に問題が発生しました。");
			//エラーページからの遷移先
			request.setAttribute("url", "adminTopPage");
			//エラーページ表示
			String view = "/WEB-INF/views/component/message.jsp";
			request.getRequestDispatcher(view).forward(request, response);
			return;
		}

		request.setAttribute("toIndicate", "deletedUser");
		request.setAttribute("userList", userList);

		String view = "/WEB-INF/views/admin/deleteUserInfoIndex.jsp";
		request.getRequestDispatcher(view).forward(request, response);
	}

	//ユーザー一覧を取得（削除済み or 全て）
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String showSelect = request.getParameter("showSelect");

		if (showSelect.equals("deletedUser")) {
			
			doGet(request, response);
			
		} else if(showSelect.equals("notDeletedUser")){
			
			ArrayList<CustomerUser> userList = AdminUser.getUserList();
			
			//データベースから取得できなかった時
			if(userList == null) {
				
				//エラーメッセージ
				request.setAttribute("errorMessage", "ユーザー情報の取得時に問題が発生しました。");
				//エラーページからの遷移先
				request.setAttribute("url", "adminTopPage");
				
				//エラーページ表示
				String view = "/WEB-INF/views/component/message.jsp";
				request.getRequestDispatcher(view).forward(request, response);
				return;
			}
			
			request.setAttribute("toIndicate", "notDeletedUser");
			request.setAttribute("userList", userList);
			
			String view = "/WEB-INF/views/admin/deleteUserInfoIndex.jsp";
			request.getRequestDispatcher(view).forward(request, response);
		}
	}
}
