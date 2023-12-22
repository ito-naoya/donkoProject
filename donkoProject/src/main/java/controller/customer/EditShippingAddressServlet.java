package controller.customer;

import java.io.IOException;

import bean.ShippingAddressBean;
import classes.BeanValidation;
import classes.ErrorHandling;
import classes.ShippingAddress;
import interfaces.group.GroupB;
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
		Object userId = session.getAttribute("user_id");
		Object shippingAddrressId = Integer.parseInt(request.getParameter("shipping_address_id"));
		
		// 値がない場合はホーム画面に遷移
		if (userId == null || shippingAddrressId == null) {
			response.sendRedirect("home");
			return;
		} else {
			// shippingAddrressIdをセット
			session.setAttribute("shipping_address_id",shippingAddrressId);
		}
		
		// インスタンス生成
		ShippingAddressBean shippingAddressBean = new ShippingAddressBean();
		
		// ユーザーIDと配送先IDをセット
		shippingAddressBean.setUserId((int) userId);
		shippingAddressBean.setShippingAddressId((int) shippingAddrressId);
		
		// 配送先詳細情報を取得
		ShippingAddressBean shippingAddressEdit = ShippingAddress.getShippingAddressDetail(shippingAddressBean);
		
		// データの取得結果
		if(shippingAddressEdit == null) {
			//エラーページに遷移
			ErrorHandling.transitionToErrorPage(request,response,"配送先情報の取得に失敗しました","shippingAddressIndex","配送先一覧画面に");
			return;
		}
		
		request.setAttribute("shippingAddressEdit", shippingAddressEdit);
		
		// 配送先編集画面
		String view = "/WEB-INF/views/customer/editShippingAddress.jsp";
		request.getRequestDispatcher(view).forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// セッションを確認
		HttpSession session = request.getSession(false);
		Object userId = session.getAttribute("user_id");
		Object shippingAddrressId = Integer.parseInt(request.getParameter("shipping_address_id"));
		
		// 値がなければホーム画面に遷移
		if (userId == null || shippingAddrressId == null) {
			response.sendRedirect("home");
			return;
		} else {
			session.setAttribute("shipping_address_id",shippingAddrressId);
		}
		
		// パラメーター作成
		ShippingAddressBean shippingAddressBean = new ShippingAddressBean();
		shippingAddressBean.setUserId((int) userId);
		shippingAddressBean.setShippingAddressId((int) shippingAddrressId);
		shippingAddressBean.setAddressee(request.getParameter("addressee"));
		shippingAddressBean.setPostalCode(request.getParameter("postalcode"));
		shippingAddressBean.setAddress(request.getParameter("address"));
		
		// 入力チェック
		BeanValidation.validate(request, "shippingAddressIndex", shippingAddressBean, GroupB.class);
		
		// 配送先編集を実行
		Boolean updateStatus = ShippingAddress.updateShippingAddress(shippingAddressBean);
		
		// 編集処理結果
		if(!updateStatus) {
			//エラーページに遷移
			ErrorHandling.transitionToErrorPage(request,response,"配送先情報の更新に失敗しました","shippingAddressIndex","配送先一覧画面に");
			return;
		}
		
		// 配送先一覧画面表示
		response.sendRedirect("shippingAddressIndex");
	}
}
