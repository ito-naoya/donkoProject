package controller.admin;

import java.io.IOException;
import java.net.URLEncoder;

import bean.ItemBean;
import classes.Item;
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

        if (Item.registerNewItem(newItemAddOption, selectBoxCount, itemSecondOptionIncrementIds)) {
        	ServletContext context = getServletContext();
            boolean imageSaved = Item.registerNewImage(imgPart, fileName, context);
            if (!imageSaved) {
                // 画像の登録に失敗した場合の処理
            	errorHandling(request,response,"写真の取得に失敗しました","adminTopPage","管理者ページに");
            }
        } else {
            // データの登録に失敗した場合の処理
        	errorHandling(request,response,"商品の登録に失敗しました","adminTopPage","管理者ページに");
        }

	    // 完了後、商品一覧ページにリダイレクト
        String encodedItemCategoryName = URLEncoder.encode(itemCategoryName, "UTF-8");
        String redirectURL = "deleteItemIndex?itemCategoryName=" + encodedItemCategoryName + "&itemDelFlg=" + "0";
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
