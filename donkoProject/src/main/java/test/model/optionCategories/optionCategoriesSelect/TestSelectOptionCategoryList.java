package test.model.optionCategories.optionCategoriesSelect;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import bean.OptionCategoryBean;
import model.optionCategories.optionCategoriesSelect.SelectOptionCategoryList;

class TestSelectOptionCategoryList {

	//成功テスト
	@Test
	void testSuccess() {
		ArrayList<OptionCategoryBean> result = SelectOptionCategoryList.selectOptionCategoryList();
		assertTrue(result instanceof ArrayList<OptionCategoryBean>);
	}

//	//失敗テスト
//	@Test
//	void testException() {
//		ArrayList<OptionCategoryBean> result = SelectOptionCategoryList.selectOptionCategoryList();
//		assertEquals(null, result);
//	}
}
