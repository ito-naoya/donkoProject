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
		
		// Valueチェック
		if(users == null || users.getUserLoginId() == null || 
				users.getGender() == null || users.getBirthday() == null) {
			// エラー画面を返す
			errorHandling(request, response, "ユーザ編集画面へのアクセスに失敗しました", "userInfoPage");
		} else {
			// ユーザー情報の値をセット
			request.setAttribute("users", users);
		}
		
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
		
		// Valueチェック
		if (session.getAttribute("user_id") == null || request.getParameter("user_login_id") == null ||
				request.getParameter("user_name") == null || request.getParameter("gender") == null || 
				request.getParameter("birthday") == null
				) {
			// エラー画面を返す
			errorHandling(request,response,"編集処理に失敗しました","userInfoEdit");
		} else {
			// nullがなければ値をBeanにセットする
			customerUser.setUserId((int)session.getAttribute("user_id"));
			customerUser.setUserLoginId(request.getParameter("user_login_id"));
			customerUser.setUserName(request.getParameter("user_name"));
			customerUser.setGender(request.getParameter("gender"));
			customerUser.setBirthday(Date.valueOf(request.getParameter("birthday")));
		}
		
		// 更新処理実行
		Boolean updateStatus = User.updateUserInfo(customerUser);
		
		// 更新結果を判定
		if (updateStatus) {
			// マイページに遷移
			response.sendRedirect("userInfoPage");
		} else {
			// 処理が失敗した場合はエラー画面に返す
			errorHandling(request,response,"編集処理に失敗しました", "userInfoEdit");
		}
	}
	
	protected void errorHandling(HttpServletRequest request,  HttpServletResponse response, String message, String url) throws ServletException, IOException {
		// エラーメッセージをセット
		request.setAttribute("errorMessage", message);
		
		// 戻り先のURL
		request.setAttribute("url", url);
		
		// TODO:全部できたらコメントアウト削除予定
		// 戻るボタンの表示文言
		// request.setAttribute("returnPage", returnPage);
		
		// エラー画面に遷移
		String view = "/WEB-INF/views/component/message.jsp";
		request.getRequestDispatcher(view).forward(request, response);
	}
}
