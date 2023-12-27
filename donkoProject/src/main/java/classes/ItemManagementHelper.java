package classes;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;

import bean.ItemBean;
import bean.ItemCategoryBean;
import bean.OptionCategoryBean;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ItemManagementHelper {

	    public static void getCategoryList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        // カテゴリリスト取得の共通処理
	    	ArrayList<ItemCategoryBean> categoryList = ItemCategory.getItemCategoryList();
			if(categoryList == null) {
				//取得情報の不備があれば、エラー画面に遷移
				ErrorHandling.transitionToErrorPage(request,response,"カテゴリ一覧の取得に失敗しました","adminTopPage","管理者ページに");
				return;
			}
			request.setAttribute("categoryList", categoryList);
	    }

	    public static void errorRedirect(HttpServletRequest request, HttpServletResponse response, ItemBean newItem, String viewPath) throws ServletException, IOException {
	            // オプション一覧を取得
	            ArrayList<ArrayList<OptionCategoryBean>> itemCategoryListAll = OptionCategory.getOptionCategoryListAllByCategory(newItem);
	            if (itemCategoryListAll == null) {
	                // 取得情報の不備があれば、エラー画面に遷移
	                ErrorHandling.transitionToErrorPage(request, response, "カテゴリ一覧の取得に失敗しました", "adminTopPage","管理者ページに");
	                return;
	            }
	            request.setAttribute("newItem", newItem);
	            request.setAttribute("itemCategoryListAll", itemCategoryListAll);
	            request.getRequestDispatcher(viewPath).forward(request, response);
	            return;
	    }

	    public static void deleteItemRedirect(HttpServletResponse response, ItemBean updateItem, String message)
				throws UnsupportedEncodingException, IOException {
			String encodedItemCategoryName = URLEncoder.encode(updateItem.getItemCategoryName(), "UTF-8");
			String encodedMessage = URLEncoder.encode(message, "UTF-8");
			String redirectURL = "deleteItemIndex?itemCategoryName=" + encodedItemCategoryName + "&itemDelFlg=" + "0" + "&message=" + encodedMessage;
			response.sendRedirect(redirectURL);
			return;
		}


}
