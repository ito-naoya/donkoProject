package controller.admin;

import java.io.IOException;
import java.net.URLEncoder;

import bean.ItemBean;
import classes.ErrorHandling;
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
@WebServlet("/editItemInfo2")
public class EditItemInfo2Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public EditItemInfo2Servlet() {
		super();
	}
//	protected void doGet(HttpServletRequest request, HttpServletResponse response)
//	throws ServletException, IOException {
//
//response.getWriter().append("Served at: ").append(request.getContextPath());
//}


	protected void doPost(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {

	//regist1で既に取得済みの情報を再度獲得
	String itemId = request.getParameter("itemId");
	String itemCategoryName = request.getParameter("itemCategoryName");
	String itemName = request.getParameter("itemName");
	String itemDescription = request.getParameter("itemDescription");
	String itemPrice = request.getParameter("itemPrice");
	String itemStock = request.getParameter("itemStock");
	String oldItemFile = request.getParameter("itemImgFileName");
	Part imgPart = request.getPart("img");

	//取得情報について、null値及び文字数制限の超過が無いかどうか確認し、itemBeanに登録
	ItemBean updateItem = Item.checkRegistItemDetail(itemCategoryName, itemName, itemDescription, itemPrice, itemStock);
	if(updateItem == null) {
		//取得情報の不備があれば、再度入力画面に戻る
		response.sendRedirect("editItemInfo1");
		return;
	}

	//セレクトボックスの個数を取得
	int selectBoxCount = Integer.parseInt(request.getParameter("selectBoxCount"));

	//順番にItembanに格納<色：緑 など>
	String ItemfirstOptionName = request.getParameter("optionCategoryName_1");
	String ItemfirstOptionIncrementId = request.getParameter("optionValue_1");
	ItemBean updateItemaddOption;
	//取得したオプション情報について、null値チェック。IremBeansに値を追加。セレクトボックスが2つなら、2個目の値も格納<衣類サイズ：S　など>
	if( selectBoxCount == 2) {
		String ItemsecondOptionName = request.getParameter("optionCategoryName_2");
	    String ItemsecondOptionIncrementId = request.getParameter("optionValue_2");
	    //取得情報について、null値及び文字数制限の超過が無いかどうか確認し、itemBeanに登録
	    updateItemaddOption = Option.checkRegistItemOptionDetail(itemId, updateItem, ItemfirstOptionName, ItemfirstOptionIncrementId, ItemsecondOptionName, ItemsecondOptionIncrementId, selectBoxCount);
	} else {
		//セレクトボックスが１つの場合
		updateItemaddOption = Option.checkRegistItemOptionDetail(itemId, updateItem, ItemfirstOptionName, ItemfirstOptionIncrementId,  null, null, selectBoxCount);
	}
	if(updateItemaddOption == null) {
		//取得情報の不備があれば、再度入力画面に戻る
		response.sendRedirect("editItemInfo1");
		return;
	}

	//写真名を設定
	String fileName = itemName + ItemfirstOptionIncrementId;
	if(!fileName.equals(oldItemFile)) {
		updateItemaddOption.setImageFileName(fileName);//商品名を変更した場合、新しい写真名になる
	} else {
		updateItemaddOption.setImageFileName(oldItemFile);//商品名はそのままの場合
	}

	//itemsテーブルと、item_optionsテーブルを同じトランザクションで更新
	if(Item.updateItemInfo(updateItemaddOption, selectBoxCount)) {
		ServletContext context = getServletContext();
		//画像をドキュメント内に保管
		boolean renameImg = Item.renameNewImage(imgPart,fileName,oldItemFile,context);
		if (!renameImg) {
			// データの登録に失敗した場合の処理
        	ErrorHandling.transitionToErrorPage(request,response,"写真の登録に失敗しました","adminTopPage","管理者ページに");
			return;
        }
    } else {
    	// データの登録に失敗した場合の処理
    	ErrorHandling.transitionToErrorPage(request,response,"商品の登録に失敗しました","adminTopPage","管理者ページに");
		return;
    	
    }

	// 完了後、商品一覧ページにリダイレクト
    String encodedItemCategoryName = URLEncoder.encode(itemCategoryName, "UTF-8");
    String redirectURL = "deleteItemIndex?itemCategoryName=" + encodedItemCategoryName + "&itemDelFlg=0";
    response.sendRedirect(redirectURL);
	}
	
}
