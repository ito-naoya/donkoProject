package controller.admin;

import java.io.IOException;
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
		//TODO:セッション管理
		//ログイン画面実装後にセットします

		//カテゴリー一覧を取得
		ArrayList<ItemCategoryBean> categoryList = ItemCategory.getItemCategoryList();
		if(categoryList == null) {
			//取得情報の不備があれば、エラー画面に遷移
			request.setAttribute("errorMessage", "カテゴリー一覧の取得に失敗しました");
			request.setAttribute("url","adminTopPage");
			String view = "/WEB-INF/views/component/message.jsp";
			request.getRequestDispatcher(view).forward(request, response);
		}

		//配列に「全ての商品」という項目を追加
		ItemCategoryBean allItem = new ItemCategoryBean();
		allItem.setItemCategoryName("全ての商品");
		categoryList.add(allItem);
		request.setAttribute("categoryName", "全ての商品");

		//初期設定の商品一覧値をセット（item_delete_flgの有無に関係なく表示させる）
		request.setAttribute("itemDelFlg", 1);

		request.setAttribute("categoryList", categoryList);
		//初期表示として、「すべてのカテゴリの商品を、削除済みのものも含めて全て表示する」(戻り値がnullでもjspに渡す)
		ArrayList<ItemBean> itemList = Item.getItemAndOptionListByDelFlg(1, "全ての商品");
		if(itemList == null) {
			//取得情報の不備があれば、エラー画面に遷移
			request.setAttribute("errorMessage", "商品一覧の取得に失敗しました");
			request.setAttribute("url","adminTopPage");
			String view = "/WEB-INF/views/component/message.jsp";
			request.getRequestDispatcher(view).forward(request, response);
		}
		request.setAttribute("itemList", itemList);

		//商品一覧画面に遷移
		String view = "/WEB-INF/views/admin/deleteItemIndex.jsp";
		request.getRequestDispatcher(view).forward(request, response);

	}

	//ソートを実行
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//TODO:セッション管理
		//ログイン画面実装後にセットします

		//カテゴリーを取得
		String itemCategoryName = request.getParameter("itemCategoryName");
		//表示方法を取得
		Integer sortParam = Integer.parseInt(request.getParameter("itemDelFlg"));

		//カテゴリー一覧を取得
		ArrayList<ItemCategoryBean> categoryList = ItemCategory.getItemCategoryList();
		if(categoryList == null) {
			//取得情報の不備があれば、エラー画面に遷移
			request.setAttribute("errorMessage", "カテゴリー一覧の取得に失敗しました");
			request.setAttribute("url","adminTopPage");
			String view = "/WEB-INF/views/component/message.jsp";
			request.getRequestDispatcher(view).forward(request, response);
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
			request.setAttribute("errorMessage", "商品一覧の取得に失敗しました");
			request.setAttribute("url","adminTopPage");
			String view = "/WEB-INF/views/component/message.jsp";
			request.getRequestDispatcher(view).forward(request, response);
		}

		request.setAttribute("itemList", itemList);
		//指定された表示方法をセット
		request.setAttribute("itemDelFlg", sortParam);

		String view = "/WEB-INF/views/admin/deleteItemIndex.jsp";
		request.getRequestDispatcher(view).forward(request, response);
	}

}
