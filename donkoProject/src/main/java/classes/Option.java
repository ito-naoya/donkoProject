package classes;

import bean.ItemBean;
import model.options.optionsInsert.InsertItemOption;

public class Option {

	//商品のオプションを登録する
	public static void registerItemOption(ItemBean itemBean) {
		InsertItemOption.insertItemOption(itemBean);
	}

	//商品登録画面から取得したオプション項目のnull値及び文字数をチェックして、ItemBeanにセット
	public static ItemBean checkRegistItemOptionDetail(ItemBean itemBean, String itemFirstOptionName,
            String itemFirstOptionIncrementId, String itemSecondOptionName, String itemSecondOptionIncrementId, int selectBoxCount) {
		//セレクトボックスの数で、セットする値を分岐
	    // オプション1 <色>
	    if(itemFirstOptionName.isEmpty() || itemFirstOptionName.length() > 20) {
	        return null;
	    }
	    // オプションId1 <1(白)>
	    if(itemFirstOptionIncrementId.isEmpty() || itemFirstOptionIncrementId.length() > 11) {
	        return null;
	    }

	    itemBean.setItemFirstOptionName(itemFirstOptionName);
	    itemBean.setItemFirstOptionIncrementId(Integer.valueOf(itemFirstOptionIncrementId));

	    // オプション2 <衣類サイズ> ※セレクトボックスが2つある場合にのみ処理
	    if(selectBoxCount == 2) {
	        if(itemSecondOptionName.isEmpty() || itemSecondOptionName.length() > 20) {
	            return null;
	        }
	        if(itemSecondOptionIncrementId.isEmpty() || itemSecondOptionIncrementId.length() > 11) {
	            return null;
	        }

	        itemBean.setItemSecondOptionName(itemSecondOptionName);
	        itemBean.setItemSecondOptionIncrementId(Integer.valueOf(itemSecondOptionIncrementId));
	    }

	    return itemBean;
	}
}
