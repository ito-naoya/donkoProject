package controller.admin;

import java.io.IOException;
import java.util.ArrayList;

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
	String olditemFile = request.getParameter("itemImgFileName");
	Part imgPart = request.getPart("img");

	//取得情報について、null値及び文字数制限の超過が無いかどうか確認し、itemBeanに登録
	ItemBean updateitem = Item.checkRegistItemDetail(itemCategoryName, itemName, itemDescription, itemPrice, itemStock);
	if(updateitem == null) {
		//取得情報の不備があれば、再度入力画面に戻る
		response.sendRedirect("edititemInfo1");
		return;
	}

	//セレクトボックスの個数を取得
	int selectBoxCount = Integer.parseInt(request.getParameter("selectBoxCount"));

	//順番にitembanに格納<色：緑 など>
	String itemfirstOptionName = request.getParameter("optionCategoryName_1");
	String itemfirstOptionIncrementId = request.getParameter("optionValue_1");
	ItemBean updateitemaddOption;
	String[] secondIds = null;
	//取得したオプション情報について、null値チェック。IremBeansに値を追加。セレクトボックスが2つなら、2個目の値も格納<衣類サイズ：S　など>
	if( selectBoxCount == 2) {
		String itemsecondOptionName = request.getParameter("optionCategoryName_2");
	    String itemsecondOptionIncrementId = request.getParameter("optionValue_2");
	    secondIds = new String[] {itemsecondOptionIncrementId};
	    //取得情報について、null値及び文字数制限の超過が無いかどうか確認し、itemBeanに登録
	    updateitemaddOption = Option.checkRegistItemOptionDetail(itemId, updateitem, itemfirstOptionName, itemfirstOptionIncrementId, itemsecondOptionName, itemsecondOptionIncrementId, selectBoxCount);
	} else {
		//セレクトボックスが１つの場合
		updateitemaddOption = Option.checkRegistItemOptionDetail(itemId, updateitem, itemfirstOptionName, itemfirstOptionIncrementId,  null, null, selectBoxCount);
	}
	if(updateitemaddOption == null) {
		//取得情報の不備があれば、再度入力画面に戻る
		response.sendRedirect("edititemInfo1");
		return;
	}

	//写真名を設定
	String fileName = itemName + itemfirstOptionIncrementId;
	if(!fileName.equals(olditemFile)) {
		updateitemaddOption.setImageFileName(fileName);//商品名を変更した場合、新しい写真名になる
	} else {
		updateitemaddOption.setImageFileName(olditemFile);//商品名はそのままの場合
	}

	//商品名とオプションについて、既存のアイテムと重複がないか確認する
    ArrayList<Integer> existId = Item.checkItemAlreadyExist(updateitemaddOption,secondIds);
    if(existId == null) {
    	ErrorHandling.transitionToErrorPage(request,response,"商品情報の取得に失敗しました","adminTopPage","管理者ページに");
    } else if (!existId.isEmpty()) { //商品が重複していた場合
    	request.setAttribute("existId", "商品が重複しています。重複商品ID：" + existId.get(0));
    }else {
    	//itemsテーブルと、item_optionsテーブルを同じトランザクションで更新
    	if(Item.updateItemInfo(updateitemaddOption, selectBoxCount)) {
    		ServletContext context = getServletContext();
    		//画像をドキュメント内に保管
    		boolean renameImg = Item.renameNewImage(imgPart,fileName,olditemFile,context);
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
    	request.setAttribute("existId", "商品情報を変更しました");
	}

    request.setAttribute("itemDelFlg","0");
    // 完了後、商品一覧ページに遷移
	request.setAttribute("categoryList", itemCategoryName);
	String view = "deleteItemIndex";
	request.getRequestDispatcher(view).forward(request, response);
	}

}
