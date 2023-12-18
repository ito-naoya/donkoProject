package controller.admin;

import java.io.IOException;
import java.sql.Date;

import classes.user.AdminUser;
import classes.user.CustomerUser;
import classes.user.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/editUserInfo")
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
		CustomerUser user = User.getUserDetail(cu);
		
		request.setAttribute("user", user);
		
		String view = "/WEB-INF/views/admin/editUserInfo.jsp";
        request.getRequestDispatcher(view).forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// セッションを確認
//		HttpSession session = request.getSession();
//		Integer userId = (Integer)session.getAttribute("user_id");
//		if (userId == null) {
//			response.sendRedirect("userSignin");
//		}
		
		String statusSelect = request.getParameter("status");
		boolean deleteStatus = statusSelect.equals("delete") ? true : false;
		
		CustomerUser customerUser = new CustomerUser();
		// PostされたデータをBeanにセット
		customerUser.setUserId(Integer.parseInt(request.getParameter("userId")));
		customerUser.setUserLoginId(request.getParameter("user_login_id"));
		customerUser.setUserName(request.getParameter("user_name"));
		customerUser.setGender(request.getParameter("gender"));
		customerUser.setBirthday(Date.valueOf(request.getParameter("birthday")));
		customerUser.setDeleted(deleteStatus);
		
		// 更新処理実行
		AdminUser.updateUserInfoByAdmin(customerUser);
		
		// ユーザー一覧に遷移
		response.sendRedirect("deleteUserInfoIndex");
		
	}

}
