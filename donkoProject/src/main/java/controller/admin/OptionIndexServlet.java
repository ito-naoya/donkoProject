package controller.admin;

import java.io.IOException;
import java.util.ArrayList;

import bean.ItemCategoryBean;
import bean.OptionCategoryBean;
import classes.ErrorHandling;
import classes.OptionCategory;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/optionIndex")
public class OptionIndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public OptionIndexServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// ヘッダーに表示する値を取得
		String disp = "/adminheader";
	    RequestDispatcher dispatch = request.getRequestDispatcher(disp);
	    dispatch.include(request, response);
		
		HttpSession as = request.getSession();
		String adminSession = (String)as.getAttribute("admin");
		
		// アドミンのセッションがnullの場合は管理者ログイン画面に遷移
		if (adminSession == null) {
			response.sendRedirect("adminSignin");
			return;
		}

		//選択されたオプションに対するオプション詳細を表示
		ItemCategoryBean option = new ItemCategoryBean();
		//オプションを受け取る
		option.setOptionCategoryName(request.getParameter("optionCategoryName"));
		//オプションに紐づくオプション詳細を取得
		ArrayList<OptionCategoryBean> optionValueList = OptionCategory.getOptionCategoryListByCategory(option);
		if(optionValueList == null) {
			ErrorHandling.transitionToErrorPage(request,response,"オプション詳細の取得に失敗しました","adminTopPage","管理者ページに");
			return;
		}

		//全てのオプション一覧を取得
		ArrayList<OptionCategoryBean> optionList = OptionCategory.getOptionCategoryList();
		if(optionList == null) {
			ErrorHandling.transitionToErrorPage(request,response,"オプションの取得に失敗しました","adminTopPage","管理者ページに");
			return;
		}

		String message = request.getParameter("message");
		request.setAttribute("message", message);
		request.setAttribute("optionValueList", optionValueList);
		request.setAttribute("optionList", optionList);
		request.setAttribute("optionCategoryName", option.getOptionCategoryName());

		String view = "/WEB-INF/views/admin/optionIndex.jsp";
		request.getRequestDispatcher(view).forward(request, response);

	}
}
