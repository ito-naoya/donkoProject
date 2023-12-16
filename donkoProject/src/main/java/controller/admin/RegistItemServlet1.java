package controller.admin;

import java.io.IOException;
import java.util.ArrayList;

import bean.ItemBean;
import bean.ItemCategoryBean;
import bean.OptionCategoryBean;
import classes.Item;
import classes.ItemCategory;
import classes.OptionCategory;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/registItem1")
public class RegistItemServlet1 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public RegistItemServlet1() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//TODO:セッション管理
		//ログイン画面実装後にセットします

		//カテゴリー一覧を取得
		ArrayList<ItemCategoryBean> categoryList = ItemCategory.getItemCategoryList();
		request.setAttribute("categoryList", categoryList);
		//商品登録画面1に転送
		request.setAttribute("errorMessage", "");
		String view = "/WEB-INF/views/admin/registItem1.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(view);
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//TODO:セッション管理
		//カテゴリー、商品名、商品説明、金額、在庫数を取得
		String itemCategoryName = request.getParameter("itemCategoryName");
		String itemName = request.getParameter("itemName");
		String itemDescription = request.getParameter("itemDescription");
		String price = request.getParameter("price");
		String stock = request.getParameter("stock");


		//取得情報について、null値及び文字数制限の超過が無いかどうか確認し、itemBeanに登録
		ItemBean newItem = Item.checkRegistItemDetail(itemCategoryName, itemName, itemDescription, price, stock);

		if(newItem == null) {
			//取得情報の不備があれば、再度入力画面に戻る
			response.sendRedirect("registItem1");
		} else {
			//取得した商品情報をセット
			request.setAttribute("newItem", newItem);

			//カテゴリー名からオプションを取得<衣類：色、衣類：衣類サイズ>
			ArrayList<ArrayList<OptionCategoryBean>> itemCategoryListAll = new ArrayList<ArrayList<OptionCategoryBean>>();
			ArrayList<ItemCategoryBean> itemCategoryList = ItemCategory.getItemOptionCategoryNameListByCategory(newItem);
			if(itemCategoryList == null) {
				//取得情報の不備があれば、再度入力画面に戻る
				response.sendRedirect("registItem1");
			} else {

				//各オプションが持っているオプションの数分for文を回す
				for (int i = 0; i < itemCategoryList.size(); i++) {
				    ItemCategoryBean itemCategory = itemCategoryList.get(i);

				    //オプションの詳細を取得する[色,1,緑],[色,2,白],[色,3,黒]
				    ArrayList<OptionCategoryBean> options = OptionCategory.getOptionCategoryListByCategory(itemCategory);
				    if(options == null) {
						//取得情報の不備があれば、再度入力画面に戻る
						response.sendRedirect("regisatItem1");
					} else {
						//詳細の配列を
						itemCategoryListAll.add(options);
					}
				}
				request.setAttribute("itemCategoryListAll", itemCategoryListAll);
				String view = "/WEB-INF/views/admin/registItem2.jsp";
				RequestDispatcher dispatcher = request.getRequestDispatcher(view);
				dispatcher.forward(request, response);
			}
		}
	}
}
