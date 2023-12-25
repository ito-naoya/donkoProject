package classes;

import bean.ItemBean;
import model.options.optionsInsert.InsertItemOption;

public class Option {

	//商品のオプションを登録する
	public static void registerItemOption(ItemBean itemBean) {
		InsertItemOption.insertItemOption(itemBean);
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
