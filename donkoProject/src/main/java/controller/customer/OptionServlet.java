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
		String[] checkedOption = request.getParameterValues("option");
		String categoryName = request.getParameter("categoryName");
		request.setAttribute("categoryName", categoryName);
		
		// オプション選択の取得
		ItemBean itemBean = new ItemBean();
		itemBean.setItemCategoryName(categoryName);
		ArrayList<ArrayList<OptionCategoryBean>> ONValueListALL = OptionCategory.getOptionCategoryListAllByCategory(itemBean);
		request.setAttribute("ONValueListALL", ONValueListALL);
		
		if (checkedOption != null && checkedOption.length > 0) {
			// オプション選択した一覧
			ArrayList<ItemBean> OCList = Item.getItemListByOption(checkedOption, categoryName);
			request.setAttribute("itemList", OCList);
			request.setAttribute("message", "検索キーワード : " + checkedOption(checkedOption));
		} else {
			// カテゴリー一覧
			ArrayList<ItemBean> itemList =Item.getItemListByCategory(itemBean);
			request.setAttribute("itemList", itemList);
		}
		
		String view = "/WEB-INF/views/customer/categoryIndex.jsp";
        request.getRequestDispatcher(view).forward(request, response);
	}
	
	private static String checkedOption(String categoryName, String[] checkedOption) {
		String str = "[ " + categoryName + " ], [ " + checkedOption[0] + " ]";
	    for (int index = 1; index < checkedOption.length; index++) {
	        str += ", " + "[ " + checkedOption[index] + " ]";
	    }
	    return str;
	}
}
