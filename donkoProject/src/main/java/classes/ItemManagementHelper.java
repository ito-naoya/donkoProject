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

	// 全角数字を半角数字に変換するメソッド
		public static String priceCheck(String price) {
			StringBuilder sb = new StringBuilder();
		    for (char c : price.toCharArray()) {
		        if (c >= '０' && c <= '９') {
		            sb.append((char) (c - '０' + '0'));
		        } else {
		            sb.append(c);
		        }
		    }
		    return sb.toString();
		}

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

	    //商品編集、登録画面から商品一覧に遷移
	    public static void deleteItemRedirect(HttpServletResponse response, ItemBean updateItem, String message)
				throws UnsupportedEncodingException, IOException {
			String encodedItemCategoryName = URLEncoder.encode(updateItem.getItemCategoryName(), "UTF-8");
			String encodedMessage = URLEncoder.encode(message, "UTF-8");
			String redirectURL = "deleteItemIndex?itemCategoryName=" + encodedItemCategoryName + "&itemDelFlg=" + "0" + "&message=" + encodedMessage + "&order=desc";
			response.sendRedirect(redirectURL);
			return;
		}

	    //オプション情報を保持した状態でオプション一覧ページに遷移
		public static void optionDetailMessage(HttpServletResponse response, OptionCategoryBean option, String rowMessage) throws UnsupportedEncodingException, IOException {
			String message = URLEncoder.encode(rowMessage, "UTF-8");
			String redirectURL;
			if(option != null) {
				String optionCategoryName = URLEncoder.encode(option.getOptionCategoryName(), "UTF-8");
				redirectURL = "optionIndex?message=" + message + "&optionCategoryName=" + optionCategoryName;
			}else {
				redirectURL = "optionIndex?message=" + message;
			}
			response.sendRedirect(redirectURL);
		}

		//カテゴリ情報を保持した状態でカテゴリ一覧ページに遷移
		public static void categoryDetailMessage(HttpServletResponse response, ItemCategoryBean category, String rowMessage) throws UnsupportedEncodingException, IOException {
			String message = URLEncoder.encode(rowMessage, "UTF-8");
			String redirectURL;
			if(category != null) {
				String itemCategoryName = URLEncoder.encode(category.getItemCategoryName(), "UTF-8");
				redirectURL = "categoryIndex?message=" + message + "&categoryCategoryName=" + itemCategoryName;
			}else {
				redirectURL = "categoryIndex?message=" + message;
			}
			response.sendRedirect(redirectURL);
		}

		//カテゴリ情報のリダイレクト
		public static void categoryDetailRedirect(HttpServletRequest request, HttpServletResponse response, ItemBean category)throws ServletException, IOException {
			//カテゴリに紐づくオプションを取得
			ArrayList<ItemCategoryBean> optionList = ItemCategory.getItemOptionCategoryNameListByCategory(category);
			if(optionList == null) {
				ErrorHandling.transitionToErrorPage(request,response,"オプションの取得に失敗しました","adminTopPage","管理者ページに");
				return;
			}
			//全てのカテゴリ一覧を取得
			ArrayList<ItemCategoryBean> categoryList = ItemCategory.getItemCategoryList();
			if(categoryList == null) {
				ErrorHandling.transitionToErrorPage(request,response,"カテゴリの取得に失敗しました","adminTopPage","管理者ページに");
				return;
			}
			//全てのオプション一覧を取得
			ArrayList<OptionCategoryBean> allOptionList = OptionCategory.getOptionCategoryList();
			if(allOptionList == null) {
				ErrorHandling.transitionToErrorPage(request,response,"オプションの取得に失敗しました","adminTopPage","管理者ページに");
				return;
			}

			request.setAttribute("optionList", optionList);
			request.setAttribute("allOptionList", allOptionList);
			request.setAttribute("categoryList", categoryList);
			request.setAttribute("itemCategoryName1", category.getItemCategoryName());
		}
}
