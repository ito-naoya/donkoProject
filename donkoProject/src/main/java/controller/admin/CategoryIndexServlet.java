package controller.admin;

import java.io.IOException;

import bean.ItemBean;
import classes.ItemManagementHelper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/categoryIndex")
public class CategoryIndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public CategoryIndexServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String admin = (String) session.getAttribute("admin");
		if (admin == null) {
			response.sendRedirect("adminSignin");
			return;
		}

		//選択されたカテゴリに対するカテゴリ詳細を表示
		ItemBean category = new ItemBean();
		//カテゴリを受け取る
		category.setItemCategoryName(request.getParameter("itemCategoryName"));
		ItemManagementHelper.categoryDetailRedirect(request, response, category);

		String message = request.getParameter("message");
		request.setAttribute("message", message);
		String view = "/WEB-INF/views/admin/categoryIndex.jsp";
		request.getRequestDispatcher(view).forward(request, response);
	}
}
