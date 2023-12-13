package controller.customer;

import java.io.IOException;
import java.util.ArrayList;

import bean.CartBean;
import bean.ItemBean;
import classes.Cart;
import classes.Item;
import classes.user.CustomerUser;
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
		itemBean.setItemId( Integer.parseInt(request.getParameter("itemId")));

		ItemBean item = Item.getItemDetail(itemBean);
		ArrayList<ItemBean> itemImageList = Item.getItemImageList(itemBean);
		ArrayList<ItemBean> itemOptionList = Item.getItemOptionList(itemBean);
		ItemBean itemOptionDetail =  Item.getItemDetailOption(itemBean);
		
		request.setAttribute("item", item);
		request.setAttribute("itemImageList", itemImageList);
		request.setAttribute("itemOptionList", itemOptionList);
		request.setAttribute("itemOptionDetail", itemOptionDetail);
		
		String view = "/WEB-INF/views/customer/itemDetail.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(view);
		dispatcher.forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		HttpSession session = request.getSession();
//		CustomerUser loginedUser = (CustomerUser)session.getAttribute("user");
		
//		if(loginedUser == null) {
//			response.sendRedirect("login");
//			return;
//		}
		
		//テストコード
		CustomerUser loginedUser = new CustomerUser();
		loginedUser.setUserId(2);
		
		int itemId = Integer.parseInt(request.getParameter("itemId"));
		int userId = loginedUser.getUserId();
		CartBean cartBean = new CartBean();
		
		cartBean.setItemId(itemId);
		cartBean.setUserId(userId);
		
		Cart.addItemToCart(cartBean);
		ArrayList<CartBean> cartList = Cart.getItemListFromCart(loginedUser);
		
		cartList.forEach(cb -> {
			ItemBean ib = new ItemBean();
			ib.setItemId(cb.getItemId());
			ItemBean itemOptionDetail =  Item.getItemDetailOption(ib);
			cb.setItemOptionDetail(itemOptionDetail.getItemFirstOptionValue());
		});
		
		request.setAttribute("cartList", cartList);
		
		String view = "/WEB-INF/views/customer/cart.jsp";
		request.getRequestDispatcher(view).forward(request, response);
	}

}
