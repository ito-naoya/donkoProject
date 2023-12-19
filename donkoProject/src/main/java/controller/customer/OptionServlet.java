package controller.customer;

import java.io.IOException;
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
		
		
		
		ItemBean itemBean = new ItemBean();
		itemBean.setItemCategoryName(categoryName);
		
		// オプション選択の項目を取得
		ArrayList<ArrayList<OptionCategoryBean>> optionCategoryValueListAll = OptionCategory.getOptionCategoryListAllByCategory(itemBean);
		
		if (checkedOptionList.length > 0) {
			// オプション選択した一覧を取得
			ArrayList<ItemBean> optionCategoryList = Item.getItemListByOption(checkedOptionList, categoryName);
			request.setAttribute("itemList", optionCategoryList);
			request.setAttribute("message", "検索キーワード : " + searchKeyword(checkedOptionList, categoryName));
		} else {
			// カテゴリー一覧を取得
			ArrayList<ItemBean> itemList = Item.getItemListByCategory(itemBean);
			request.setAttribute("itemList", itemList);
		}
		
		request.setAttribute("categoryName", categoryName);
		request.setAttribute("optionCategoryValueListAll", optionCategoryValueListAll);
		
		String view = "/WEB-INF/views/customer/categoryIndex.jsp";
        request.getRequestDispatcher(view).forward(request, response);
	}
	
	// 検索したキーワードを文字連結して表示
	private static String searchKeyword(String[] checkedOptionList, String categoryName) {
		String searchKeyword = "[ " + categoryName + " ], [ " + checkedOptionList[0] + " ]";
	    for (int i = 1; i < checkedOptionList.length; i++) {
	    	searchKeyword += ", " + "[ " + checkedOptionList[i] + " ]";
	    }
	    return searchKeyword;
	}
}
