package controller.admin;

import java.io.IOException;
import java.util.ArrayList;

import bean.PurchaseBean;
import classes.ErrorHandling;
import classes.Purchase;
import jakarta.servlet.RequestDispatcher;
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
		
		// null判定をするためにIntegerに変換
		Integer purchase_id = Integer.valueOf(purchaseId);
		Integer shipping_id = Integer.valueOf(shippingId);
		
		// 値がnullの場合はアドミンのホーム画面に遷移
		if (purchase_id == null || shipping_id == null) {
			response.sendRedirect("adminTopPage");
			return;
		}
		
		// Beanに値をセット
		PurchaseBean purchaseBean = new PurchaseBean();
		purchaseBean.setPurchaseId(purchaseId);
		purchaseBean.setShippingId(shippingId);
		
		// 配送ステータスの更新処理
		Boolean sendStatus = Purchase.sendItems(purchaseBean);
		
		if(!sendStatus) {
			 ErrorHandling.transitionToErrorPage(request, response, "発送処理に失敗しました","adminTopPage","管理者ページに");
			return;
		}
		
		// アドミンのホーム画面で表示するデータを取得
		ArrayList<PurchaseBean> unshippingedItemList = Purchase.getUnshippingedItemListByDesc();
		
		// 取得した値の状態によって挙動を条件分岐
		if (unshippingedItemList == null){
	        ErrorHandling.transitionToErrorPage(request, response, "データの取得に失敗しました","adminTopPage","管理者ページに");
			return;
		}
		
		// ヘッダーに表示する値を取得
		String disp = "/adminheader";
	    RequestDispatcher dispatch = request.getRequestDispatcher(disp);
	    dispatch.include(request, response);
		
		if (unshippingedItemList.size() > 0) {
			request.setAttribute("unshippingedItemList", unshippingedItemList);
			
		} else {
			request.setAttribute("message", "現在未発送の商品はありません");
		}
		
		String view = "/WEB-INF/views/admin/adminTopPage.jsp";
        request.getRequestDispatcher(view).forward(request, response);
	}

}
