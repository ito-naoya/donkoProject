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

		//商品の詳細情報を取得する
		ItemBean item = Item.getItemDetail(itemBean);
		//商品の色違い画像を取得する
		ArrayList<ItemBean> itemImageList = Item.getItemImageList(itemBean);
		//商品のオプションを取得する
		ArrayList<ItemBean> itemOptionList = Item.getItemOptionList(itemBean);
		//商品のオプション詳細を取得する
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
		
		//カートに商品を追加する
		Cart.addItemToCart(cartBean);
		
		response.sendRedirect("cart");
	}

}
