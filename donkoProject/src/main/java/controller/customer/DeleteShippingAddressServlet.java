package controller.customer;

import java.io.IOException;

import bean.ShippingAddressBean;
import classes.ErrorHandling;
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
		Object userId = session.getAttribute("user_id");
		
		// userIdがない場合はホーム画面に遷移
		if (userId == null) {
			response.sendRedirect("home");
			return;
		}
		
		// インスタンス生成
		ShippingAddressBean shippingAddressBean = new ShippingAddressBean();
		
		// PostされたデータをBeanにセット
		shippingAddressBean.setUserId((int) userId);
		shippingAddressBean.setShippingAddressId(Integer.parseInt(request.getParameter("shipping_address_id")));
		
		// 更新処理実行
		Boolean deleteStatus = ShippingAddress.deleteShippingAddresses(shippingAddressBean);
		
		//配送先削除処理に失敗した場合
		if(!deleteStatus) {
			// エラー画面を返す
			ErrorHandling.transitionToErrorPage(request, response, "配送先削除処理に失敗しました", "shippingAddressIndex", "配送先一覧画面に");
			return;
		}
			
		// マイページに遷移
		response.sendRedirect("shippingAddressIndex");
	}

}
