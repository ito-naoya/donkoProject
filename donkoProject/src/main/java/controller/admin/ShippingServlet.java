package controller.admin;

import java.io.IOException;
import java.util.ArrayList;

import bean.PurchaseBean;
import classes.Purchase;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/shipping")
public class ShippingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ShippingServlet() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int purchaseId = Integer.parseInt(request.getParameter("purchaseId"));
		int shippingId = Integer.parseInt(request.getParameter("shippingId"));
		Integer purchase_id = Integer.valueOf(purchaseId);
		Integer shipping_id = Integer.valueOf(shippingId);
		if (purchase_id == null || shipping_id == null) {
			
		}
	
		PurchaseBean purchaseBean = new PurchaseBean();
		purchaseBean.setPurchaseId(purchaseId);
		purchaseBean.setShippingId(shippingId);
		
		Purchase.sendItems(purchaseBean);
		
		ArrayList<PurchaseBean> unshippingedItemList = Purchase.getUnshippingedItemListByDesc();
		request.setAttribute("unshippingedItemList", unshippingedItemList);
		
		String view = "/WEB-INF/views/admin/adminTopPage.jsp";
        request.getRequestDispatcher(view).forward(request, response);
	}

}
