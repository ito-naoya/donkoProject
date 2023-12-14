package classes;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import bean.ItemBean;
import bean.PurchaseBean;
import jakarta.servlet.http.Part;
import model.items.itemsDelete.DeleteItemFromItems;
import model.items.itemsInsert.InsertNewItemToItems;
import model.items.itemsSelect.SelectItemAllDetailFromItems;
import model.items.itemsSelect.SelectItemAndOptionListAll;
import model.items.itemsSelect.SelectItemAndOptionListByDelFlg;
import model.items.itemsSelect.SelectItemDetailFromItems;
import model.items.itemsSelect.SelectItemDetailOptionFromItems;
import model.items.itemsSelect.SelectItemImageListFromItems;
import model.items.itemsSelect.SelectItemListFromItems;
import model.items.itemsSelect.SelectItemListFromItemsByCategory;
import model.items.itemsSelect.SelectItemListFromItemsByOption;
import model.items.itemsSelect.SelectItemListWithoutDuplicate;
import model.items.itemsSelect.SelectItemNameListFromItemsByCategory;
import model.items.itemsSelect.SelectItemOptionListFromItems;
import model.items.itemsUpdate.UpdateItemInfoInItems;
import model.items.itemsUpdate.UpdateItemStockInItems;

public class Item {

	//商品をカテゴリとオプション指定して取得する
	public static ArrayList<ItemBean> getItemListByOption(String[] checkedOption, String categoryName){
		return SelectItemListFromItemsByOption.selectItemListFromItemsByOption(checkedOption, categoryName);
	};

	//登録されている商品名を重複なしで取得する
	public static ArrayList<ItemBean> getItemListWithoutDuplicate(){
		return SelectItemListWithoutDuplicate.selectItemListWithoutDuplicate();
	};

	//商品をカテゴリ指定で取得する
	public static ArrayList<ItemBean> getItemListByCategory(ItemBean itemBean){
		return SelectItemListFromItemsByCategory.selectItemListFromItemsByCategory(itemBean);
	};

	//商品名をカテゴリ指定で取得する
	public static ArrayList<ItemBean>getItemNameListByCategory(ItemBean itemBean){
		return SelectItemNameListFromItemsByCategory.selectItemNameListFromItemsByCategory(itemBean);
	}

	//商品の一覧を取得する
		public static ArrayList<ItemBean> getItemList(){
			return SelectItemListFromItems.selectItemListFromItems();
		};

	//商品の一覧を取得する(カテゴリ指定も可能)
	public static ArrayList<ItemBean> getItemAndOptionListAll(String itemCategoryName){
		return SelectItemAndOptionListAll.selectItemAndOptionListAll(itemCategoryName);
	};

	//商品の一覧を取り扱い指定込みで取得する(カテゴリ指定も可能)
	public static ArrayList<ItemBean> getItemAndOptionListByDelFlg(int itemDeleteFlg, String itemCategoryName){
		return SelectItemAndOptionListByDelFlg.selectItemAndOptionListByDelFlg(itemDeleteFlg, itemCategoryName);
	};

	//商品の詳細を取得する
	public static ItemBean getItemDetail(ItemBean itemBean){
		return SelectItemDetailFromItems.selectItemDetailFromItems(itemBean);
	};

	//商品の詳細情報（オプションだけ）を取得する
	public static ItemBean getItemDetailOption(ItemBean itemBean) {
		return SelectItemDetailOptionFromItems.selectItemDetailOptionFromItems(itemBean);
	}

	//商品がもつ全ての情報を取得する
	public static ItemBean getItemAllDetail(ItemBean itemBean) {
		return SelectItemAllDetailFromItems.selectItemAllDetailFromItems(itemBean);
	}

	//商品の画像一覧を取得する
	public static ArrayList<ItemBean> getItemImageList(ItemBean itemBean) {
		return SelectItemImageListFromItems.selectItemImageListFromItems(itemBean);
	}

	//登録されている商品のオプション一覧を取得する
	public static ArrayList<ItemBean> getItemOptionList(ItemBean itemBean){
		return SelectItemOptionListFromItems.selectItemOptionListFromItems(itemBean);
	}

	//商品を新規登録する
	public static void registerNewItem(ItemBean itemBean, int selectBoxCount){
		InsertNewItemToItems.insertNewItemToItems(itemBean,selectBoxCount);
	};

	//商品の情報を更新する
	public static void updateItemInfo(ItemBean itemBean,  int selectBoxCount){
		UpdateItemInfoInItems.updateItemInfoInItems(itemBean, selectBoxCount);
	};

	//商品の在庫数を更新する
	public static void updateItemStock(PurchaseBean purchaseBean) {
		UpdateItemStockInItems.updateItemStockInItems(purchaseBean);
	}

	//商品を削除する（論理削除）
	public static void deleteItem(ItemBean itemBean){
		DeleteItemFromItems.deleteItemFromItems(itemBean);
	};
	//商品画像をドキュメント内に登録する
	public static void registerNewImage(Part part,String fileName, String oldFileName){
		if(part != null && !fileName.isEmpty()) {
			try {
			    // フルパスじゃないと上手く読み込まれないみたいなので、自分のファイルパスに適宜変更してください。
			    String filePath = "/Users/nakahara.erika/git/donkoProject/donkoProject/src/main/webapp/images/" + fileName + ".jpg";
			    // ファイルを保存
				part.write(filePath);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	};

	public static void renameNewImage(Part part, String fileName, String oldFileName) {
	    String imagesDirectory = "/Users/nakahara.erika/git/donkoProject/donkoProject/src/main/webapp/images/";
	    // 写真がある場合
	    if (part != null) {
	        // 古いファイルを削除（名前が異なる場合）
	        if (!fileName.equals(oldFileName)) {
	            File oldFile = new File(imagesDirectory + oldFileName + ".jpg");
	            if (oldFile.exists()) {
	                oldFile.delete();
	            }
	        }
	        // 新しいファイルを保存
	        String newFilePath = imagesDirectory + fileName + ".jpg";
	        try {
	            part.write(newFilePath);
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	    // 写真がないが、名前が異なる場合
	    else if (!fileName.equals(oldFileName)) {
	        File oldFile = new File(imagesDirectory + oldFileName + ".jpg");
	        File newFile = new File(imagesDirectory + fileName + ".jpg");
	        // ファイル名を変更
	        if (oldFile.exists()) {
	            oldFile.renameTo(newFile);
	        }
	    }
	    // その他の場合は何もしない
	}

	//ふやしました
	//商品登録画面から取得した値のnull値及び文字数をチェックして、ItemBeanにセット
		public static ItemBean checkRegistItemDetail(String itemCategoryName, String itemName, String itemDescription, String price, String stock) {
			//カテゴリー名
			if(itemCategoryName.isEmpty() || itemCategoryName.length() > 20) {
				return null;
			}
			//商品名
			if(itemName.isEmpty() || itemName.length() > 30) {
				return null;
			}
			//商品説明
			if(itemDescription.isEmpty() || itemDescription.length() > 100) {
				return null;
			}
			//金額
			if(price.isEmpty() || price.length() > 11) {
				return null;
			}
			//在庫
			if(stock.isEmpty() || stock.length() > 11) {
				return null;
			}

			//ItemBeanに値をセット

			ItemBean newItem = new ItemBean();
			newItem.setItemCategoryName(itemCategoryName);
			newItem.setItemName(itemName);
			newItem.setItemDescription(itemDescription);
			newItem.setItemPrice(Integer.valueOf(price.replaceAll(",", "")));
			newItem.setItemStock(Integer.valueOf(stock));

			return newItem;

		};

}
