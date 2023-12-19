package controller.customer;

import java.io.IOException;

import bean.ShippingAddressBean;
import classes.ShippingAddress;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


@WebServlet("/deleteShippingAddress")
public class DeleteShippingAddressServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public DeleteShippingAddressServlet() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// セッションを確認
		HttpSession session = request.getSession(false);
		int userId = (int) session.getAttribute("user_id");
		if (userId == 0) {
			String view = "/WEB-INF/views/customer/home.jsp";
			request.getRequestDispatcher(view).forward(request, response);
		}
		
		// インスタンス生成
		ShippingAddressBean shippingAddressBean = new ShippingAddressBean();
		
		// PostされたデータをBeanにセット
		shippingAddressBean.setUserId(userId);
		shippingAddressBean.setShippingAddressId(Integer.parseInt(request.getParameter("shipping_address_id")));
		
		// 更新処理実行
		ShippingAddress.deleteShippingAddresses(shippingAddressBean);
		
		// マイページに遷移
		response.sendRedirect("shippingAddressIndex");
	}

}
