package controller.customer;

import java.io.IOException;

import bean.ShippingAddressBean;
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

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
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

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
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
		if (userId == 0) {
			// エラー画面を返す
			errorHandling(request, response, "配送先登録処理に失敗しました", "createShippingAddress");
		} else {
			// userIdに値をセット
			customerUser.setUserId(userId);
		}

		// メイン配送先の設定が必要かを確認する処理
		ShippingAddressBean getMainShippingAddress = ShippingAddress.getMainShippingAddress(customerUser);
		int shippingAddressId = 0;
		if (getMainShippingAddress == null) {
			// エラー画面を返す
			errorHandling(request, response, "配送先登録処理に失敗しました", "createShippingAddress");
		} else {
			// shippingAddressIdが存在するか
			shippingAddressId = getMainShippingAddress.getShippingAddressId();
		}

		// shippingAddressIdの存在の有無によってメイン配送先のステータスを設定
		int status;
		if (shippingAddressId == 0) {
			// メイン配送先として作成
			status = 1;
		} else {
			// 追加の配送先として作成
			status = 0;
		}

		// ShippingAddressBeanに値をセット
		shippingAddressBean.setUserId(userId);
		shippingAddressBean.setAddressee(request.getParameter("addresses"));
		shippingAddressBean.setPostalCode(request.getParameter("postcode"));
		shippingAddressBean.setAddress(request.getParameter("address"));
		shippingAddressBean.setMainShippingAddress(status);

		// 新規配送先を登録する
		Boolean insertStatus = ShippingAddress.registerNewShippingAddress(shippingAddressBean);
		
		// 登録処理の結果
		if (insertStatus) {
			// 配送先一覧の画面に遷移
			response.sendRedirect("shippingAddressIndex");
		} else {
			// エラー画面を返す
			errorHandling(request, response, "配送先登録処理に失敗しました", "createShippingAddress");
		}
	}

	protected void errorHandling(HttpServletRequest request, HttpServletResponse response, String message, String url)
			throws ServletException, IOException {
		// エラーメッセージをセット
		request.setAttribute("errorMessage", message);

		// 戻り先のURL
		request.setAttribute("url", url);

		// TODO:全部できたらコメントアウト削除予定
		// 戻るボタンの表示文言
		// request.setAttribute("returnPage", returnPage);

		// エラー画面に遷移
		String view = "/WEB-INF/views/component/message.jsp";
		request.getRequestDispatcher(view).forward(request, response);
	}
}
