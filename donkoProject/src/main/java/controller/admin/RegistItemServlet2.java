package controller.admin;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;

import bean.ItemBean;
import bean.ItemCategoryBean;
import classes.ErrorHandling;
import classes.Item;
import classes.ItemCategory;
import classes.Option;
import jakarta.servlet.ServletContext;
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
        String[] itemSecondOptionIncrementIds = null;

	    // オプション値の検証と追加
	    ItemBean newItemAddOption;
	    if (selectBoxCount == 1) {
	        // オプションが1つの場合
	        newItemAddOption = Option.checkRegistItemOptionDetail("0", newItem, itemFirstOptionName, itemFirstOptionIncrementId, null, null, selectBoxCount);
	    } else {
	        // オプションが2つの場合
	        String itemSecondOptionName = request.getParameter("optionCategoryName_2");

            // チェックボックスでサイズを選択した場合
            itemSecondOptionIncrementIds = request.getParameterValues("optionValueC_2");
            newItemAddOption = Option.checkRegistItemOptionDetail("0", newItem, itemFirstOptionName, itemFirstOptionIncrementId, itemSecondOptionName, "0", selectBoxCount);
	    }

	    if (newItemAddOption == null) {
	        // オプション情報の不備があれば、再度入力画面に戻る
	        response.sendRedirect("registItem1");
	        return;
	    }
        // 写真名を設定(商品名＋オプションID)
        String fileName = itemName + itemFirstOptionIncrementId;
        newItemAddOption.setImageFileName(fileName);

        //商品名とオプションについて、既存のアイテムと重複がないか確認する
        ArrayList<Integer> existId = Item.checkItemAlreadyExist(newItemAddOption,itemSecondOptionIncrementIds);
        if(existId == null) {
        	ErrorHandling.transitionToErrorPage(request,response,"写真の取得に失敗しました","adminTopPage","管理者ページに");
        } else if (!existId.isEmpty()) { //商品が重複していた場合
        	request.setAttribute("existId", existId);
        	//カテゴリー一覧を取得
    		ArrayList<ItemCategoryBean> categoryList = ItemCategory.getItemCategoryList();
    		if(categoryList == null) {
    			//取得情報の不備があれば、エラー画面に遷移
    			ErrorHandling.transitionToErrorPage(request,response,"カテゴリ一覧の取得に失敗しました","adminTopPage","管理者ページに");
    		}
    		request.setAttribute("categoryList", categoryList);
        	String view = "/WEB-INF/views/admin/registItem1.jsp";
    		request.getRequestDispatcher(view).forward(request, response);
    		return;
        }

        if (Item.registerNewItem(newItemAddOption, selectBoxCount, itemSecondOptionIncrementIds)) {
        	ServletContext context = getServletContext();
            boolean imageSaved = Item.registerNewImage(imgPart, fileName, context);
            if (!imageSaved) {
                // 画像の登録に失敗した場合の処理
            	ErrorHandling.transitionToErrorPage(request, response, "写真の取得に失敗しました","adminTopPage","管理者ページに");
    			return;
            }
        } else {
            // データの登録に失敗した場合の処理
        	ErrorHandling.transitionToErrorPage(request, response, "商品の登録に失敗しました","adminTopPage","管理者ページに");
			return;
        }

	    // 完了後、商品一覧ページにリダイレクト
        String encodedItemCategoryName = URLEncoder.encode(itemCategoryName, "UTF-8");
        String redirectURL = "deleteItemIndex?itemCategoryName=" + encodedItemCategoryName + "&itemDelFlg=0";
        response.sendRedirect(redirectURL);

	}
}
