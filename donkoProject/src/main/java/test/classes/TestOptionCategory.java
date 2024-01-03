package test.classes;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import bean.ItemBean;
import bean.OptionCategoryBean;
import classes.OptionCategory;

class TestOptionCategory {

	// 成功テスト
	@Test
	void testSuccess() {
		ItemBean itemBean = new ItemBean();
		itemBean.setItemCategoryName("靴");
		ArrayList<ArrayList<OptionCategoryBean>> result = OptionCategory.getOptionCategoryListAllByCategory(itemBean);
		assertTrue(result instanceof ArrayList<ArrayList<OptionCategoryBean>>);
	}
	
	// 失敗テスト
	@Test
	void testException() {
		ItemBean itemBean = new ItemBean();
		itemBean.setItemCategoryName(null);
		ArrayList<ArrayList<OptionCategoryBean>> result = OptionCategory.getOptionCategoryListAllByCategory(itemBean);
		assertEquals(null, result);
	}
}
