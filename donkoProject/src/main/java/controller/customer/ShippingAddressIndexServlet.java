package controller.customer;

import java.io.IOException;
import java.util.ArrayList;

import bean.ShippingAddressBean;
import classes.ShippingAddress;
import classes.user.CustomerUser;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/shippingAddressIndex")
public class ShippingAddressIndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
    public ShippingAddressIndexServlet() {
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
		// ユーザIDをセット
		CustomerUser customerUser = new CustomerUser();
		customerUser.setUserId(userId);
		
		// 配送先一覧をメインの配送先順にソート SQL実行
		ArrayList<ShippingAddressBean> mainShippingAddressList = ShippingAddress.getMainShippingAddressSort(customerUser);
		request.setAttribute("mainShippingAddressList", mainShippingAddressList);
		
		// 配送先一覧表示 SQL実行
		ArrayList<ShippingAddressBean> shippingAddressList = ShippingAddress.getShippingAddressList(customerUser);
		request.setAttribute("shippingAddressList", shippingAddressList);
		
		// 配送先一覧
		String view = "/WEB-INF/views/customer/shippingAddressIndex.jsp";
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
		ShippingAddressBean shippingAddressBean = new ShippingAddressBean();
		
		// パラメーター作成
		shippingAddressBean.setAddressee(request.getParameter("addressee"));
		shippingAddressBean.setPostalCode(request.getParameter("postalcode"));
		shippingAddressBean.setAddress(request.getParameter("address"));
		shippingAddressBean.setUserId(userId);
		shippingAddressBean.setShippingAddressId(Integer.parseInt(request.getParameter("update_shipping_address")));
		ShippingAddress.updateMainShippingAddress(shippingAddressBean);
		
		// 配送先一覧画面
		response.sendRedirect("shippingAddressIndex");
	}
}
