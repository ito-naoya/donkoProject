package classes;

import bean.ItemBean;
import model.options.optionsInsert.InsertItemOption;

public class Option {
	
	//商品のオプションを登録する
	public static void registerItemOption(ItemBean itemBean) {
		InsertItemOption.insertItemOption(itemBean);
	}

}
