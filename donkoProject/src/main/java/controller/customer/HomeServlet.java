package controller.customer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import bean.ItemBean;
import classes.Item;
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
		ArrayList<ItemBean> itemList = Item.getItemList();
		ArrayList<ItemBean> resultList = new ArrayList<>();
		Random random = new Random();

		for (int i = 0; i < 9 ; i++) {
		    if (!itemList.isEmpty()) {
		        int index = random.nextInt(itemList.size()); // ランダムなインデックスを生成
		        ItemBean randomItem = itemList.get(index);
		        resultList.add(randomItem);
		    }
		}
		request.setAttribute("resultList", resultList);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
