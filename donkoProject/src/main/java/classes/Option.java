package classes;

import bean.ItemBean;
import bean.OptionCategoryBean;
import model.options.optionsDelete.DeleteItemOptionName;
import model.options.optionsDelete.DeleteItemOptionValue;
import model.options.optionsInsert.InsertItemOptionName;
import model.options.optionsInsert.InsertItemOptionValue;

public class Option {

	//商品のオプションを登録する
	public static Integer registerItemOptionName(OptionCategoryBean optionCategoryBean) {
		return InsertItemOptionName.insertItemOptionName(optionCategoryBean);
	}

	//商品のオプション詳細を登録する
	public static Integer registerItemOptionValue(OptionCategoryBean optionCategoryBean) {
		return InsertItemOptionValue.insertItemOptionValue(optionCategoryBean);
	}

	//商品のオプションを削除する
	public static Integer deleteItemOptionName(OptionCategoryBean optionCategoryBean) {
		return DeleteItemOptionName.deleteItemOptionName(optionCategoryBean);
	}

	//商品のオプション詳細を削除する
	public static Integer deleteItemOptionValue(OptionCategoryBean optionCategoryBean) {
		return DeleteItemOptionValue.deleteItemOptionValue(optionCategoryBean);
	}

	//商品登録画面から取得したオプション項目のnull値及び文字数をチェックして、ItemBeanにセット
	public static ItemBean checkRegistItemOptionDetail(String itemId, ItemBean itemBean, String itemFirstOptionIncrementId, String[] itemSecondOptionIncrementIds) {
	    // 商品id
	    if (itemId.isEmpty() || !itemId.matches("\\d+")) {
	        return null;
	    }

	    // オプションId1
	    int firstOptionId;
	    if (itemFirstOptionIncrementId == null) {
	        firstOptionId = 0;
	    } else if (!itemFirstOptionIncrementId.matches("\\d+")) {
	        return null;
	    } else {
	        firstOptionId = Integer.valueOf(itemFirstOptionIncrementId);
	    }

	    // オプションId2
	    int secondOptionId = 0; // 初期値を0に設定
	    if (itemSecondOptionIncrementIds != null && itemSecondOptionIncrementIds.length > 0) {
	        if (!itemSecondOptionIncrementIds[0].matches("\\d+")) {
	            return null;
	        }
	        secondOptionId = Integer.valueOf(itemSecondOptionIncrementIds[0]);
	    }

	    itemBean.setItemId(Integer.valueOf(itemId));
	    itemBean.setItemFirstOptionIncrementId(Integer.valueOf(firstOptionId));
	    itemBean.setItemSecondOptionIncrementId(secondOptionId);

	    return itemBean;
	}


}
