package classes;

import java.util.ArrayList;

import bean.ItemBean;
import model.items.itemsDelete.DeleteItemFromItems;
import model.items.itemsInsert.InsertNewItemToItems;
import model.items.itemsSelect.SelectItemDetailFromItems;
import model.items.itemsSelect.SelectItemListFromItems;
import model.items.itemsSelect.SelectItemListFromItemsByCategory;
import model.items.itemsSelect.SelectItemListFromItemsByOption;
import model.items.itemsUpdate.UpdateItemInfoInItems;

public class Item {
	
	//商品をカテゴリとオプション指定して取得する
	public static ArrayList<ItemBean> getItemListByOption(ItemBean itemBean){
		return SelectItemListFromItemsByOption.selectItemListFromItemsByOption(itemBean);
	};
	
	//商品をカテゴリ指定で取得する
	public static ArrayList<ItemBean> getItemListByCategory(ItemBean itemBean){
		return SelectItemListFromItemsByCategory.selectItemListFromItemsByCategory(itemBean);
	};
	
	//商品の一覧を取得する
	public static ArrayList<ItemBean> getItemList(){
		return SelectItemListFromItems.selectItemListFromItems();
	};
	
	//商品の詳細を取得する
	public static ItemBean getItemDetail(ItemBean itemBean){
		return SelectItemDetailFromItems.selectItemDetailFromItems(itemBean);
	};
	
	//商品を新規登録する
	public static void registerNewItem(ItemBean itemBean){
		InsertNewItemToItems.insertNewItemToItems(itemBean);
	};
	
	//商品の情報を更新する
	public static void updateItemInfo(ItemBean itemBean){
		UpdateItemInfoInItems.updateItemInfoInItems(itemBean);
	};
	
	//商品を削除する（論理削除）
	public static void deleteItem(ItemBean itemBean){
		DeleteItemFromItems.deleteItemFromItems(itemBean);
	};

}
