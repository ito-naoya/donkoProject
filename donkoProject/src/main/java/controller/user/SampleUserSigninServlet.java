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

@WebServlet("/userSignin")
public class SampleUserSigninServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public SampleUserSigninServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String view = "/WEB-INF/views/customer/sampleUserSignin.jsp";
		request.getRequestDispatcher(view).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String userLoginId = request.getParameter("userLoginId");
		String userLoginPass = request.getParameter("userLoginPass");
		
		CustomerUser cu = new CustomerUser();
		cu.setUserLoginId(userLoginId);
		cu.setPassword(userLoginPass);
		
		CustomerUser customerUser = User.sampleLogin(cu);
		
		if(customerUser == null) {
			request.setAttribute("errorMessage", "IDまたはパスワードが異なります");
			String view = "/WEB-INF/views/customer/sampleUserSignin.jsp";
			request.getRequestDispatcher(view).forward(request, response);
			return;
		}
		
		HttpSession session = request.getSession();
		session.setAttribute("user_id", customerUser.getUserId());
		
		response.sendRedirect("home");
		
	}

}
