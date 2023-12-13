package controller.customer;

import java.io.IOException;
import java.util.ArrayList;

import bean.ItemBean;
import bean.ItemCategoryBean;
import bean.OptionCategoryBean;
import classes.Item;
import classes.ItemCategory;
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
		
		// テストコード
		for (int i = 0; i < checkedOption.length; i++) {
		System.out.println(i + ":" + checkedOption[i]);
		}
		
		String categoryName = request.getParameter("categoryName");
		
		// オプション選択した一覧
		ArrayList<ItemBean> OCList = Item.getItemListByOption(checkedOption, categoryName);
		
		// オプション選択の表示
		ItemBean itemBean = new ItemBean();
		itemBean.setItemCategoryName(categoryName);
		ArrayList<ArrayList<OptionCategoryBean>> ONValueListALL = new ArrayList<ArrayList<OptionCategoryBean>>();
		ArrayList<ItemCategoryBean> CNList = ItemCategory.getItemOptionCategoryNameListByCategory(itemBean);
		for (int i = 0; i < CNList.size(); i++) {
			ItemCategoryBean OCName = CNList.get(i);
			ArrayList<OptionCategoryBean> ONValueList = OptionCategory.getOptionCategoryListByCategory(OCName);
			ONValueListALL.add(ONValueList);
		}
		
		request.setAttribute("categoryName", categoryName);
		request.setAttribute("ONValueListALL", ONValueListALL);
		request.setAttribute("itemList", OCList);
		request.setAttribute("message", "検索キーワード : " + checkedOption(checkedOption));
		
		String view = "/WEB-INF/views/customer/categoryIndex.jsp";
        request.getRequestDispatcher(view).forward(request, response);
	}
	
	public static String checkedOption(String[] checkedOption) {
		String str = null;
		for (int index = 0; index < checkedOption.length; index++) {
			if (index == 0) {
				str = "'" + checkedOption[0] + "'";
			} else {
				str += "," + "'" + checkedOption[index] + "'";
			}
			index++;
		}
		return str;
	}
}
