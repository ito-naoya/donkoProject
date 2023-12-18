package controller.customer;

import java.io.IOException;
import java.util.ArrayList;

import bean.CartBean;
import bean.ItemBean;
import classes.Cart;
import classes.Item;
import classes.user.CustomerUser;
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

    //商品の詳細情報を表示する
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		int itemId = Integer.parseInt(request.getParameter("itemId"));
		
		//商品IDを保持するitemBeanをnew
		ItemBean ib = new ItemBean();
		//商品IDをitemBeanにセットする
		ib.setItemId(itemId);

		//商品IDを元に商品の詳細情報を取得する
		ItemBean item = Item.getItemDetail(ib);
		//詳細表示する商品の色違い画像を取得する
		ArrayList<ItemBean> itemImageList = Item.getItemImageList(ib);
		//商品の登録されているオプションを全て取得する(衣類：S、M、L)
		ArrayList<ItemBean> itemOptionList = Item.getItemOptionList(ib);
		
		if(item != null && itemImageList != null && itemOptionList != null) {
			request.setAttribute("item", item);
			request.setAttribute("itemImageList", itemImageList);
			request.setAttribute("itemOptionList", itemOptionList);
			
			//商品詳細ページを返す
			String view = "/WEB-INF/views/customer/itemDetail.jsp";
			request.getRequestDispatcher(view).forward(request, response);
		} else {
			request.setAttribute("errorMessage", "商品詳細情報の取得に失敗しました。");
			request.setAttribute("url", "home");

			//エラーページを返す
			String view = "/WEB-INF/views/component/message.jsp";
			request.getRequestDispatcher(view).forward(request, response);
			
		}
	}
	
	//商品をカートに入れる
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
		
		String itemId = request.getParameter("itemId");
		
		//商品IDとユーザーIDを保持するcartBeanをnewする
		CartBean cb = new CartBean();
		//商品IDをcartBeanにセットする
		cb.setItemId(Integer.parseInt(itemId));
		//ログインしているユーザーのIDをcartBeanにセットする
		cb.setUserId(loginedUser.getUserId());
		
		//カートに商品を追加する
		Cart.addItemToCart(cb);
		
		//カート画面にリダイレクト
		response.sendRedirect("cart");
	}
}
