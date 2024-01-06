package controller.customer;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/deleteAccount")
public class DeleteAccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
    public DeleteAccountServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// セッションの確認
		HttpSession session = request.getSession(false);
		if(session == null) {
			// セッションがなければホーム画面に遷移
			response.sendRedirect("home");
			return;
		}
		
		// ヘッダーの値を取得
		String disp = "/header";
	    RequestDispatcher dispatch = request.getRequestDispatcher(disp);
	    dispatch.include(request, response);
		
		String view = "/WEB-INF/views/customer/deleteAccount.jsp";
        request.getRequestDispatcher(view).forward(request, response);
	}
}
