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
//以下テストコード
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String itemCategoryName = "衣類";
		int flg = 1;
		ArrayList<ItemBean> itemList = Item.getItemAndOptionListByDelFlg(flg,itemCategoryName);
		request.setAttribute("item", itemList);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
