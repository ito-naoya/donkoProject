package controller.user;

import java.io.IOException;

import classes.ErrorHandling;
import classes.user.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public LogoutServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Boolean logoutStatus = User.logout(request);
		
		if(logoutStatus == null || logoutStatus == false) {
			ErrorHandling.transitionToErrorPage(request, response, "ログアウトに失敗しました", "myPage", "マイページに");
		} else {
			response.sendRedirect("home");
		}
	}
}
