package classes;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ErrorHandling {
	
	public static void transitionToErrorPage(HttpServletRequest request, HttpServletResponse response, String message, String url, String returnPage)
			throws ServletException, IOException {
		// エラーメッセージをセット
		request.setAttribute("errorMessage", message);
		// 戻り先のURL
		request.setAttribute("url", url);
		// 戻るボタンの表示文言
		request.setAttribute("returnPage", returnPage);
		// エラー画面に遷移
		String view = "/WEB-INF/views/component/message.jsp";
		request.getRequestDispatcher(view).forward(request, response);
	}

}
