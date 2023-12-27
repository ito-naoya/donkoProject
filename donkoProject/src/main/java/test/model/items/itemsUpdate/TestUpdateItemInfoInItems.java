package test.model.items.itemsUpdate;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import bean.ItemBean;
import model.items.itemsUpdate.UpdateItemInfoInItems;

class TestUpdateItemInfoInItems {

	//成功テスト
	@Test
	void testSuccess() {
		ItemBean item = new ItemBean();
		item.setItemCategoryName("衣類");
		item.setItemName("hoge");
		item.setItemDescription("hoge");
		item.setItemPrice(0);
		item.setItemStock(0);
		item.setImageFileName("hoge");
		item.setItemId(131); //既存データより
		item.setItemFirstOptionName("色");
		item.setItemFirstOptionIncrementId(1);
		item.setItemSecondOptionName("衣類サイズ");
		item.setItemSecondOptionIncrementId(1);
		Boolean result = UpdateItemInfoInItems.updateItemInfoInItems(item,2);
		assertTrue(result);
	}
	//失敗テスト1,INSERT_NEWITEM_ITEM_SQLのインジェクション
	@Test
	void testException1() {
		ItemBean item = new ItemBean();
		item.setItemCategoryName("");
		item.setItemName("");
		item.setItemDescription("");
		item.setItemPrice(-1);
		item.setItemStock(-1);
		item.setImageFileName("");
		item.setItemId(0); //hoge
		item.setItemFirstOptionName("色");
		item.setItemFirstOptionIncrementId(1);
		Boolean result = UpdateItemInfoInItems.updateItemInfoInItems(item,1);
		assertFalse(result);
	}
	//失敗テスト1,INSERT_NEWITEM_OPTION_SQLのインジェクション
	@Test
	void testException2() {
		ItemBean item = new ItemBean();
		item.setItemCategoryName("衣類");
		item.setItemName("hoge");
		item.setItemDescription("hoge");
		item.setItemPrice(0);
		item.setItemStock(0);
		item.setImageFileName("hoge");
		item.setItemFirstOptionName("");
		item.setItemFirstOptionIncrementId(0);
		item.setItemSecondOptionName("");
		item.setItemSecondOptionIncrementId(0);
		Boolean result = UpdateItemInfoInItems.updateItemInfoInItems(item,2);
		assertFalse(result);
	}

}
