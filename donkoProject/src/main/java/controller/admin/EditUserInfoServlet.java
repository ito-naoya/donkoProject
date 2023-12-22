package controller.admin;

import java.io.IOException;
import java.sql.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Path;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

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
		
		String status = request.getParameter("status");
		
		CustomerUser customerUser = new CustomerUser();
		// PostされたデータをBeanにセット
		customerUser.setUserId(Integer.valueOf(request.getParameter("userId")));
		customerUser.setUserLoginId(request.getParameter("user_login_id"));
		customerUser.setUserName(request.getParameter("user_name"));
		customerUser.setGender(request.getParameter("gender"));
		customerUser.setBirthday(Date.valueOf(request.getParameter("birthday")));
		customerUser.setDeleted(status.equals("delete") ? true : false);
		
		// Validator を取得
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        
        // バリデーションを実行
        Set<ConstraintViolation<CustomerUser>> result = validator.validate(customerUser);
        
        //バリデーションメッセージを保持するmapをnew
        Map<Path, String> validationMsg = new LinkedHashMap<Path, String>();
        
         //バリデーションメッセージのセット
        for(ConstraintViolation<CustomerUser> rs : result){
        validationMsg.put(rs.getPropertyPath(), rs.getMessage());
        }
        
        System.out.println(validationMsg);
		
//		// 更新処理実行
//		Boolean isCommit = AdminUser.updateUserInfoByAdmin(customerUser);
//		
//		//ユーザー情報の更新に失敗したとき
//		if(!isCommit) {
//			//エラーメッセージ
//			request.setAttribute("errorMessage", "ユーザー情報の更新時に問題が発生しました。");
//			//エラーページからの遷移先
//			request.setAttribute("url", "adminTopPage");
//			//エラーページ表示
//			String view = "/WEB-INF/views/component/message.jsp";
//			request.getRequestDispatcher(view).forward(request, response);
//			return;
//		}
//		
//		// ユーザー一覧に遷移
//		response.sendRedirect("deleteUserInfoIndex");
		
	}

}
