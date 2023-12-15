package controller.admin;

import java.io.IOException;

import classes.user.CustomerUser;
import classes.user.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("editUserInfo")
public class EditUserInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
    public EditUserInfoServlet() {
        super();
  
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int userId = Integer.parseInt(request.getParameter("userId"));
		
		//ユーザーIDを保持するcustomerUserをnew
		CustomerUser cu = new CustomerUser();
		
		//編集対象ユーザーのユーザーIDをセットする
		cu.setUserId(userId);
		
		//編集対象ユーザーの詳細情報を取得する
		CustomerUser customerUser = User.getUserDetail(cu);
		
		request.setAttribute("customerUser", customerUser);
		
		String view = "/WEB-INF/views/admin/editUserInfo.jsp";
        request.getRequestDispatcher(view).forward(request, response);
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
