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

@WebServlet("/editShippingAddress")
public class EditShippingAddressServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public EditShippingAddressServlet() {
		super();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// セッションを確認
		HttpSession session = request.getSession(false);
		int userId = (int) session.getAttribute("user_id");
		int shippingAdddrressId = Integer.parseInt(request.getParameter("shipping_address_id"));
		session.setAttribute("shipping_address_id",shippingAdddrressId);
		if (userId == 0) {
			String view = "/WEB-INF/views/customer/home.jsp";
			request.getRequestDispatcher(view).forward(request, response);
		}
		
		// インスタンス生成
		ShippingAddressBean shippingAddressBean = new ShippingAddressBean();
		
		// ユーザーIDと配送先IDをセット
		shippingAddressBean.setUserId(userId);
		shippingAddressBean.setShippingAddressId(shippingAdddrressId);
		
		//SQL実行
		ShippingAddressBean shippingAddressEdit = ShippingAddress.getShippingAddressDetail(shippingAddressBean);
		request.setAttribute("shippingAddressEdit", shippingAddressEdit);
		
		// 配送先編集画面
		String view = "/WEB-INF/views/customer/editShippingAddress.jsp";
		request.getRequestDispatcher(view).forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// セッションを確認
		HttpSession session = request.getSession(false);
		int userId = (int) session.getAttribute("user_id");
		int shippingAddressId = (int) session.getAttribute("shipping_address_id");
		if (userId == 0) {
			String view = "/WEB-INF/views/customer/home.jsp";
			request.getRequestDispatcher(view).forward(request, response);
		}
		
		// パラメーター作成
		ShippingAddressBean shippingAddressBean = new ShippingAddressBean();
		shippingAddressBean.setUserId(userId);
		shippingAddressBean.setShippingAddressId(shippingAddressId);
		shippingAddressBean.setAddressee(request.getParameter("addressee"));
		shippingAddressBean.setPostalCode(request.getParameter("postalcode"));
		shippingAddressBean.setAddress(request.getParameter("address"));
		ShippingAddress.updateShippingAddress(shippingAddressBean);
		
		// 配送先一覧画面表示
		response.sendRedirect("shippingAddressIndex");
	}
}
