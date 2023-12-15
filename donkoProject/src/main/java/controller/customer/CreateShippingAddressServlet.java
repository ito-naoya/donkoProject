package controller.customer;

import java.io.IOException;
import java.util.ArrayList;

import bean.PurchaseBean;
import bean.ShippingAddressBean;
import classes.Purchase;
import classes.ShippingAddress;
import classes.user.CustomerUser;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/createShippingAddress")
public class CreateShippingAddressServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public CreateShippingAddressServlet() {
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
				
				// 配送先登録画面
				String view = "/WEB-INF/views/customer/createShippingAddress.jsp";
				request.getRequestDispatcher(view).forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// セッション確認
				HttpSession session = request.getSession(false);
				int userId = (int) session.getAttribute("user_id");
				if (userId == 0) {
					String view = "/WEB-INF/views/customer/home.jsp";
					request.getRequestDispatcher(view).forward(request, response);
				}
				
				// インスタス生成
				CustomerUser customerUser = new CustomerUser();
				ShippingAddressBean shippingAddressBean = new ShippingAddressBean();
				
				// メイン配送先の設定確認
				customerUser.setUserId(userId);
				ShippingAddressBean idAddressBean = ShippingAddress.getMainShippingAddress(customerUser);
				int shippingAddressId = idAddressBean.getShippingAddressId();
				int status;
				if(shippingAddressId == 0) {
					status = 1;
				} else {
					status = 0;
				}
				
				// バラメーターをセット
				shippingAddressBean.setUserId(userId);
				shippingAddressBean.setAddressee(request.getParameter("addresses"));
				shippingAddressBean.setPostalCode(request.getParameter("postcode"));
				shippingAddressBean.setAddress(request.getParameter("address"));
				shippingAddressBean.setMainShippingAddress(status);
				
				// 新規配送先を登録する
				ShippingAddress.registerNewShippingAddress(shippingAddressBean);
				
				// マイページに戻るためのデータセット
				customerUser.setUserId(userId);
				ArrayList<PurchaseBean> purchaseList = Purchase.getMyPurchaseHistory(customerUser);
				request.setAttribute("purchaseList", purchaseList);
				
				// 更新後の画面遷移
				String view = "/WEB-INF/views/customer/myPage.jsp";
				request.getRequestDispatcher(view).forward(request, response);
	}

}
