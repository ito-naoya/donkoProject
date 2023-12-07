package classes;

import java.util.ArrayList;

import bean.CartBean;
import bean.ItemBean;
import model.carts.cartsDelete.DeleteItemAllFromCarts;
import model.carts.cartsDelete.DeleteItemFromCarts;
import model.carts.cartsInsert.InsertItemToCarts;
import model.carts.cartsSelect.SelectItemListFromCarts;
import model.carts.cartsUpdate.UpdateItemQuantityInCarts;

public class Cart {
	
	//カートに商品を追加する
	public static void addItemToCart(CartBean cartBean){
		InsertItemToCarts.insertItemToCarts(cartBean);
	};
	
	//カートから商品を削除する
	public static void deleteItemFromCart(CartBean cartBean){
		DeleteItemFromCarts.deleteItemFromCarts(cartBean);
	};
	
	//カート内の商品一覧を取得する
	public static ArrayList<ItemBean> getItemListFromCart(CustomerUser customerUser){
		return SelectItemListFromCarts.selectItemListFromCarts(customerUser);
	};
	
	//カート内の商品を全て削除する
	public static void deleteAllItemFromCart(CustomerUser customerUser){
		DeleteItemAllFromCarts.deleteItemAllFromCarts(customerUser);
	};
	
	//カート内の商品の数量を更新する
	public static void updateItemQuantityInCart(CartBean cartBean){
		UpdateItemQuantityInCarts.updateItemQuantityInCarts(cartBean);
	};

}
