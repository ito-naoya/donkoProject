package classes;

import bean.ItemBean;
import model.options.optionsInsert.InsertItemOption;

public class Option {

	//商品のオプションを登録する
	public static void registerItemOption(ItemBean itemBean) {
		InsertItemOption.insertItemOption(itemBean);
	}

	//商品登録画面から取得したオプション項目のnull値及び文字数をチェックして、ItemBeanにセット
	public static ItemBean checkRegistItemOptionDetail(String itemId, ItemBean itemBean, String itemFirstOptionIncrementId) {
		//セレクトボックスの数で、セットする値を分岐
		// 商品id
	    if(itemId.isEmpty() || !itemId.matches("\\d+")) {
	        return null;
	    }
	    // オプションId1 <1(白)>
	    if(itemFirstOptionIncrementId == null || !itemFirstOptionIncrementId.matches("\\d+")) {
	        return null;
	    }
	    itemBean.setItemId(Integer.valueOf(itemId));
	    itemBean.setItemFirstOptionIncrementId(Integer.valueOf(itemFirstOptionIncrementId));

	    return itemBean;
	}

	public static ItemBean checkItemAndOptionDetail(ItemBean updateItem, String itemId, String fileName,
			String firstOptionId, String secondOptionId, int selectBoxCount) {

		// 商品id
	    if(itemId.isEmpty() || itemId.length() > 11) {
	        return null;
	    }

	    //ファイル名
	    if(fileName.isEmpty() || fileName.length() > 35) {
	        return null;
	    }

	    //オプション1
	    if(firstOptionId.isEmpty() || firstOptionId.length() > 11) {
	    	return null;
	    }

	    if(selectBoxCount == 2) {
		    //オプション2
		    if(secondOptionId.isEmpty() || secondOptionId.length() > 11) {
		    	return null;
		    }
		    updateItem.setItemSecondOptionIncrementId(Integer.valueOf(secondOptionId));
	    }

		updateItem.setItemId(Integer.valueOf(itemId));
		updateItem.setImageFileName(fileName);
		updateItem.setItemFirstOptionIncrementId(Integer.valueOf(firstOptionId));

		return updateItem;
	}
}
