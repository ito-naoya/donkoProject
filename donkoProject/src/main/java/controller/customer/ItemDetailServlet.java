package controller.customer;

import java.io.IOException;
import java.util.ArrayList;

import bean.CartBean;
import bean.ItemBean;
import classes.Cart;
import classes.ErrorHandling;
import classes.Item;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/itemDetail")
public class ItemDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
    public ItemDetailServlet() {
        super();
    }

    //商品の詳細情報を表示する
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		Integer itemId = Integer.valueOf(request.getParameter("itemId"));
		String source = request.getParameter("source");
		String categoryName = request.getParameter("categoryName");
		
		String disp = "/header";
		RequestDispatcher dispatch = request.getRequestDispatcher(disp);
		dispatch.include(request, response);
		
		//商品IDを保持するitemBeanをnew
		ItemBean ib = new ItemBean();
		//商品IDをitemBeanにセットする
		ib.setItemId(itemId);

		//商品IDを元に商品の詳細情報を取得する
		ItemBean item = Item.getItemDetail(ib);
		
		//データベースから取得できなかった時
		if(item == null ) {			
			//エラーページに遷移
			ErrorHandling.transitionToErrorPage(request,response,"商品詳細情報の取得時に問題が発生しました。","home","ホームに");
			return;
		} 
		
		//詳細表示する商品の色違い画像を取得する
		ArrayList<ItemBean> itemImageList = Item.getItemImageList(ib);
		
		//データベースから取得できなかった時
		if(itemImageList == null ) {			
			//エラーページに遷移
			ErrorHandling.transitionToErrorPage(request,response,"商品画像の取得時に問題が発生しました。","home","ホームに");
			return;
		} 
		
		
		//商品の登録されているオプションを全て取得する(衣類：S、M、L)
		ArrayList<ItemBean> itemOptionList = Item.getItemOptionList(ib);
		
		//データベースから取得できなかった時
		if(itemOptionList == null) {			
			//エラーページに遷移
			ErrorHandling.transitionToErrorPage(request,response,"商品オプションの取得時に問題が発生しました。","home","ホームに");
			return;
		} 
		
		//カテゴリ別商品一覧ページからの遷移
		if(source != null && categoryName != null) {
			request.setAttribute("url", source + "?categoryName=" + categoryName);
		//トップページの商品一覧からの遷移
		}else if(source != null && categoryName == null) {
			request.setAttribute("url", source);
		//商品詳細画面で種類違い選択時の遷移
		}else {
			request.setAttribute("url", "itemDetail?itemId=" + itemId);
		}
		
		request.setAttribute("item", item);
		request.setAttribute("itemImageList", itemImageList);
		request.setAttribute("itemOptionList", itemOptionList);
		
		//商品詳細ページを返す
		String view = "/WEB-INF/views/customer/itemDetail.jsp";
		request.getRequestDispatcher(view).forward(request, response);

	}
	
	//商品をカートに入れる
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession(false);
		
		if(session == null) {
			response.sendRedirect("userSignin");
			return;
		}
		
		Object loginedUserId = session.getAttribute("user_id");
		
		String disp = "/header";
		RequestDispatcher dispatch = request.getRequestDispatcher(disp);
		dispatch.include(request, response);
		
		String itemId = request.getParameter("itemId");
		Integer quantity = Integer.parseInt(request.getParameter("quantity"));
		
		//商品IDとユーザーIDを保持するcartBeanをnewする
		CartBean cb = new CartBean();
		//商品IDをcartBeanにセットする
		cb.setItemId(Integer.parseInt(itemId));
		//ログインしているユーザーのIDをcartBeanにセットする
		cb.setUserId(Integer.parseInt(loginedUserId.toString()));
		//商品の数量をcartBeanにセットする
		cb.setQuantity(quantity);
		
		//カートに商品を追加する
		Boolean isCommit = Cart.addItemToCart(cb);
		
		//カート追加に失敗した場合
		if(!isCommit) {
			//エラーページに遷移
			ErrorHandling.transitionToErrorPage(request,response, "カート処理中に問題が発生しました。","cart","カートに");
			return;
		}
		
		//カート画面にリダイレクト
		response.sendRedirect("cart");
	}
}
