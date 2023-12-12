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


@WebServlet("/category")
public class CategoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public CategoryServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String categoryName = request.getParameter("categoryName");
		ItemBean itemBean = new ItemBean();
		itemBean.setItemCategoryName(categoryName);
		ArrayList<ItemBean> itemList =Item.getItemListByCategory(itemBean);
		request.setAttribute("itemList", itemList);
		
		// 取得した値を格納するArrayList
		ArrayList<ArrayList<OptionCategoryBean>> ONValueListALL = new ArrayList<ArrayList<OptionCategoryBean>>();
		
		// 引数 : 衣類 => 配列の中身 : 衣類サイズ, 色
		ArrayList<ItemCategoryBean> CNList = ItemCategory.getItemOptionCategoryNameListByCategory(itemBean);
		for (int i = 0; i < CNList.size(); i++) {
			ItemCategoryBean OCName = CNList.get(i);
			// 引数 : 衣類サイズ => 配列の中身 : S, M, L
			ArrayList<OptionCategoryBean> ONValueList = OptionCategory.getOptionCategoryListByCategory(OCName);
			ONValueListALL.add(ONValueList);
		}
		request.setAttribute("ONValueListALL", ONValueListALL);
		
		String view = "/WEB-INF/views/customer/categoryIndex.jsp";
        request.getRequestDispatcher(view).forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
}
