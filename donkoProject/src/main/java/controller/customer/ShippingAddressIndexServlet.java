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
		Object userId = session.getAttribute("user_id");
		if(userId == null) {
			response.sendRedirect("home");
			return;
		}
		
		// ユーザIDをセット
		CustomerUser customerUser = new CustomerUser();
		customerUser.setUserId((int) userId);
		
		// 配送先一覧をメインの配送先順にソートされたものを取得(メイン配送先更新用の一覧)
		ArrayList<ShippingAddressBean> mainShippingAddressList = ShippingAddress.getMainShippingAddressSort(customerUser);
		// 配送先一覧を取得
		ArrayList<ShippingAddressBean> shippingAddressList = ShippingAddress.getShippingAddressList(customerUser);
		
		if (mainShippingAddressList == null || shippingAddressList == null) {
			errorHandling(request, response, "配送先一覧画面へのアクセスに失敗しました", "myPage", "マイページ画面に");
		} else {
			// データ取得に成功した場合に値をセットする
			request.setAttribute("mainShippingAddressList", mainShippingAddressList);
			request.setAttribute("shippingAddressList", shippingAddressList);
			
			// 配送先一覧
			String view = "/WEB-INF/views/customer/shippingAddressIndex.jsp";
			request.getRequestDispatcher(view).forward(request, response);
		}
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
		
		/*
		 * TODO:バリーションは後ほど
		 * */
		
		// メイン配送先の更新処理
		Boolean updateStatus = ShippingAddress.updateMainShippingAddress(shippingAddressBean);
		
		if (!updateStatus) {
			errorHandling(request, response, "メイン配送先の更新に失敗しました", "shippingAddressIndex", "配送先一覧の画面に");
		} else {
			// 配送先一覧画面
			response.sendRedirect("shippingAddressIndex");
		}
	}
	
	protected void errorHandling(HttpServletRequest request,  HttpServletResponse response, String message, String url, String returnPage) throws ServletException, IOException {
		// エラーメッセージをセット
		request.setAttribute("errorMessage", message);
		// 戻り先のURL
		request.setAttribute("url", url);
		// 戻るボタンの表示文言
		request.setAttribute("returnPage", returnPage);
			
		// エラー画面に遷移
		String view = "/WEB-INF/views/component/message.jsp";
		request.getRequestDispatcher(view).forward(request, response);
		}
}
