package test.model.items.itemsInsert;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import bean.ItemBean;
import model.items.itemsInsert.InsertNewItemToItems;

class TestInsertNewItemToItems {

	//成功テスト
	@Test
	void testSuccess() {
		ItemBean item = new ItemBean();
		String[] itemSecondOptionIncrementIds = {"1"};
		item.setItemCategoryName("衣類");
		item.setItemName("hoge");
		item.setItemDescription("hoge");
		item.setItemPrice(0);
		item.setItemStock(0);
		item.setImageFileName("hoge");
		item.setItemFirstOptionName("色");
		item.setItemFirstOptionIncrementId(1);
		item.setItemSecondOptionName("衣類サイズ");
		item.setItemSecondOptionIncrementId(1);
		Boolean result = InsertNewItemToItems.insertNewItemToItems(item,2,itemSecondOptionIncrementIds);
		assertTrue(result);
	}
	//失敗テスト1,INSERT_NEWITEM_ITEM_SQLのインジェクション
	@Test
	void testException1() {
		ItemBean item = new ItemBean();
		String[] itemSecondOptionIncrementIds = {null};
		item.setItemCategoryName(null);
		item.setItemName(null);
		item.setItemDescription(null);
		item.setItemPrice(-1);
		item.setItemStock(-1);
		item.setImageFileName(null);
		item.setItemFirstOptionName("色");
		item.setItemFirstOptionIncrementId(1);
		Boolean result = InsertNewItemToItems.insertNewItemToItems(item,1,itemSecondOptionIncrementIds);
		assertFalse(result);
	}
	//失敗テスト1,INSERT_NEWITEM_OPTION_SQLのインジェクション
	@Test
	void testException2() {
		ItemBean item = new ItemBean();
		String[] itemSecondOptionIncrementIds = {"1"};
		item.setItemCategoryName("衣類");
		item.setItemName("hoge");
		item.setItemDescription("hoge");
		item.setItemPrice(0);
		item.setItemStock(0);
		item.setImageFileName("hoge");
		item.setItemFirstOptionName(null);
		item.setItemFirstOptionIncrementId(0);
		item.setItemSecondOptionName(null);
		item.setItemSecondOptionIncrementId(0);
		Boolean result = InsertNewItemToItems.insertNewItemToItems(item,2,itemSecondOptionIncrementIds);
		assertFalse(result);
	}

}
