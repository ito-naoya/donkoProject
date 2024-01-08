package controller.admin;

import java.io.IOException;
import java.util.ArrayList;

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
import jakarta.servlet.http.HttpSession;
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
		HttpSession session = request.getSession();
		String admin = (String) session.getAttribute("admin");
		if (admin == null) {
			response.sendRedirect("adminSignin");
			return;
		}

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
		String redirectPath = "/WEB-INF/views/admin/editItemInfo2.jsp";

		//入力した取得情報について、null値及び文字数制限の超過が無いかどうか確認し、itemBeanに登録
		updateItem = Item.checkRegistItemDetail(updateItem, price, stock);
		if(updateItem == null) {
			//取得情報の不備があれば、再度入力画面に戻る
			response.sendRedirect("editItemInfo1");
			return;
		}

		//入力文字チェック。入力内容に不備があった場合、再度カテゴリーリストを作成して、元の画面にリダイレクト
		if(BeanValidation.validate(request, "item", updateItem, GroupA.class)) {
  			ItemManagementHelper.errorRedirect(request,response,updateItem,redirectPath);
  		}

		//セレクトボックスの個数を取得
		int selectBoxCount = Integer.parseInt(request.getParameter("selectBoxCount"));

		//順番にitembanに格納<色：緑 など>
		updateItem.setItemFirstOptionName(request.getParameter("optionCategoryName_1"));
		String itemFirstOptionIncrementId = request.getParameter("optionValue_1");
		String[] secondIds = new String[1]; // 配列の初期化
		//取得したオプション情報について、null値チェック。IremBeansに値を追加。セレクトボックスが2つなら、2個目の値も格納<衣類サイズ：S　など>
		//商品の持つ他の情報について、null値及び文字数制限の超過が無いかどうか確認し、itemBeanに登録
	    if (selectBoxCount == 1) {
	    	updateItem = Option.checkRegistItemOptionDetail(itemId, updateItem, itemFirstOptionIncrementId, null);
	    } else {
	    	updateItem.setItemSecondOptionName(request.getParameter("optionCategoryName_2"));
	    	secondIds[0] = request.getParameter("optionValue_2");
	        updateItem = Option.checkRegistItemOptionDetail(itemId, updateItem, itemFirstOptionIncrementId, secondIds);
	    }
	    if (updateItem == null) {
	    	//不正な値を入力していた場合はリダイレクト
	    	ItemManagementHelper.errorRedirect(request,response,updateItem,redirectPath);
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
        	if(BeanValidation.validate(request, "item", updateItem, GroupB.class)) {
        		ItemManagementHelper.errorRedirect(request,response,updateItem,redirectPath);
        		return;
        	}
        } else {
        	if(BeanValidation.validate(request, "item", updateItem, GroupC.class)) {
        		ItemManagementHelper.errorRedirect(request,response,updateItem,redirectPath);
        		return;
        	}
        }

	    String message = null;
	    //重複商品を確認するために、商品詳細を取得
  		ItemBean editItem = Item.getItemAllDetail(updateItem);
  		if(editItem == null) {
  			//取得情報の不備があれば、エラー画面に遷移
  			ErrorHandling.transitionToErrorPage(request,response,"商品詳細の取得に失敗しました","adminTopPage","管理者ページに");
  			return;
  		}
  		int oldId = editItem.getItemSecondOptionIncrementId();
		//変更後の商品名とオプションについて、既存のアイテムと重複がないか確認する
	    if(!fileName.equals(olditemFile) || oldId != updateItem.getItemSecondOptionIncrementId()){
		    ArrayList<Integer> existId = Item.checkItemAlreadyExist(updateItem,secondIds);
		    if(existId == null) {
		    	ErrorHandling.transitionToErrorPage(request,response,"商品情報の取得に失敗しました","adminTopPage","管理者ページに");
		    	return;
		    } else if (!existId.isEmpty()) { //商品が重複していた場合
		    	message = "商品が重複しています。重複商品ID：" + existId.get(0);
		    	ItemManagementHelper.deleteItemRedirect(response, updateItem, message);
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
    	ItemManagementHelper.deleteItemRedirect(response, updateItem, message);
	}
}
