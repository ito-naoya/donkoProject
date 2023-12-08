package controller.customer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
		ArrayList<ItemBean> shuffled = new ArrayList<ItemBean>(itemList);
		Collections.shuffle(shuffled);
		List<ItemBean> result = shuffled.subList(0, 8);
		ArrayList<ItemBean> IList = (ArrayList<ItemBean>)result;
		request.setAttribute("IList", IList);
		
		String view = "/WEB-INF/views/customer/home.jsp";
        request.getRequestDispatcher(view).forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
