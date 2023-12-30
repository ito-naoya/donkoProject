package controller.customer;

import java.io.IOException;
import java.text.Normalizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
		Object shippingAddrressId = session.getAttribute("shipping_address_id");
		
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
		shippingAddressBean.setPostalCode(regexPostalCodeCheck(request.getParameter("postalcode"))); // 正規表現メソッドで数値確認
		shippingAddressBean.setAddress(request.getParameter("address"));
		
		// 入力チェック
		Boolean isIncomplete = BeanValidation.validate(request, "shippingAddressEdit", shippingAddressBean, GroupB.class);
		
		//入力内容に不備があった場合
		if(isIncomplete) {
			String view = "/WEB-INF/views/customer/editShippingAddress.jsp";
			request.getRequestDispatcher(view).forward(request, response);
			return;
		}
		
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
