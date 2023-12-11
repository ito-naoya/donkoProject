package controller.customer;

import java.io.IOException;
import java.util.ArrayList;

import bean.PurchaseDetailBean;
import classes.PurchaseDetail;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/orderDetail")
public class OrderDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public OrderDetailServlet() {
		super();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 購入ID取得 
		int purchase_id = Integer.parseInt(request.getParameter("purchase_id"));
		request.setAttribute("purchase_id", purchase_id);
		
		PurchaseDetailBean purchaseDetailBean = new PurchaseDetailBean();
		purchaseDetailBean.setPurchaseId(purchase_id);
		ArrayList<PurchaseDetailBean> purchaseDetailList = PurchaseDetail.getPurchaseDetail(purchaseDetailBean);
		
		request.setAttribute("purchaseDetailList", purchaseDetailList);
		
		String view = "/WEB-INF/views/customer/orderDetail.jsp";
		request.getRequestDispatcher(view).forward(request, response);
		
	}
}
