package controller.admin;

import java.io.IOException;

import bean.OptionCategoryBean;
import classes.BeanValidation;
import classes.ErrorHandling;
import classes.ItemManagementHelper;
import classes.Option;
import interfaces.group.GroupB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/editOptionDeteal")
public class EditOptionDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public EditOptionDetailServlet() {
		super();
	}
	//オプション詳細に関わるサーブレット
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		OptionCategoryBean option = new OptionCategoryBean();
		//オプションを受け取る
		option.setOptionCategoryName(request.getParameter("optionCategoryName"));
		option.setOptionCategoryValue(request.getParameter("optionCategoryValue"));

		//問題なければ削除
		Integer deleteOptionResult = Option.deleteItemOptionValue(option);
		if (deleteOptionResult == null) {
		    ErrorHandling.transitionToErrorPage(request, response, "オプションの更新に失敗しました", "adminTopPage", "管理者ページに");
		} else if (deleteOptionResult == 0) {
		    ItemManagementHelper.optionDetailMessage(response, option, "商品登録中ののオプション詳細は削除できません");
		} else if (deleteOptionResult > 0) {
			ItemManagementHelper.optionDetailMessage(response, option, "オプション詳細を削除しました");;
		}
		return;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//新しいオプション名を取得
		OptionCategoryBean option = new OptionCategoryBean();
		option.setOptionCategoryName(request.getParameter("currentOptionCategoryName"));
		option.setOptionCategoryValue(request.getParameter("newOptionValue"));

		//入力文字チェック。入力内容に不備があった場合、元の画面にリダイレクト
		if(BeanValidation.validate(request, "option", option, GroupB.class)) {
			ItemManagementHelper.optionMessage(response,"1文字以上20文字以下で入力してください");
			return;
		}
		//SQL実行
		Integer registOptionResult = Option.registerItemOptionValue(option);

		if (registOptionResult == null) {
		    ErrorHandling.transitionToErrorPage(request, response, "オプションの更新に失敗しました", "adminTopPage", "管理者ページに");
		} else if (registOptionResult == 0) {
		    ItemManagementHelper.optionDetailMessage(response, option, "同じ名前のオプション詳細は登録できません");
		} else if (registOptionResult > 0) {
			ItemManagementHelper.optionDetailMessage(response, option, "オプション詳細を登録しました");;
		}
		return;
	}



}
