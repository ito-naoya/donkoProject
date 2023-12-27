package controller.admin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.stream.Collectors;

import bean.ItemBean;
import classes.BeanValidation;
import classes.ErrorHandling;
import classes.Item;
import classes.ItemManagementHelper;
import classes.Option;
import interfaces.group.GroupA;
import interfaces.group.GroupB;
import interfaces.group.GroupC;
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

		ItemBean newItem = new ItemBean();
		//TODO:セッション管理
		//カテゴリー、商品名、商品説明、金額、在庫数を取得
		newItem.setItemCategoryName(request.getParameter("itemCategoryName"));
		newItem.setItemName(request.getParameter("itemName"));
		newItem.setItemDescription(request.getParameter("itemDescription"));
		String price = request.getParameter("itemPrice");
		String stock = request.getParameter("itemStock");
		String redirectPath = "/WEB-INF/views/admin/registItem2.jsp";

	    // 画像情報を取得
	    Part imgPart = request.getPart("img");

	    //int型のフィールドの取得情報について、null値及び数値以外の値がないかどうか確認し、itemBeanに登録
  		newItem = Item.checkRegistItemDetail(newItem, price, stock);
  		if(newItem == null) {
  			//取得情報の不備があれば、再度入力画面に戻る
  			response.sendRedirect("registItem1");
  			return;
  		}
  		if(BeanValidation.validate(request, "item", newItem, GroupA.class)) {
  			ItemManagementHelper.errorRedirect(request,response,newItem,redirectPath);
  		}

	    // セレクトボックスの個数を取得
	    int selectBoxCount = Integer.parseInt(request.getParameter("selectBoxCount"));

	    // 最初のオプション（色）の値を取得
	    newItem.setItemFirstOptionName(request.getParameter("optionCategoryName_1"));
	    String itemFirstOptionIncrementId = request.getParameter("optionValue_1");
        String[] itemSecondOptionIncrementIds = null;
	    // オプション値の検証と追加
	    if (selectBoxCount == 1) {
	    	newItem = Option.checkRegistItemOptionDetail("0", newItem, itemFirstOptionIncrementId, null);
        } else {
        	newItem.setItemSecondOptionName(request.getParameter("optionCategoryName_2"));
            itemSecondOptionIncrementIds = request.getParameterValues("optionValue_2");
            newItem = Option.checkRegistItemOptionDetail("0", newItem, itemFirstOptionIncrementId, itemSecondOptionIncrementIds);
        }
	    if (newItem == null) {
	    	//不正な値を入力していた場合はリダイレクト
	    	ItemManagementHelper.errorRedirect(request,response,newItem,redirectPath);
	    }

        // 写真がセットされている事を確認して、写真名を設定(商品名＋オプションID)
	    String fileName = null;
	    if(imgPart.getSize() != 0) {
	        fileName = newItem.getItemName() + itemFirstOptionIncrementId;
	        newItem.setImageFileName(fileName);
	    }

        //バリデーションチェック
        if (selectBoxCount == 1) {
        	if(BeanValidation.validate(request, "item", newItem, GroupB.class)) {
        		ItemManagementHelper.errorRedirect(request,response,newItem,redirectPath);
        	}
        } else {
        	if(BeanValidation.validate(request, "item", newItem, GroupC.class)) {
        		ItemManagementHelper.errorRedirect(request,response,newItem,redirectPath);
        	}
        }

        String message = null;
        //商品名とオプションについて、既存のアイテムと重複がないか確認する
        ArrayList<Integer> existId = Item.checkItemAlreadyExist(newItem,itemSecondOptionIncrementIds);
        if(existId == null) {
        	ItemManagementHelper.errorRedirect(request,response,newItem,redirectPath);
        } else if (!existId.isEmpty()) { //商品が重複していた場合
        	String idsStr = existId.stream()
                    .map(String::valueOf)
                    .collect(Collectors.joining(", "));
        	message = "商品が重複しています。重複商品ID：" + idsStr;
        	ItemManagementHelper.deleteItemRedirect(response, newItem, message);
        	return;

        } else { //重複がなければ登録
	        if (Item.registerNewItem(newItem, selectBoxCount, itemSecondOptionIncrementIds)) {
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
	        message ="商品を登録しました";
        }

	    // 完了後、商品一覧ページに遷移
        ItemManagementHelper.deleteItemRedirect(response, newItem, message);

	}
}
