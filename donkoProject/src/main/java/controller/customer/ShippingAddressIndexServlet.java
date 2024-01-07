package controller.customer;

import java.io.IOException;
import java.util.ArrayList;

import bean.ShippingAddressBean;
import classes.BeanValidation;
import classes.ErrorHandling;
import classes.ShippingAddress;
import classes.user.CustomerUser;
import interfaces.group.GroupB;
import jakarta.servlet.RequestDispatcher;
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
		Object userId = session.getAttribute("user_id");
		if(userId == null) {
			response.sendRedirect("home");
			return;
		} else {
			// ヘッダー表示用
			String disp = "/header";
			RequestDispatcher dispatch = request.getRequestDispatcher(disp);
			dispatch.include(request, response);
		}
		
		String source = request.getParameter("source");
		request.setAttribute("url", source != null ? source : "myPage");
		
		// ユーザIDをセット
		CustomerUser customerUser = new CustomerUser();
		customerUser.setUserId((int) userId);
		
		// 配送先一覧をメインの配送先順にソートされたものを取得(メイン配送先更新用の一覧)
		ArrayList<ShippingAddressBean> mainShippingAddressList = ShippingAddress.getMainShippingAddressSort(customerUser);
		// 配送先一覧を取得
		ArrayList<ShippingAddressBean> shippingAddressList = ShippingAddress.getShippingAddressList(customerUser);
		
		if (mainShippingAddressList == null || shippingAddressList == null) {			
			// エラー画面に遷移
			ErrorHandling.transitionToErrorPage(request,response,"配送先一覧画面へのアクセスに失敗しました","myPage","マイページ画面に");
			return;
		} 
		
		
			// データ取得に成功した場合に値をセットする
			request.setAttribute("mainShippingAddressList", mainShippingAddressList);
			request.setAttribute("shippingAddressList", shippingAddressList);
			
			// 配送先一覧
			String view = "/WEB-INF/views/customer/shippingAddressIndex.jsp";
			request.getRequestDispatcher(view).forward(request, response);
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// セッション確認
		HttpSession session = request.getSession(false);
		Object userId = session.getAttribute("user_id");
		if(userId == null) {
			response.sendRedirect("home");
			return;
		}
		// インスタンス生成
		ShippingAddressBean shippingAddressBean = new ShippingAddressBean();
		
		// 値をセット
		shippingAddressBean.setAddressee(request.getParameter("addressee"));
		shippingAddressBean.setPostalCode(request.getParameter("postalcode"));
		shippingAddressBean.setAddress(request.getParameter("address"));
		shippingAddressBean.setUserId((int) userId);
		shippingAddressBean.setShippingAddressId(Integer.parseInt(request.getParameter("update_shipping_address")));
		
		// 入力チェック
		Boolean isIncomplete = BeanValidation.validate(request, "mainShippingAddressList", shippingAddressBean,GroupB.class);
		
		// 入力チェックの結果を判定
		if (isIncomplete) {
			// 配送先編集画面
			String view = "/WEB-INF/views/customer/editShippingAddress.jsp";
			request.getRequestDispatcher(view).forward(request, response);
			return;
		} 
		
		// メイン配送先の更新処理
		Boolean updateStatus = ShippingAddress.updateMainShippingAddress(shippingAddressBean);
		
		if (!updateStatus) {
			// エラー画面に遷移
			ErrorHandling.transitionToErrorPage(request,response,"メイン配送先の更新に失敗しました。","shippingAddressIndex","配送先一覧の画面に");
			return;
		} 
		
			// 配送先一覧画面
			response.sendRedirect("shippingAddressIndex");
	
	}
}
