package controller.admin;

import java.io.IOException;

import classes.Item;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/swichItemDelFlg")
public class SwichItemDelFlg extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public SwichItemDelFlg() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//選択済みの商品ステータスを全て取得、配列に格納
//		String[] itemStatus = request.getParameterValues("itemStatus");

		//テストコード
		String[] itemStatus = {"1", "2", "3"};

		//選択済みの商品のステータスを切り替えるサーブレット
		if(itemStatus != null && itemStatus.length > 0) {
			Item.deleteItem(itemStatus);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}
}
