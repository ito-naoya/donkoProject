package classes;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import bean.ItemBean;
import jakarta.servlet.ServletContext;
import jakarta.servlet.http.Part;
import model.items.itemsInsert.InsertNewItemToItems;
import model.items.itemsSelect.SelectItemAllDetailFromItems;
import model.items.itemsSelect.SelectItemAlreadyExistFromItems;
import model.items.itemsSelect.SelectItemAndOptionListByDelFlg;
import model.items.itemsSelect.SelectItemDetailFromItems;
import model.items.itemsSelect.SelectItemImageListFromItems;
import model.items.itemsSelect.SelectItemListFromItemsByCategory;
import model.items.itemsSelect.SelectItemListFromItemsByOption;
import model.items.itemsSelect.SelectItemListWithoutDuplicate;
import model.items.itemsSelect.SelectItemNameListFromItemsByCategory;
import model.items.itemsSelect.SelectItemOptionListFromItems;
import model.items.itemsSelect.SelectRandomItemListFromItems;
import model.items.itemsUpdate.UpdateItemDeleteFromItems;
import model.items.itemsUpdate.UpdateItemInfoInItems;

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
			return SelectRandomItemListFromItems.selectItemListFromItems();
		};

	//商品の一覧を取り扱い指定込みで取得する(カテゴリ指定も可能)
	public static ArrayList<ItemBean> getItemAndOptionListByDelFlg(int itemDeleteFlg, String itemCategoryName, String sortOrder, String keyword){
		//全ての商品を抽出する場合は、値をセットしない
		if(itemCategoryName.equals("全ての商品")) {
			itemCategoryName = "";
		}
		return SelectItemAndOptionListByDelFlg.selectItemAndOptionListByDelFlg(itemDeleteFlg, itemCategoryName, sortOrder, keyword);
	};

	//商品の詳細を取得する
	public static ItemBean getItemDetail(ItemBean itemBean){
		return SelectItemDetailFromItems.selectItemDetailFromItems(itemBean);
	};

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
	public static boolean registerNewItem(ItemBean itemBean, int selectBoxCount, String[] itemSecondOptionIncrementIds){
		return(InsertNewItemToItems.insertNewItemToItems(itemBean,selectBoxCount,itemSecondOptionIncrementIds));
	};

	//商品が既に存在するかをチェックする
	public static ArrayList<Integer> checkItemAlreadyExist(ItemBean itemAddOption,  String[] itemSecondOptionIncrementIds) {
		return (SelectItemAlreadyExistFromItems.selectItemAlreadyExistFromItems(itemAddOption, itemSecondOptionIncrementIds));
	};

	//商品の情報を更新する
	public static Boolean updateItemInfo(ItemBean itemBean,  int selectBoxCount){
		return (UpdateItemInfoInItems.updateItemInfoInItems(itemBean, selectBoxCount));
	};

	//商品を削除する（論理削除）
	public static int deleteItem(String[] itemStatus){
		if (itemStatus == null || itemStatus.length == 0) {
			return 1;//なんか入れて
		}
		if(!UpdateItemDeleteFromItems.deleteItemFromItems(itemStatus)) {
			return 2;//不正なSQL
		}
		return 0;//成功
	};

	//写真を新規登録
	public static boolean registerNewImage(Part part,String fileName, ServletContext context){
		final long MAX_SIZE = 2 * 1024 * 1024; // 2MB
	    final String TYPE = "image/jpg";

	    if (part.getSize() == 0 || fileName.isEmpty() || part.getSize() > MAX_SIZE || !part.getContentType().equals(TYPE)) {
	        return false;
		} else {
				try {
					// サーブレットコンテキストで相対パスの場所を取得して、絶対パスに変換。最後に/をつける。
		            String imagesDirectory = context.getRealPath("/images") + File.separator;
		            String filePath = imagesDirectory + fileName + ".jpg";

				    // ファイルを保存
					part.write(filePath);
				} catch (IOException e) {
					e.printStackTrace();
					return false;
				}
			return true;
		}
	};

	//写真を編集、変更
	public static boolean renameNewImage(Part part, String fileName, String oldFileName, ServletContext context) {
		final long MAX_SIZE = 2 * 1024 * 1024; // 2MB
	    final String TYPE = "image/jpeg";

		// サーブレットコンテキストで相対パスの場所を取得して、絶対パスに変換。最後に/をつける。
        String imagesDirectory = context.getRealPath("/images") + File.separator;
	    if (part.getSize() != 0) {// 写真がある場合
	    	if(part.getSize() > MAX_SIZE || !part.getContentType().equals(TYPE)) {
	    		return false;
	    	}
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
	            return false;
	        }
	    } else if (!fileName.equals(oldFileName)) {// 写真がないが、名前が異なる場合
	        File oldFile = new File(imagesDirectory + oldFileName + ".jpg");
	        File newFile = new File(imagesDirectory + fileName + ".jpg");
	        // ファイル名を変更
	        if (oldFile.exists()) {
	            oldFile.renameTo(newFile);
	        }
	    }
	    // その他の場合は何もしない(商品名も写真名も変更がない時)
	    return true;
	}

	//商品登録画面から取得した値のnull値及び文字数をチェックして、ItemBeanにセット
	public static ItemBean checkRegistItemDetail(ItemBean newItem, String price, String stock) {
		String cleanedPrice = price.replaceAll(",", "");
		int processedPrice = -1;
	    int processedStock = -1;

	    // 金額の処理
	    if (!cleanedPrice.isEmpty() && cleanedPrice.matches("\\d+")) {
	        processedPrice = Integer.parseInt(cleanedPrice);
	    }

	    // 在庫の処理
	    if (!stock.isEmpty() && stock.matches("\\d+")) {
	        processedStock = Integer.parseInt(stock);
	    }

	    // ItemBeanに値をセット
	    newItem.setItemPrice(processedPrice);
	    newItem.setItemStock(processedStock);

	    return newItem;
	}

}
