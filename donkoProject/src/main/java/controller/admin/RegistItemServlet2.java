package controller.admin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.stream.Collectors;

import bean.ItemBean;
import bean.OptionCategoryBean;
import classes.BeanValidation;
import classes.ErrorHandling;
import classes.Item;
import classes.Option;
import classes.OptionCategory;
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

	    // 画像情報を取得
	    Part imgPart = request.getPart("img");

	    //int型のフィールドの取得情報について、null値及び文字数制限の超過が無いかどうか確認し、itemBeanに登録
  		newItem = Item.checkRegistItemDetail(newItem, price, stock);
  		if(newItem == null) {
  			//取得情報の不備があれば、再度入力画面に戻る
  			response.sendRedirect("registItem1");
  			return;
  		}
  		checkBalidation(request, response, newItem, GroupA.class);

	    // セレクトボックスの個数を取得
	    int selectBoxCount = Integer.parseInt(request.getParameter("selectBoxCount"));

	    // 最初のオプション（色）の値を取得
	    newItem.setItemFirstOptionName(request.getParameter("optionCategoryName_1"));
	    String itemFirstOptionIncrementId = request.getParameter("optionValueS_1");
        String[] itemSecondOptionIncrementIds = null;

	    // オプション値の検証と追加
	    if (selectBoxCount == 2) {
	        newItem.setItemSecondOptionName(request.getParameter("optionCategoryName_2"));
            // チェックボックスでoption2を選択した場合
            itemSecondOptionIncrementIds = request.getParameterValues("optionValueC_2");
        }
	    ItemBean newItemAddOption = Option.checkRegistItemOptionDetail("0", newItem, itemFirstOptionIncrementId);
	    if (newItemAddOption == null) {
	        regist2Foward(request, response, newItem);
	    }

        // 写真名を設定(商品名＋オプションID)
        String fileName = newItem.getItemName() + itemFirstOptionIncrementId;
        newItemAddOption.setImageFileName(fileName);

        //バリデーションチェック
        if (selectBoxCount == 1) {
        	checkBalidation(request, response, newItem, GroupB.class);
        } else {
        	checkBalidation(request, response, newItem, GroupC.class);
        }

        //商品名とオプションについて、既存のアイテムと重複がないか確認する
        ArrayList<Integer> existId = Item.checkItemAlreadyExist(newItemAddOption,itemSecondOptionIncrementIds);
        if(existId == null) {
        	regist2Foward(request, response, newItem);
        } else if (!existId.isEmpty()) { //商品が重複していた場合
        	String idsStr = existId.stream()
                    .map(String::valueOf)
                    .collect(Collectors.joining(", "));
        	String message = "商品が重複しています。重複商品ID：" + idsStr;
        	request.setAttribute("existId", message);

        } else { //重複がなければ登録
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
	        request.setAttribute("existId", "商品を登録しました");
        }
        request.setAttribute("existId", "test");
        request.setAttribute("itemDelFlg","0");
	    // 完了後、商品一覧ページに遷移
		request.setAttribute("categoryList", newItem.getItemCategoryName());
    	String view = "deleteItemIndex";
		request.getRequestDispatcher(view).forward(request, response);

	}

	private void regist2Foward(HttpServletRequest request, HttpServletResponse response, ItemBean newItem)
			throws ServletException, IOException {
		// オプション情報の不備があれば、再度入力画面に戻る
		ArrayList<ArrayList<OptionCategoryBean>> itemCategoryListAll = OptionCategory.getOptionCategoryListAllByCategory(newItem);
		request.setAttribute("itemCategoryListAll", itemCategoryListAll);
		request.setAttribute("newItem", newItem);
		String view = "/WEB-INF/views/admin/registItem2.jsp";
		request.getRequestDispatcher(view).forward(request, response);
	}

	private void checkBalidation(HttpServletRequest request, HttpServletResponse response, ItemBean newItem, Class<?> groupClass)
			throws ServletException, IOException {
		//入力文字チェック。入力内容に不備があった場合、オプションリストを作成して、元の画面にリダイレクト
		if(BeanValidation.validate(request, "item", newItem, groupClass)) {
			//オプション一覧を取得
			ArrayList<ArrayList<OptionCategoryBean>> itemCategoryListAll = OptionCategory.getOptionCategoryListAllByCategory(newItem);
			if(itemCategoryListAll == null) {
				//取得情報の不備があれば、エラー画面に遷移
				ErrorHandling.transitionToErrorPage(request,response,"カテゴリ一覧の取得に失敗しました","adminTopPage","管理者ページに");
				return;
			}
			String view = "/WEB-INF/views/admin/registItem2.jsp";
			request.getRequestDispatcher(view).forward(request, response);
			return;
		}
	}
}
