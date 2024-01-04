package controller.user;
import java.io.IOException;

import classes.user.CustomerUser;
import classes.user.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

//UserSigninServlet.java
@WebServlet("/userSignin1")
public class UserSigninServlet extends HttpServlet {
 private static final long serialVersionUID = 1L;

 public UserSigninServlet() {
     super();
 }

 protected void doGet(HttpServletRequest request, HttpServletResponse response)
         throws ServletException, IOException {
     // ログイン時の初期メッセージ
     request.setAttribute("errorMessage", "");
     String view = "/WEB-INF/views/customer/userSignin.jsp";
     request.getRequestDispatcher(view).forward(request, response);
 }

 protected void doPost(HttpServletRequest request, HttpServletResponse response)
         throws ServletException, IOException {
     // 入力フォームからユーザーIDとパスワードを受け取る
     String user_login_id = request.getParameter("user_login_id");
     String password = request.getParameter("password");

     // CustomerUserにユーザーが送信した情報をセット
     CustomerUser loginUser = new CustomerUser();
     loginUser.setUserLoginId(user_login_id);
     loginUser.setPassword(password);

     // ログイン処理を呼び出し
     User user = new User();

     try {
         User.login(loginUser);

         // ログイン成功時にはセッションにユーザー情報を保存して指定のページへリダイレクト
         HttpSession userSession = request.getSession();
         // ユーザーオブジェクトをセッションに保存
         userSession.setAttribute("user", loginUser); 
      // ログイン成功後のページへリダイレクト
         response.sendRedirect("home"); 
     } catch (Exception e) {
         // try-catch ブロック内で例外が発生した場合に、例外から取得したエラーメッセージを設定し、その後ログイン画面にフォワードする
    	 //空白の時も同様
         String errorMessage = e.getMessage();
         request.setAttribute("errorMessage", errorMessage);
         String view = "/WEB-INF/views/customer/userSignin.jsp";
         request.getRequestDispatcher(view).forward(request, response);
     }
 }
}