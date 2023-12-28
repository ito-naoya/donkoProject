package test.model.optionCategories.optionCategoriesSelect;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import bean.ItemCategoryBean;
import bean.OptionCategoryBean;
import model.optionCategories.optionCategoriesSelect.SelectOptionCategoryListByCategory;

class TestSelectOptionCategoryListByCategory {

	//成功テスト
	@Test
	void testSuccess() {
		ItemCategoryBean item  = new ItemCategoryBean();
		item.setOptionCategoryName("色");
		ArrayList<OptionCategoryBean> result = SelectOptionCategoryListByCategory.selectOptionCategoryListByCategory(item);
		assertTrue(result instanceof ArrayList<OptionCategoryBean>);
	}

	//失敗テスト
	@Test
	void testException() {
		ItemCategoryBean item  = new ItemCategoryBean();
		item.setOptionCategoryName("");
		ArrayList<OptionCategoryBean> result = SelectOptionCategoryListByCategory.selectOptionCategoryListByCategory(item);
		assertEquals(null, result);
	}
}
