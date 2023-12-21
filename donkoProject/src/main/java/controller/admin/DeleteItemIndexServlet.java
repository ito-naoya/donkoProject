package controller.admin;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;

import bean.ItemBean;
import bean.ItemCategoryBean;
import classes.Item;
import classes.ItemCategory;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/deleteItemIndex")
public class DeleteItemIndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public DeleteItemIndexServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//カテゴリーを取得
		String itemCategoryName = request.getParameter("itemCategoryName");
		//表示方法を取得
		Integer sortParam = Integer.parseInt(request.getParameter("itemDelFlg"));

		//カテゴリー一覧を取得
		ArrayList<ItemCategoryBean> categoryList = ItemCategory.getItemCategoryList();
		if(categoryList == null) {
			//取得情報の不備があれば、エラー画面に遷移
			errorHandling(request,response,"カテゴリ一覧の取得に失敗しました","adminTopPage","管理者ページに");
		}

		//配列に「全ての商品」という項目を追加
		ItemCategoryBean allItem = new ItemCategoryBean();
		allItem.setItemCategoryName("全ての商品");
		categoryList.add(allItem);
		//ソートで指定されたカテゴリをセット
		request.setAttribute("categoryName", itemCategoryName);
		//カテゴリ一覧をセット
		request.setAttribute("categoryList", categoryList);

		//以下、ソートSQL
		ArrayList<ItemBean> itemList = null;
		if(!itemCategoryName.isEmpty() && sortParam == 2) { //sortParamが2なら、全商品を抽出
			itemList = Item.getItemAndOptionListAll(itemCategoryName);

		} else if(!itemCategoryName.isEmpty() && sortParam < 2 ) { //sortParamが2未満なら、削除フラグを確認して抽出
			itemList = Item.getItemAndOptionListByDelFlg(sortParam, itemCategoryName);
		}

		if(itemList == null) {
			//取得情報の不備があれば、エラー画面に遷移
			errorHandling(request,response,"商品一覧の取得に失敗しました","adminTopPage","管理者ページに");
		}

		request.setAttribute("itemList", itemList);
		//指定された表示方法をセット
		request.setAttribute("itemDelFlg", sortParam);

		String view = "/WEB-INF/views/admin/deleteItemIndex.jsp";
		request.getRequestDispatcher(view).forward(request, response);

	}

	//ソートを実行
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//カテゴリーを取得
		String itemCategoryName = request.getParameter("itemCategoryName");
		//選択済みの商品ステータスを全て取得、配列に格納
		String[] itemStatus = request.getParameterValues("itemStatus");

		//選択済みの商品のステータスを切り替えるサーブレット
		if(itemStatus != null && itemStatus.length > 0) {
			Item.deleteItem(itemStatus);
		}

		//商品一覧ページに戻る（とりあえずリダイレクト）
		String encodedItemCategoryName = URLEncoder.encode(itemCategoryName, "UTF-8");
        String redirectURL = "deleteItemIndex?itemCategoryName=" + encodedItemCategoryName + "&itemDelFlg=" + "2";
        response.sendRedirect(redirectURL);
	}

	protected void errorHandling(HttpServletRequest request, HttpServletResponse response, String message, String url, String returnPage)
			throws ServletException, IOException {
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
