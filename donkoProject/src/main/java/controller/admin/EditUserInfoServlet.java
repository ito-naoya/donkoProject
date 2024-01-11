package controller.admin;

import java.io.IOException;
import java.sql.Date;

import classes.BeanValidation;
import classes.ErrorHandling;
import classes.user.AdminUser;
import classes.user.CustomerUser;
import classes.user.User;
import interfaces.group.GroupD;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/editUserInfo")
public class EditUserInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


    public EditUserInfoServlet() {
        super();
    }

    //ユーザーの詳細情報を取得
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession as = request.getSession();
		String adminSession = (String)as.getAttribute("admin");

		// アドミンのセッションがnullの場合は管理者ログイン画面に遷移
		if (adminSession == null) {
			response.sendRedirect("adminSignin");
			return;
		}


		Integer userId = Integer.valueOf(request.getParameter("userId"));

		//ユーザーIDを保持するcustomerUserをnew
		CustomerUser cu = new CustomerUser();

		//編集対象ユーザーのユーザーIDをセットする
		cu.setUserId(userId);

		//編集対象ユーザーの詳細情報を取得する
		CustomerUser user = User.getUserDetail(cu);

		//データベースから取得できなかった時
		if(user == null) {
			//エラーページ遷移
			ErrorHandling.transitionToErrorPage(request, response, "ユーザー情報の取得時に問題が発生しました。","adminTopPage","管理者ページに");
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

		String status = request.getParameter("status");

		CustomerUser customerUser = new CustomerUser();
		// PostされたデータをBeanにセット
		customerUser.setUserId(Integer.valueOf(request.getParameter("userId")));
		customerUser.setUserLoginId(request.getParameter("user_login_id"));
		customerUser.setUserName(request.getParameter("user_name"));
		customerUser.setDeleted(status.equals("delete") ? true : false);

		String gender = request.getParameter("gender");
		customerUser.setGender(gender.equals("選択してください") ? null : gender);

		String birthday = request.getParameter("birthday");
		if(!birthday.equals("")) customerUser.setBirthday(Date.valueOf(request.getParameter("birthday")));

		//入力チェック
		Boolean isIncomplete = BeanValidation.validate(request, "user", customerUser, GroupD.class);

		//入力内容に不備があった場合
	      if(isIncomplete) {
	    	  //ユーザー編集画面に戻す（エラーメッセージを出して再度入力を促す）
	    	  String view = "/WEB-INF/views/admin/editUserInfo.jsp";
	    	  request.getRequestDispatcher(view).forward(request, response);
	    	  return;
	      }

		// 更新処理実行
		Boolean isCommit = AdminUser.updateUserInfoByAdmin(customerUser);

		//ユーザー情報の更新に失敗したとき
		if(!isCommit) {
			//エラーページ遷移
			ErrorHandling.transitionToErrorPage(request, response, "ユーザー情報の更新時に問題が発生しました。","adminTopPage","管理者ページに");
			return;
		}

		// ユーザー一覧に遷移
		response.sendRedirect("deleteUserInfoIndex?status=" + status);

	}

}
