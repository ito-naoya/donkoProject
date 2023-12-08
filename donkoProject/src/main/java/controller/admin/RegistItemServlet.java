package controller.admin;

import java.io.IOException;
import java.util.ArrayList;

import bean.ItemCategoryBean;
import classes.ItemCategory;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/registItem")
public class RegistItemServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public RegistItemServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		ArrayList<ItemCategoryBean> items = ItemCategory.getItemCategoryList();
		for (ItemCategoryBean item : items) {
            System.out.println(item.getItemCategoryName());
        }
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		
		doGet(request, response);
	}

}
