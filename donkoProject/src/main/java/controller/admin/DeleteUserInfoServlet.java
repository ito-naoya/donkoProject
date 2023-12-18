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

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//削除済みのユーザー一覧を取得
		ArrayList<CustomerUser> userList = AdminUser.getDeletedUserList();

		request.setAttribute("toIndicate", "deletedUser");
		request.setAttribute("userList", userList);

		String view = "/WEB-INF/views/admin/deleteUserInfoIndex.jsp";
		request.getRequestDispatcher(view).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String showSelect = request.getParameter("showSelect");

		if (showSelect.equals("deletedUser")) {
			
			doGet(request, response);
			
		} else if(showSelect.equals("notDeletedUser")){
			
			ArrayList<CustomerUser> userList = AdminUser.getUserList();
			
			request.setAttribute("toIndicate", "notDeletedUser");
			request.setAttribute("userList", userList);
			
			String view = "/WEB-INF/views/admin/deleteUserInfoIndex.jsp";
			request.getRequestDispatcher(view).forward(request, response);
		}
	}
}
