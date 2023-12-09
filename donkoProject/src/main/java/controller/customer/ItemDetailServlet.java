package controller.customer;

import java.io.IOException;
import java.util.ArrayList;

import bean.ItemBean;
import classes.Item;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/itemDetail")
public class ItemDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
    public ItemDetailServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		ItemBean itemBean = new ItemBean();
//		itemBean.setItemId( Integer.parseInt(request.getParameter("itemId")));
		
		String test = request.getParameter("itemId");
		
		if(test != null) {	
			itemBean.setItemId(Integer.parseInt(test));
		}else {
			itemBean.setItemId(10);
		}

		ItemBean item = Item.getItemDetail(itemBean);
		ArrayList<ItemBean> itemImageList = Item.getItemImageList(itemBean);

		
		request.setAttribute("itemImageList", itemImageList);
		request.setAttribute("item", item);
		
		String view = "/WEB-INF/views/customer/itemDetail.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(view);
		dispatcher.forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
