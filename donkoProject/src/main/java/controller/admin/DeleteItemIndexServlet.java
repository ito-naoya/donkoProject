package controller.admin;

import java.io.IOException;
import java.util.ArrayList;

import bean.ItemBean;
import classes.Item;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/deleteItemIndex")
public class DeleteItemIndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public DeleteItemIndexServlet() {
		super();
	}
//カテゴリーなし
//カテゴリー

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String itemCategoryName = "";
		ArrayList<ItemBean> itemList = Item.getItemAndOptionListAll(itemCategoryName);
		request.setAttribute("item", itemList);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
