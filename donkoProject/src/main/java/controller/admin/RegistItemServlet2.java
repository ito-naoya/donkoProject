package controller.admin;

import java.io.IOException;

import bean.ItemBean;
import classes.Item;
import classes.Option;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

@MultipartConfig
@WebServlet("/registItem2")
public class RegistItemServlet2 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public RegistItemServlet2() {
		super();
	}

//	protected void doGet(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//
//		response.getWriter().append("Served at: ").append(request.getContextPath());
//	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//regist1で既に取得済みの情報を再度獲得
        String itemCategoryName = request.getParameter("itemCategoryName");
        String itemName = request.getParameter("itemName");
        String itemDescription = request.getParameter("itemDescription");
        String itemPrice = request.getParameter("itemPrice");
        String itemStock = request.getParameter("itemStock");
        Part imgPart = request.getPart("img");
		//取得情報について、null値及び文字数制限の超過が無いかどうか確認し、itemBeanに登録
		ItemBean newItem = Item.checkRegistItemDetail(itemCategoryName, itemName, itemDescription, itemPrice, itemStock);
		if(newItem == null) {
			//取得情報の不備があれば、再度入力画面に戻る
			response.sendRedirect("registItem1");
		}
		//セレクトボックスの個数を取得
		int selectBoxCount = Integer.parseInt(request.getParameter("selectBoxCount"));

		//順番にItembanに格納<色：緑 など>
		String ItemfirstOptionName = request.getParameter("optionCategoryName_1");
        String ItemfirstOptionIncrementId = request.getParameter("optionValue_1");
        ItemBean newItemaddOption;
        //取得したオプション情報について、null値チェック。IremBeansに値を追加。セレクトボックスが2つなら、2個目の値も格納<衣類サイズ：S　など>
		if( selectBoxCount == 2) {
			String ItemsecondOptionName = request.getParameter("optionCategoryName_2");
	        String ItemsecondOptionIncrementId = request.getParameter("optionValue_2");
	        newItemaddOption = Option.checkRegistItemOptionDetail(newItem, ItemfirstOptionName, ItemfirstOptionIncrementId, ItemsecondOptionName, ItemsecondOptionIncrementId, selectBoxCount);
		} else {
			//セレクトボックスが１つの場合
			newItemaddOption = Option.checkRegistItemOptionDetail(newItem, ItemfirstOptionName, ItemfirstOptionIncrementId,  null, null, selectBoxCount);
		}

		if(newItem == null) {
			//取得情報の不備があれば、再度入力画面に戻る
			response.sendRedirect("registItem1");
		} else {
			//写真名を設定
			String fileName = itemName + ItemfirstOptionIncrementId;
			newItemaddOption.setImageFileName(fileName);
			//itemsテーブルと、item_optionsテーブルを同じトランザクションで更新
			Item.registerNewItem(newItemaddOption, selectBoxCount);

			//画像をドキュメント内に保管
			Item.registerNewImage(imgPart,fileName);
		}

		String view = "deleteItemIndex";
		RequestDispatcher dispatcher = request.getRequestDispatcher(view);
		dispatcher.forward(request, response);
	}

}
