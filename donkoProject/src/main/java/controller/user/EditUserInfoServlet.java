package controller.user;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;

import bean.PurchaseBean;
import classes.Purchase;
import classes.user.CustomerUser;
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

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		int userId = (int) session.getAttribute("user_id");
		if (userId == 0) {
			String view = "/WEB-INF/views/customer/home.jsp";
			request.getRequestDispatcher(view).forward(request, response);
		}
		
		CustomerUser customerUser = new CustomerUser();
		customerUser.setUserId(user_id);
		
		// ユーザー情報取得
		CustomerUser users = CustomerUser.getUserDetail(customerUser);
		request.setAttribute("users", users);
		
		String view = "/WEB-INF/views/customer/userInfoEdit.jsp";
		request.getRequestDispatcher(view).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		int userId = (int) session.getAttribute("user_id");
		if (userId == 0) {
			String view = "/WEB-INF/views/customer/home.jsp";
			request.getRequestDispatcher(view).forward(request, response);
		}
		
		// インスタンス生成
		CustomerUser customerUser = new CustomerUser();
		
		// PostされたデータをBeanにセット
		customerUser.setUserId((int)session.getAttribute("user_id"));
		customerUser.setUserLoginId(request.getParameter("user_login_id"));
		customerUser.setUserName(request.getParameter("user_name"));
		customerUser.setGender(request.getParameter("gender"));
		customerUser.setBirthday(Date.valueOf(request.getParameter("birthday")));
		
		// 更新処理実行
		CustomerUser users = new CustomerUser();
		users.updateUserInfo(customerUser);
		
		// マイページに戻るためのデータセット
		ArrayList<PurchaseBean> purchaseList = Purchase.getMyPurchaseHistory(customerUser);
		request.setAttribute("purchaseList", purchaseList);
		
		// 更新後の画面遷移
		String view = "/WEB-INF/views/customer/myPage.jsp";
		request.getRequestDispatcher(view).forward(request, response);
		
		
	}
}
