package controller.customer;

import java.io.IOException;
import java.text.Normalizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import bean.ShippingAddressBean;
import classes.ErrorHandling;
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
		Object userId = session.getAttribute("user_id");

		// userIdがnullの場合はマイページに遷移
		if(userId == null) {
			response.sendRedirect("home");
			return;
		}
		// 配送先登録画面
		String view = "/WEB-INF/views/customer/createShippingAddress.jsp";
		request.getRequestDispatcher(view).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// インスタス生成
		CustomerUser customerUser = new CustomerUser();
		ShippingAddressBean shippingAddressBean = new ShippingAddressBean();
		
		// セッション確認
		HttpSession session = request.getSession(false);
		Object userId = session.getAttribute("user_id");

		// userIdがnullの場合はマイページに遷移
		if(userId == null) {
			response.sendRedirect("home");
			return;
		} else {
			// userIdがある場合は値をセット
			customerUser.setUserId((int)userId);
		}
		
		// メイン配送先の件数を取得
		Integer mainStatus = ShippingAddress.selectMainShippingAddressCount(customerUser);
		
		if (mainStatus == null) {
			// エラー画面を返す
			ErrorHandling.transitionToErrorPage(request, response, "配送先登録処理に失敗しました", "myPage", "マイページに");
			return;
		}
		
		int status;
		if (mainStatus == 0) {
			// メイン配送先として作成
			status = 1;
		} else {
			// 追加の配送先として作成
			status = 0;
		}
		
		// ShippingAddressBeanに値をセット
		shippingAddressBean.setUserId((int)userId);
		shippingAddressBean.setAddressee(request.getParameter("addresses"));
		shippingAddressBean.setPostalCode(regexPostalCodeCheck(request.getParameter("postalcode"))); // 正規表現で郵便番号を確認して変換
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
			ErrorHandling.transitionToErrorPage(request, response, "配送先登録処理に失敗しました", "myPage", "マイページに");
		}
	}
	
	// 全角数字を半角数字に変換するメソッド
	private String regexPostalCodeCheck(String postalCode) {
		// 正規表現のパターン
		String regex = "^[0-9]{7}+$";
		// 正規表現のパターンをセット
		Pattern pattern = Pattern.compile(regex);
		// 郵便番号の値が正規表現のパターンと一致するかセット
		Matcher matcher = pattern.matcher(postalCode);
		// 郵便番号の値と正規表現のパターンと照らし合わせた結果
		boolean regexPostalCode = matcher.matches();
		// 正規表現で照らし合わせた結果
		if(regexPostalCode) {
			// 半角数字7桁であればBeanに値をセット
			return postalCode;
		} else {
			// 半角数字7桁に置換してからBeanにセット
			String postalCodeSub = Normalizer.normalize(postalCode, Normalizer.Form.NFKC).replaceAll("[^\\p{ASCII}]", "");
			return postalCodeSub;
		}
	}
}
