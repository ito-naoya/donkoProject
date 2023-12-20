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

    //ユーザーの詳細情報を取得
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Integer userId = Integer.valueOf(request.getParameter("userId"));
		
		//ユーザーIDを保持するcustomerUserをnew
		CustomerUser cu = new CustomerUser();
		
		//編集対象ユーザーのユーザーIDをセットする
		cu.setUserId(userId);
		
		//編集対象ユーザーの詳細情報を取得する
		CustomerUser user = User.getUserDetail(cu);
		
		//データベースから取得できなかった時
		if(user == null) {
			//エラーメッセージ
			request.setAttribute("errorMessage", "ユーザー情報の取得時に問題が発生しました。");
			//エラーページからの遷移先
			request.setAttribute("url", "adminTopPage");
			//エラーページ表示
			String view = "/WEB-INF/views/component/message.jsp";
			request.getRequestDispatcher(view).forward(request, response);
			return;
		}
		
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
		
		CustomerUser customerUser = new CustomerUser();
		// PostされたデータをBeanにセット
		customerUser.setUserId(Integer.parseInt(request.getParameter("userId")));
		customerUser.setUserLoginId(request.getParameter("user_login_id"));
		customerUser.setUserName(request.getParameter("user_name"));
		customerUser.setGender(request.getParameter("gender"));
		customerUser.setBirthday(Date.valueOf(request.getParameter("birthday")));
		customerUser.setDeleted(statusSelect.equals("delete") ? true : false);
		
		// 更新処理実行
		Boolean isCommit = AdminUser.updateUserInfoByAdmin(customerUser);
		
		//ユーザー情報の更新に失敗したとき
		if(!isCommit) {
			//エラーメッセージ
			request.setAttribute("errorMessage", "ユーザー情報の更新時に問題が発生しました。");
			//エラーページからの遷移先
			request.setAttribute("url", "adminTopPage");
			//エラーページ表示
			String view = "/WEB-INF/views/component/message.jsp";
			request.getRequestDispatcher(view).forward(request, response);
			return;
		}
		
		// ユーザー一覧に遷移
		response.sendRedirect("deleteUserInfoIndex");
		
	}

}
