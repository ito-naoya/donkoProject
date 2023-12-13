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

		//カテゴリー一覧を取得
		ArrayList<ItemCategoryBean> categoryList = ItemCategory.getItemCategoryList();
		request.setAttribute("categoryList", categoryList);

		//商品登録画面1に転送
		request.setAttribute("errorMessage", "");

		//商品詳細を取得
		ItemBean editItem = Item.getItemAllDetail(item);
		request.setAttribute("item", editItem);
		String view = "/WEB-INF/views/admin/editItemInfo1.jsp";
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
		String fileName = request.getParameter("fileName");
		int firstOptionId = Integer.parseInt(request.getParameter("firstOptionId"));
		int secondOptionId = Integer.parseInt(request.getParameter("secondOptionId"));

		//取得情報について、null値及び文字数制限の超過が無いかどうか確認し、itemBeanに登録
		ItemBean updateItem = Item.checkRegistItemDetail(itemCategoryName, itemName, itemDescription, price, stock);

		if(updateItem == null) {
			//取得情報の不備があれば、再度入力画面に戻る
			response.sendRedirect("registItem1");
		} else {
			//オプション属性をセット
			updateItem.setImageFileName(fileName);
			updateItem.setItemFirstOptionIncrementId(firstOptionId);
			updateItem.setItemSecondOptionIncrementId(secondOptionId);
			//取得した商品情報をセット
			request.setAttribute("item", updateItem);

			//カテゴリー名からオプションを取得<衣類：色、衣類：衣類サイズ>
			ArrayList<ArrayList<OptionCategoryBean>> itemCategoryListAll = new ArrayList<ArrayList<OptionCategoryBean>>();
			ArrayList<ItemCategoryBean> itemCategoryList = ItemCategory.getItemOptionCategoryNameListByCategory(updateItem);
			if(itemCategoryList == null) {
				//取得情報の不備があれば、再度入力画面に戻る
				response.sendRedirect("editItem1");
			} else {

				//各オプションが持っているオプションの数分for文を回す
				for (int i = 0; i < itemCategoryList.size(); i++) {
				    ItemCategoryBean itemCategory = itemCategoryList.get(i);

				    //オプションの詳細を取得する[色,1,緑],[色,2,白],[色,3,黒]
				    ArrayList<OptionCategoryBean> options = OptionCategory.getOptionCategoryListByCategory(itemCategory);
				    if(options == null) {
						//取得情報の不備があれば、再度入力画面に戻る
						response.sendRedirect("editItem1");
					} else {
						//詳細の配列を追加
						itemCategoryListAll.add(options);
					}
				}
				request.setAttribute("itemCategoryListAll", itemCategoryListAll);
				String view = "/WEB-INF/views/admin/editItemInfo2.jsp";
				RequestDispatcher dispatcher = request.getRequestDispatcher(view);
				dispatcher.forward(request, response);
				//TODOチャレンジ；商品名から既存の登録済みオプションと写真情報を取得してjsp上で選択不可にする
			}
		}
	}
}

