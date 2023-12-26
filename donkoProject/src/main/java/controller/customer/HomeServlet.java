package controller.customer;

import java.io.IOException;
import java.util.ArrayList;

import bean.ItemBean;
import classes.ErrorHandling;
import classes.Item;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet("/home")
public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public HomeServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// ヘッダーの値を取得
		String disp = "/header";
	    RequestDispatcher dispatch = request.getRequestDispatcher(disp);
	    dispatch.include(request, response);
		
		// 商品の画像を取得
		ArrayList<ItemBean> itemList = Item.getItemList();
		
		if (itemList == null) {
	      //エラーページに遷移
			ErrorHandling.transitionToErrorPage(request,response,"画像の取得に失敗しました","home","ホームに");
			return;
		}
		
		request.setAttribute("itemList", itemList);
		
		String view = "/WEB-INF/views/customer/home.jsp";
        request.getRequestDispatcher(view).forward(request, response);
	}
}

