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
	
		Integer itemId = Integer.valueOf(request.getParameter("itemId"));
		String source = request.getParameter("source");
		String categoryName = request.getParameter("categoryName");
		
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
		
		//データベースから取得できなかった時
		if(item == null || itemImageList == null || itemOptionList == null) {
			//エラーメッセージ
			request.setAttribute("errorMessage", "商品情報の取得時に問題が発生しました。");
			//エラーページからの遷移先
			request.setAttribute("url", "home");
			//エラーページを返す
			String view = "/WEB-INF/views/component/message.jsp";
			request.getRequestDispatcher(view).forward(request, response);
			return;
		} 
		
		//カテゴリ別商品一覧ページからの遷移
		if(source != null && categoryName != null) {
			request.setAttribute("url", source + "?categoryName=" + categoryName);
		//トップページの商品一覧からの遷移
		}else if(source != null && categoryName == null) {
			request.setAttribute("url", source);
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
		Boolean isCommit = Cart.addItemToCart(cb);
		
		//カート追加に失敗した場合
		if(!isCommit) {
			//エラーメッセージ
			request.setAttribute("errorMessage", "カート処理中に問題が発生しました。");
			//エラーページからの遷移先
			request.setAttribute("url", "cart");
			//エラーページ表示
			String view = "/WEB-INF/views/component/message.jsp";
			request.getRequestDispatcher(view).forward(request, response);
			return;
		}
		
		//カート画面にリダイレクト
		response.sendRedirect("cart");
	}
}
