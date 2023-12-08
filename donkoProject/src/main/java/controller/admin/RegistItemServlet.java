package controller.admin;

import java.io.IOException;
import java.util.ArrayList;

import bean.ItemBean;
import bean.ItemCategoryBean;
import bean.OptionCategoryBean;
import classes.ItemCategory;
import classes.OptionCategory;
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
		ItemBean itemBean = new ItemBean();
		itemBean.setItemCategoryName("衣類");
		
		ArrayList<ItemCategoryBean> itemCategories = ItemCategory.getOptionListByCategory(itemBean);
		
		for (ItemCategoryBean itemCategory : itemCategories) {
            ArrayList<OptionCategoryBean> options = OptionCategory.getOptionListByCategory(itemCategory);
            for(OptionCategoryBean option : options) {
            	System.out.println(itemCategory.getOptionCategoryName() + " " + option.getOptionCategoryValue());
            	System.out.println(itemCategory.getOptionCategoryName() + " " + option.getOptionCategoryId());
            }
        }
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
