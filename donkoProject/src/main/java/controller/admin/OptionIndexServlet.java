package controller.admin;

import java.io.IOException;
import java.util.ArrayList;

import bean.OptionCategoryBean;
import classes.ErrorHandling;
import classes.OptionCategory;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/optionIndex")
public class OptionIndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public OptionIndexServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//全てのオプション一覧を取得
		ArrayList<OptionCategoryBean> optionList = OptionCategory.getOptionCategoryList();
		if(optionList == null) {
			ErrorHandling.transitionToErrorPage(request,response,"オプションの取得に失敗しました","adminTopPage","管理者ページに");
			return;
		}
		String message = request.getParameter("message");
		request.setAttribute("message", message);
		request.setAttribute("optionList", optionList);

		String view = "/WEB-INF/views/admin/optionIndex.jsp";
		request.getRequestDispatcher(view).forward(request, response);

	}
}
