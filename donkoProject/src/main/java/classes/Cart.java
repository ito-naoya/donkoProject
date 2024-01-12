package classes;

import java.util.ArrayList;

import bean.CartBean;
import classes.user.CustomerUser;
import model.carts.cartsDelete.DeleteItemAllFromCarts;
import model.carts.cartsDelete.DeleteItemFromCarts;
import model.carts.cartsInsert.InsertItemToCarts;
import model.carts.cartsSelect.SelectItemListFromCarts;
import model.carts.cartsUpdate.UpdateItemQuantityInCarts;

public class Cart {
	
	//カートに商品を追加する
	public static Boolean addItemToCart(CartBean cartBean){
		return InsertItemToCarts.insertItemToCarts(cartBean);
	};
	
	//カートから商品を削除する
	public static Boolean deleteItemFromCart(CartBean cartBean){
		return DeleteItemFromCarts.deleteItemFromCarts(cartBean);
	};
	
	//カート内の商品一覧を取得する
	public static ArrayList<CartBean> getItemListFromCart(CustomerUser customerUser){
		return SelectItemListFromCarts.selectItemListFromCarts(customerUser);
	};
	
	//カート内の商品を全て削除する
	public static Boolean deleteAllItemFromCart(CustomerUser customerUser){
		return DeleteItemAllFromCarts.deleteItemAllFromCarts(customerUser);
	};
	
	//カート内の商品の数量を更新する
	public static Boolean updateItemQuantityInCart(CartBean cartBean){
		return UpdateItemQuantityInCarts.updateItemQuantityInCarts(cartBean);
	};

}
