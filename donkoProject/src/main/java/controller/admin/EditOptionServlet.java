package controller.admin;

import java.io.IOException;

import bean.OptionCategoryBean;
import classes.BeanValidation;
import classes.ErrorHandling;
import classes.ItemManagementHelper;
import classes.Option;
import interfaces.group.GroupA;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/editOption")
public class EditOptionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public EditOptionServlet() {
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

		OptionCategoryBean option = new OptionCategoryBean();
		//オプションを受け取る
		option.setOptionCategoryName(request.getParameter("optionCategoryName"));

		//問題なければ削除
		Integer deleteOptionResult = Option.deleteItemOptionName(option);
		if (deleteOptionResult == null) {
		    ErrorHandling.transitionToErrorPage(request, response, "オプションの更新に失敗しました", "adminTopPage", "管理者ページに");
		} else if (deleteOptionResult == 0) {
		    ItemManagementHelper.optionDetailMessage(response, option, "商品登録中ののオプションは削除できません");
		} else if (deleteOptionResult > 0) {
			ItemManagementHelper.optionDetailMessage(response, null, "オプションを削除しました");;
		}
		return;

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//新しいオプション名を取得
		OptionCategoryBean option = new OptionCategoryBean();
		option.setOptionCategoryName(request.getParameter("newOptionName"));

		//入力文字チェック。入力内容に不備があった場合、元の画面にリダイレクト
		if(BeanValidation.validate(request, "option", option, GroupA.class)) {
			ItemManagementHelper.optionDetailMessage(response, option, "1文字以上20文字以下で入力してください");
			return;
		}
		//SQL実行
		Integer registOptionResult = Option.registerItemOptionName(option);

		if (registOptionResult == null) {
		    ErrorHandling.transitionToErrorPage(request, response, "オプションの更新に失敗しました", "adminTopPage", "管理者ページに");
		} else if (registOptionResult == 0) {
		    ItemManagementHelper.optionDetailMessage(response, option, "同じ名前のオプションは登録できません");
		} else if (registOptionResult > 0) {
		    ItemManagementHelper.optionDetailMessage(response, option, "オプションを追加しました");
		}

		return;
	}
}
