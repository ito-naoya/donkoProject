package controller.admin;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;

import bean.ItemBean;
import bean.ItemCategoryBean;
import bean.OptionCategoryBean;
import classes.BeanValidation;
import classes.ErrorHandling;
import classes.Item;
import classes.ItemCategory;
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

		//edit1で既に取得済みの情報を再度獲得
		ItemBean updateItem = new ItemBean();
		String itemId = request.getParameter("itemId");
		updateItem.setItemCategoryName(request.getParameter("itemCategoryName"));
		updateItem.setItemName(request.getParameter("itemName"));
		updateItem.setItemDescription(request.getParameter("itemDescription"));
		String price = request.getParameter("itemPrice");
		String stock = request.getParameter("itemStock");
		String olditemFile = request.getParameter("itemImgFileName");
		Part imgPart = request.getPart("img");

		//入力した取得情報について、null値及び文字数制限の超過が無いかどうか確認し、itemBeanに登録
		updateItem = Item.checkRegistItemDetail(updateItem, price, stock);
		if(updateItem == null) {
			//取得情報の不備があれば、再度入力画面に戻る
			response.sendRedirect("editItem1");
			return;
		}

		//入力文字チェック。入力内容に不備があった場合、再度カテゴリーリストを作成して、元の画面にリダイレクト
		if(BeanValidation.validate(request, "item", updateItem, GroupA.class)) {
			//カテゴリー一覧を取得
			getCategoryList(request, response);
			String view = "/WEB-INF/views/admin/editItem1.jsp";
			request.getRequestDispatcher(view).forward(request, response);
			return;
		}

		//セレクトボックスの個数を取得
		int selectBoxCount = Integer.parseInt(request.getParameter("selectBoxCount"));

		//順番にitembanに格納<色：緑 など>
		updateItem.setItemFirstOptionName(request.getParameter("optionCategoryName_1"));
		String itemFirstOptionIncrementId = request.getParameter("optionValue_1");
		String[] secondIds = null;
		//取得したオプション情報について、null値チェック。IremBeansに値を追加。セレクトボックスが2つなら、2個目の値も格納<衣類サイズ：S　など>
		//商品の持つ他の情報について、null値及び文字数制限の超過が無いかどうか確認し、itemBeanに登録
	    if (selectBoxCount == 1) {
	    	updateItem = Option.checkRegistItemOptionDetail(itemId, updateItem, itemFirstOptionIncrementId, null);
	    } else {
	    	updateItem.setItemSecondOptionName(request.getParameter("optionCategoryName_2"));
	    	String secondOptionId = request.getParameter("optionValue_2");
			String[] itemSecondOptionIncrementIds = {secondOptionId};
	        updateItem = Option.checkRegistItemOptionDetail(itemId, updateItem, itemFirstOptionIncrementId, itemSecondOptionIncrementIds);
	    }
	    if (updateItem == null) {
	    	//不正な値を入力していた場合はリダイレクト
	        edit2Foward(request, response, updateItem);
	    }

		//写真名を設定
		String fileName = updateItem.getItemName() + itemFirstOptionIncrementId;
		if(!fileName.equals(olditemFile)) {
			updateItem.setImageFileName(fileName);//商品名を変更した場合、新しい写真名になる
		} else {
			updateItem.setImageFileName(olditemFile);//商品名はそのままの場合
		}

		//バリデーションチェック
	    if (selectBoxCount == 1) {
	    	checkBalidation(request, response, updateItem, GroupB.class);
	    } else {
	    	checkBalidation(request, response, updateItem, GroupC.class);
	    }


	    String message = null;
		//商品名とオプションについて、既存のアイテムと重複がないか確認する
	    if(!fileName.equals(olditemFile)){
		    ArrayList<Integer> existId = Item.checkItemAlreadyExist(updateItem,secondIds);
		    if(existId == null) {
		    	ErrorHandling.transitionToErrorPage(request,response,"商品情報の取得に失敗しました","adminTopPage","管理者ページに");
		    } else if (!existId.isEmpty()) { //商品が重複していた場合
		    	message = "商品が重複しています。重複商品ID：" + existId.get(0);
		    	deleteItemRedirect(response, updateItem, message);
			    return;
		    }
	    }

    	//itemsテーブルと、item_optionsテーブルを同じトランザクションで更新
    	if(Item.updateItemInfo(updateItem, selectBoxCount)) {
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

    	message ="商品を編集しました";
	    deleteItemRedirect(response, updateItem, message);
	    return;
	}

	private void deleteItemRedirect(HttpServletResponse response, ItemBean updateItem, String message)
			throws UnsupportedEncodingException, IOException {
		String encodedItemCategoryName = URLEncoder.encode(updateItem.getItemCategoryName(), "UTF-8");
		String encodedMessage = URLEncoder.encode(message, "UTF-8");
		String redirectURL = "deleteItemIndex?itemCategoryName=" + encodedItemCategoryName + "&itemDelFlg=" + "0" + "&message=" + encodedMessage;
		response.sendRedirect(redirectURL);
	}

	private void edit2Foward(HttpServletRequest request, HttpServletResponse response, ItemBean newItem)
			throws ServletException, IOException {
		// オプション情報の不備があれば、再度入力画面に戻る
		ArrayList<ArrayList<OptionCategoryBean>> itemCategoryListAll = OptionCategory.getOptionCategoryListAllByCategory(newItem);
		request.setAttribute("itemCategoryListAll", itemCategoryListAll);
		request.setAttribute("newItem", newItem);
		String view = "/WEB-INF/views/admin/editItem2.jsp";
		request.getRequestDispatcher(view).forward(request, response);
	}

	//カテゴリ抽出メソッド
	private void getCategoryList(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ArrayList<ItemCategoryBean> categoryList = ItemCategory.getItemCategoryList();
		if(categoryList == null) {
			//取得情報の不備があれば、エラー画面に遷移
			ErrorHandling.transitionToErrorPage(request,response,"カテゴリ一覧の取得に失敗しました","adminTopPage","管理者ページに");
			return;
		}
		request.setAttribute("categoryList", categoryList);
	}

	private void checkBalidation(HttpServletRequest request, HttpServletResponse response, ItemBean updateItem, Class<?> groupClass)
			throws ServletException, IOException {
		//入力文字チェック。入力内容に不備があった場合、オプションリストを作成して、元の画面にリダイレクト
		if(BeanValidation.validate(request, "item", updateItem, groupClass)) {
			//オプション一覧を取得
			ArrayList<ArrayList<OptionCategoryBean>> itemCategoryListAll = OptionCategory.getOptionCategoryListAllByCategory(updateItem);
			if(itemCategoryListAll == null) {
				//取得情報の不備があれば、エラー画面に遷移
				ErrorHandling.transitionToErrorPage(request,response,"カテゴリ一覧の取得に失敗しました","adminTopPage","管理者ページに");
				return;
			}
			request.setAttribute("updateItem", updateItem);
			request.setAttribute("itemCategoryListAll", itemCategoryListAll);
			String view = "/WEB-INF/views/admin/editItem2.jsp";
			request.getRequestDispatcher(view).forward(request, response);
			return;
		}
	}
}
