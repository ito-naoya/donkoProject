package controller.admin;

import java.io.IOException;
import java.util.ArrayList;

import bean.ItemBean;
import bean.ItemCategoryBean;
import classes.Item;
import classes.ItemCategory;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/editItemInfo1")
public class EditItemInfo1Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public EditItemInfo1Servlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//商品idを取得して、商品詳細を取得
		Integer itemId = Integer.parseInt(request.getParameter("itemId"));

		ItemBean itemBean = new ItemBean();
		itemBean.setItemId(itemId);
		
		
		//カテゴリー一覧を取得
		ArrayList<ItemCategoryBean> categoryList = ItemCategory.getItemCategoryList();
		request.setAttribute("categoryList", categoryList);
		
		//商品登録画面1に転送
		request.setAttribute("errorMessage", "");
		
		//商品詳細を取得
		ItemBean item = Item.getItemDetail(itemBean);
		if(item == null) {
			response.sendRedirect("deleteItemIndex");
		} else {
			request.setAttribute("item", item);
			String view = "/WEB-INF/views/admin/editItemInfo1.jsp";
			RequestDispatcher dispatcher = request.getRequestDispatcher(view);
			dispatcher.forward(request, response);
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
