package controller.admin;

import java.io.IOException;
import java.util.ArrayList;

import bean.ItemBean;
import bean.ItemCategoryBean;
import bean.OptionCategoryBean;
import classes.BeanValidation;
import classes.ErrorHandling;
import classes.Item;
import classes.ItemCategory;
import classes.Option;
import classes.OptionCategory;
import interfaces.group.GroupA;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/editItemInfo1")
public class EditItemInfo1Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public EditItemInfo1Servlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//商品idを取得して、商品詳細を取得
		Integer itemId = Integer.parseInt(request.getParameter("itemId"));
		ItemBean item = new ItemBean();
		item.setItemId(itemId);

		//商品詳細を取得
		ItemBean editItem = Item.getItemAllDetail(item);
		if(editItem == null) {
			//取得情報の不備があれば、エラー画面に遷移
			ErrorHandling.transitionToErrorPage(request,response,"商品詳細の取得に失敗しました","adminTopPage","管理者ページに");
			return;
		}

		//問題なければ商品編集画面1に遷移
		request.setAttribute("item", editItem);
		String view = "/WEB-INF/views/admin/editItemInfo1.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(view);
		dispatcher.forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//TODO:セッション管理
		//カテゴリー、商品名、商品説明、金額、在庫数を取得
		ItemBean updateItem = new ItemBean();
		String itemId = request.getParameter("itemId");
		updateItem.setItemCategoryName(request.getParameter("itemCategoryName"));
		updateItem.setItemName(request.getParameter("itemName"));
		updateItem.setItemDescription(request.getParameter("itemDescription"));
		String price = request.getParameter("price");
		String stock = request.getParameter("stock");
		updateItem.setImageFileName(request.getParameter("fileName"));
		String itemFirstOptionIncrementId = request.getParameter("firstOptionId");


		//入力した取得情報について、null値及び文字数制限の超過が無いかどうか確認し、itemBeanに登録
		updateItem = Item.checkRegistItemDetail(updateItem, price, stock);
		if(updateItem == null) {
			//取得情報の不備があれば、再度入力画面に戻る
			response.sendRedirect("editItem1");
			return;
		}

		//入力文字チェック。入力内容に不備があった場合、再度カテゴリーリストを作成して、元の画面にリダイレクト
		if(BeanValidation.validate(request, "item", updateItem, GroupA.class)) {
			//カテゴリー一覧を取得
			getCategoryList(request, response);
			String view = "/WEB-INF/views/admin/registItem1.jsp";
			request.getRequestDispatcher(view).forward(request, response);
			return;
		}

		//カテゴリー名からオプションを取得<衣類：色、衣類：衣類サイズ>
		ArrayList<ArrayList<OptionCategoryBean>> itemCategoryListAll = OptionCategory.getOptionCategoryListAllByCategory(updateItem);
		if(itemCategoryListAll == null) {
			//取得情報の不備があれば、エラー画面に遷移
			ErrorHandling.transitionToErrorPage(request,response,"カテゴリ一覧の取得に失敗しました","adminTopPage","管理者ページに");
			return;
		}

		// オプションの数を取得
	    int selectBoxCount = itemCategoryListAll.size();
		//商品の持つ他の情報について、null値及び文字数制限の超過が無いかどうか確認し、itemBeanに登録
	    if (selectBoxCount == 1) {
	    	updateItem = Option.checkRegistItemOptionDetail(itemId, updateItem, itemFirstOptionIncrementId, null);
        } else {
        	String secondOptionId = request.getParameter("secondOptionId");
    		String[] itemSecondOptionIncrementIds = {secondOptionId};
            updateItem = Option.checkRegistItemOptionDetail(itemId, updateItem, itemFirstOptionIncrementId, itemSecondOptionIncrementIds);
        }
	    if (updateItem == null) {
	    	//不正な値を入力していた場合はリダイレクト
	        edit2Foward(request, response, updateItem);
	    }

		//取得した商品情報をセット
		request.setAttribute("item", updateItem);

		//問題なければ商品編集画面2に遷移
		request.setAttribute("itemCategoryListAll", itemCategoryListAll);
		String view = "/WEB-INF/views/admin/editItemInfo2.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(view);
		dispatcher.forward(request, response);
	}

	private void edit2Foward(HttpServletRequest request, HttpServletResponse response, ItemBean newItem)
			throws ServletException, IOException {
		// オプション情報の不備があれば、再度入力画面に戻る
		ArrayList<ArrayList<OptionCategoryBean>> itemCategoryListAll = OptionCategory.getOptionCategoryListAllByCategory(newItem);
		request.setAttribute("itemCategoryListAll", itemCategoryListAll);
		request.setAttribute("newItem", newItem);
		String view = "/WEB-INF/views/admin/registItem2.jsp";
		request.getRequestDispatcher(view).forward(request, response);
	}

	//カテゴリ抽出メソッド
	private void getCategoryList(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ArrayList<ItemCategoryBean> categoryList = ItemCategory.getItemCategoryList();
		if(categoryList == null) {
			//取得情報の不備があれば、エラー画面に遷移
			ErrorHandling.transitionToErrorPage(request,response,"カテゴリ一覧の取得に失敗しました","adminTopPage","管理者ページに");
			return;
		}
		request.setAttribute("categoryList", categoryList);
	}
}

