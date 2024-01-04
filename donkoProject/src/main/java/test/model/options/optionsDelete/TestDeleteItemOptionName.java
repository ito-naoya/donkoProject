package test.model.options.optionsDelete;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import bean.OptionCategoryBean;
import model.options.optionsDelete.DeleteItemOptionName;

class TestDeleteItemOptionName {

	//成功テスト
	@Test
	void testSuccess() {
		OptionCategoryBean option = new OptionCategoryBean();
		option.setOptionCategoryName("hoge");
		Integer result = DeleteItemOptionName.deleteItemOptionName(option);
		assertEquals(1,result);
	}

	//失敗テスト
	@Test
	void testException1() {
		OptionCategoryBean option = new OptionCategoryBean();
		option.setOptionCategoryName("衣類サイズ");
		Integer result = DeleteItemOptionName.deleteItemOptionName(option);
		assertEquals(0,result);
	}

//	//失敗テスト
//	@Test
//	void testException2() {
//		OptionCategoryBean option = new OptionCategoryBean();
//		option.setOptionCategoryName("衣類サイズ");
//		Integer result = DeleteItemOptionName.deleteItemOptionName(option);
//		assertEquals(null,result);
//	}
}
