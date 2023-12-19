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


@WebServlet("/category")
public class CategoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public CategoryServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String categoryName = request.getParameter("categoryName");
		// カテゴリ名がnullの場合はホーム画面に遷移
		if (categoryName == null) {
			response.sendRedirect("home");
			return;
		}
		
		// Beanにカテゴリ名をセット
		ItemBean itemBean = new ItemBean();
		itemBean.setItemCategoryName(categoryName);
		
		// オプション選択の取得
		ArrayList<ArrayList<OptionCategoryBean>> optionCategoryValueListAll = OptionCategory.getOptionCategoryListAllByCategory(itemBean);
		// カテゴリー一覧の取得
		ArrayList<ItemBean> itemList = Item.getItemListByCategory(itemBean);
		
		// エラー画面に遷移
		if (optionCategoryValueListAll == null || itemList == null) {
			// 失敗したらmessage.jspに遷移
			if(optionCategoryValueListAll == null) {
				request.setAttribute("errorMessage", "オプション選択のデータが取得できませんでした");
			} else {
				request.setAttribute("errorMessage", "カテゴリ一覧のデータが取得できませんでした");
			}
			request.setAttribute("url", "home");
			String view = "/WEB-INF/views/customer/message.jsp";
	        request.getRequestDispatcher(view).forward(request, response);
		}
		
		request.setAttribute("categoryName", categoryName);
		request.setAttribute("optionCategoryValueListAll", optionCategoryValueListAll);
		request.setAttribute("itemList", itemList);
		
		String view = "/WEB-INF/views/customer/categoryIndex.jsp";
        request.getRequestDispatcher(view).forward(request, response);
	}
}
