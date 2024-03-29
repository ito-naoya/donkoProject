package controller.admin;

import java.io.IOException;
import java.util.ArrayList;

import bean.ItemBean;
import bean.OptionCategoryBean;
import classes.BeanValidation;
import classes.ErrorHandling;
import classes.Item;
import classes.ItemManagementHelper;
import classes.OptionCategory;
import interfaces.group.GroupA;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/registItem1")
public class RegistItemServlet1 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public RegistItemServlet1() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// ヘッダーに表示する値を取得
		String disp = "/adminheader";
	    RequestDispatcher dispatch = request.getRequestDispatcher(disp);
	    dispatch.include(request, response);

		HttpSession as = request.getSession();
		String adminSession = (String)as.getAttribute("admin");

		// アドミンのセッションがnullの場合は管理者ログイン画面に遷移
		if (adminSession == null) {
			response.sendRedirect("adminSignin");
			return;
		}
		//カテゴリー一覧を取得
		ItemManagementHelper.getCategoryList(request, response);

		ItemBean newItem = new ItemBean();
		request.setAttribute("item", newItem);

		request.setAttribute("existId", null);

		//商品登録画面1に転送
		String view = "/WEB-INF/views/admin/registItem1.jsp";
		request.getRequestDispatcher(view).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// ヘッダーに表示する値を取得
		String disp = "/adminheader";
	    RequestDispatcher dispatch = request.getRequestDispatcher(disp);
	    dispatch.include(request, response);

		HttpSession as = request.getSession();
		String adminSession = (String)as.getAttribute("admin");

		// アドミンのセッションがnullの場合は管理者ログイン画面に遷移
		if (adminSession == null) {
			response.sendRedirect("adminSignin");
			return;
		}

		ItemBean newItem = new ItemBean();
		//TODO:セッション管理
		//カテゴリー、商品名、商品説明、金額、在庫数を取得
		newItem.setItemCategoryName(request.getParameter("itemCategoryName"));
		newItem.setItemName(request.getParameter("itemName"));
		newItem.setItemDescription(request.getParameter("itemDescription"));
		String price = request.getParameter("price");
		String stock = request.getParameter("stock");

		//int型のフィールドの取得情報について、null値及び文字数制限の超過が無いかどうか確認し、itemBeanに登録
		newItem = Item.checkRegistItemDetail(newItem, price, stock);
		if(newItem == null) {
			//取得情報の不備があれば、再度入力画面に戻る
			response.sendRedirect("registItem1");
			return;
		}

		//入力文字チェック。入力内容に不備があった場合、再度カテゴリーリストを作成して、元の画面にリダイレクト
		if(BeanValidation.validate(request, "item", newItem, GroupA.class)) {
			//カテゴリー一覧を取得
			ItemManagementHelper.getCategoryList(request, response);
			String view = "/WEB-INF/views/admin/registItem1.jsp";
			request.getRequestDispatcher(view).forward(request, response);
			return;
  		}

		//取得した商品情報をセット
		request.setAttribute("newItem", newItem);

		//カテゴリー名からオプションを取得<衣類：色、衣類：衣類サイズ>
		ArrayList<ArrayList<OptionCategoryBean>> itemCategoryListAll = OptionCategory.getOptionCategoryListAllByCategory(newItem);
		if(itemCategoryListAll == null) {
			//取得情報の不備があれば、エラー画面に遷移
			ErrorHandling.transitionToErrorPage(request,response,"カテゴリ一覧の取得に失敗しました","adminTopPage","管理者ページに");
			return;
		}

		request.setAttribute("itemCategoryListAll", itemCategoryListAll);
		String view = "/WEB-INF/views/admin/registItem2.jsp";
		request.getRequestDispatcher(view).forward(request, response);
	}
}
