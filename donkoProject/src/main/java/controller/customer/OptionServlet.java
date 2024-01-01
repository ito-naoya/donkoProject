package controller.customer;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;

import bean.ItemBean;
import bean.OptionCategoryBean;
import classes.Item;
import classes.OptionCategory;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/option")
public class OptionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
    public OptionServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String[] checkedOptionList = request.getParameterValues("option");
		String categoryName = request.getParameter("categoryName");
		// URLの形式を日本語対応させるための処理
		String encodedCategoryName = URLEncoder.encode(categoryName, "UTF-8");
		
		// 取得した値がnullの場合はリダイレクトする
		if (checkedOptionList == null && !categoryName.isEmpty()) {
			response.sendRedirect("category?categoryName=" + encodedCategoryName);
			return;
		}
		
		// Beanに値をセットする
		ItemBean itemBean = new ItemBean();
		itemBean.setItemCategoryName(categoryName);
		
		if (checkedOptionList.length > 0) {
			request.setAttribute("message", "検索キーワード : 　" + searchKeyword(checkedOptionList, categoryName));
		}
		
		// オプション選択の項目を取得
		ArrayList<ArrayList<OptionCategoryBean>> optionCategoryValueListAll = OptionCategory.getOptionCategoryListAllByCategory(itemBean);
		// オプション選択した一覧を取得
		ArrayList<ItemBean> optionCategoryList = Item.getItemListByOption(checkedOptionList, categoryName);
		
		// 値がnullの場合はエラー画面に遷移する
		if (optionCategoryValueListAll == null || optionCategoryList == null) {
			if (optionCategoryValueListAll == null) {
				request.setAttribute("errorMessage", "オプション選択のデータの取得に失敗しました");
			} else {
				request.setAttribute("errorMessage", "オプション選択した一覧のデータの取得に失敗しました");
			}
			request.setAttribute("url", "category?categoryName=" + categoryName);
			String view = "/WEB-INF/views/component/message.jsp";
	        request.getRequestDispatcher(view).forward(request, response);
		}
		
		request.setAttribute("optionCategoryValueListAll", optionCategoryValueListAll);
		request.setAttribute("itemList", optionCategoryList);
		request.setAttribute("categoryName", categoryName);
		
		String view = "/WEB-INF/views/customer/categoryIndex.jsp";
        request.getRequestDispatcher(view).forward(request, response);
	}
	
	// 検索したキーワードを文字連結して表示
	private static String searchKeyword(String[] checkedOptionList, String categoryName) {
		String searchKeyword = categoryName + " , " + checkedOptionList[0];
	    for (int i = 1; i < checkedOptionList.length; i++) {
	    	searchKeyword += " , " + checkedOptionList[i];
	    }
	    return searchKeyword;
	}
}
