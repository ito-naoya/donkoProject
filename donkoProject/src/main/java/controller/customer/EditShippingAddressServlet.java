package controller.customer;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet("/editShippingAddress")
public class EditShippingAddressServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public EditShippingAddressServlet() {
        super();
    }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 配送先編集画面
		String view = "/WEB-INF/views/customer/editShippingAddress.jsp";
		request.getRequestDispatcher(view).forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
