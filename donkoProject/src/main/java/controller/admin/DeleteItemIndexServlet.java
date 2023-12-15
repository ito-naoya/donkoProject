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

		//カテゴリー一覧を取得(戻り値がnullでもjspに渡す)
		ArrayList<ItemCategoryBean> categoryList = ItemCategory.getItemCategoryList();
		request.setAttribute("categoryList", categoryList);
		//初期表示として、「すべてのカテゴリの商品を、削除済みのものも含めて全て表示する」(戻り値がnullでもjspに渡す)
		ArrayList<ItemBean> itemList = Item.getItemAndOptionListAll("");
		request.setAttribute("itemList", itemList);

		//商品一覧画面に遷移
		String view = "/WEB-INF/views/admin/deleteItemIndex.jsp";
		request.getRequestDispatcher(view).forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//TODO:セッション管理
		//ログイン画面実装後にセットします
		//ソートを実行

		//カテゴリーを取得
		String itemCategory = request.getParameter("itemCategory");
		//表示方法を取得
		Integer sortParam = Integer.parseInt(request.getParameter("sortParam"));

		//以下、ソートSQL(戻り値がnullでもjspに渡す)
		ArrayList<ItemBean> itemList = null;
		if(!itemCategory.isEmpty() && sortParam == 2) { //sortParamが2なら、全商品を抽出
			itemList = Item.getItemAndOptionListAll(itemCategory);

		} else if(itemCategory.isEmpty() && sortParam < 2 ) { //sortParamが2未満なら、削除フラグを確認して抽出
			itemList = Item.getItemAndOptionListByDelFlg(sortParam, itemCategory);
		}
		request.setAttribute("itemList", itemList);
		
		String view = "/WEB-INF/views/admin/deleteItemIndex.jsp";
		request.getRequestDispatcher(view).forward(request, response);
	}

}
