package controller.admin;

import java.io.IOException;

import bean.ItemBean;
import bean.ItemCategoryBean;
import classes.BeanValidation;
import classes.ErrorHandling;
import classes.ItemCategory;
import classes.ItemManagementHelper;
import interfaces.group.GroupA;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/editCategory")
public class EditCategoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public EditCategoryServlet() {
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

		ItemCategoryBean category = new ItemCategoryBean();
		//カテゴリを受け取る
		category.setItemCategoryName(request.getParameter("itemCategoryName"));

		//問題なければ削除
		Integer deleteCategoryResult = ItemCategory.deleteItemCategory(category);
		if (deleteCategoryResult == null) {
		    ErrorHandling.transitionToErrorPage(request, response, "オプションの更新に失敗しました", "adminTopPage", "管理者ページに");
		} else if (deleteCategoryResult == 0) {
		    ItemManagementHelper.categoryDetailMessage(response, category, "商品登録中のカテゴリは削除できません");
		} else if (deleteCategoryResult > 0) {
			ItemManagementHelper.categoryDetailMessage(response, null, "カテゴリを削除しました");;
		}
		return;

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//選択されたカテゴリに対するカテゴリ詳細を表示
		ItemBean category = new ItemBean();
		//カテゴリを受け取る
		category.setItemCategoryName(request.getParameter("itemCategoryName"));

		//新しいカテゴリ名を取得
		ItemCategoryBean newCategory = new ItemCategoryBean();
		newCategory.setItemCategoryName(request.getParameter("newCategoryName"));
		newCategory.setOptionCategoryName(request.getParameter("optionSelect1"));
		//入力文字チェック。入力内容に不備があった場合、元の画面にリダイレクト
		if(BeanValidation.validate(request, "modal", newCategory, GroupA.class)) {
			request.setAttribute("modal", newCategory);
			request.setAttribute("showModal","true");
		    ItemManagementHelper.categoryDetailRedirect(request, response, category);
		    redirectModal(request,response);
		    return;
		}
		// セレクトボックスの値を配列に格納
	    String[] selectedOptions = new String[2];
	    selectedOptions[0] = request.getParameter("optionSelect1");
	    selectedOptions[1] = request.getParameter("optionSelect2");

		//SQL実行
		Integer registCategoryResult = ItemCategory.registerItemCategoryName(newCategory, selectedOptions);

		if (registCategoryResult == null) {
		    ErrorHandling.transitionToErrorPage(request, response, "カテゴリの更新に失敗しました", "adminTopPage", "管理者ページに");
		} else if (registCategoryResult == 0) {
			request.setAttribute("modal", newCategory);
			request.setAttribute("showModal","true");
			ItemManagementHelper.categoryDetailRedirect(request, response, category);
			redirectModal(request,response);
		    return;
		} else if (registCategoryResult == 1) {
			ItemManagementHelper.categoryDetailMessage(response, newCategory, "新しいカテゴリを登録しました");
		}
		return;
	}

	private void redirectModal(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String view = "/WEB-INF/views/admin/categoryIndex.jsp";
		request.getRequestDispatcher(view).forward(request, response);
		return;
	}
}
