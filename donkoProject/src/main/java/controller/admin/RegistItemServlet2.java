package controller.admin;

import java.io.IOException;

import bean.ItemBean;
import classes.Item;
import classes.Option;
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

	    // 既に取得済みの商品情報を再取得
	    String itemCategoryName = request.getParameter("itemCategoryName");
	    String itemName = request.getParameter("itemName");
	    String itemDescription = request.getParameter("itemDescription");
	    String itemPrice = request.getParameter("itemPrice");
	    String itemStock = request.getParameter("itemStock");

	    // 画像情報を取得
	    Part imgPart = request.getPart("img");

	    // 取得情報のnull値及び文字数制限の超過を確認し、ItemBeanに登録
	    ItemBean newItem = Item.checkRegistItemDetail(itemCategoryName, itemName, itemDescription, itemPrice, itemStock);
	    if (newItem == null) {
	        // 取得情報の不備があれば、再度入力画面に戻る
	        response.sendRedirect("registItem1");
	        return;
	    }

	    // セレクトボックスの個数を取得
	    int selectBoxCount = Integer.parseInt(request.getParameter("selectBoxCount"));

	    // 最初のオプション（色）の値を取得
	    String itemFirstOptionName = request.getParameter("optionCategoryName_1");
	    String itemFirstOptionIncrementId = request.getParameter("optionValueS_1");
	    String itemSecondOptionIncrementId = null;
        String[] itemSecondOptionIncrementIds = null;

	    // オプション値の検証と追加
	    ItemBean newItemAddOption;
	    if (selectBoxCount == 1) {
	        // オプションが1つの場合
	        newItemAddOption = Option.checkRegistItemOptionDetail(newItem, itemFirstOptionName, itemFirstOptionIncrementId, null, null, selectBoxCount);
	    } else {
	        // オプションが2つの場合
	        String sizeDisplayType = request.getParameter("sizeDisplayType");
	        String itemSecondOptionName = request.getParameter("optionCategoryName_2");

	        if (sizeDisplayType.equals("select")) {
	            // セレクトボックスでサイズを選択した場合
	            itemSecondOptionIncrementId = request.getParameter("optionValueS_2");
	            newItemAddOption = Option.checkRegistItemOptionDetail(newItem, itemFirstOptionName, itemFirstOptionIncrementId, itemSecondOptionName, itemSecondOptionIncrementId, selectBoxCount);
	        } else {
	            // チェックボックスでサイズを選択した場合
	            itemSecondOptionIncrementIds = request.getParameterValues("optionValueC_2");
	            newItemAddOption = Option.checkRegistItemOptionDetail(newItem, itemFirstOptionName, itemFirstOptionIncrementId, itemSecondOptionName, "0", selectBoxCount);
	        }
	    }

	    if (newItemAddOption == null) {
	        // オプション情報の不備があれば、再度入力画面に戻る
	        response.sendRedirect("registItem1");
	        return;
	    }
        // 写真名を設定(商品名＋オプションID)
        String fileName = itemName + itemFirstOptionIncrementId;
        newItemAddOption.setImageFileName(fileName);

        // itemsテーブルと、item_optionsテーブルを同じトランザクションで更新
        if(Item.registerNewItem(newItemAddOption, selectBoxCount, itemSecondOptionIncrementIds)) {

        }

        // 画像をドキュメント内に保管
        Item.registerNewImage(imgPart, fileName, null);

	    // 完了後、商品一覧ページにリダイレクト
	    response.sendRedirect("deleteItemIndex");
	}
}
