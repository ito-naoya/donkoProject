package test.model.options.optionsDelete;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import bean.OptionCategoryBean;
import model.options.optionsDelete.DeleteItemOptionValue;

class TestDeleteItemOptionValue {

	//成功テスト
	@Test
	void testSuccess() {
		OptionCategoryBean option = new OptionCategoryBean();
		option.setOptionCategoryName("hoge");
		option.setOptionCategoryValue("fuga");
		Integer result = DeleteItemOptionValue.deleteItemOptionValue(option);
		assertEquals(1,result);
	}

	//失敗テスト
	@Test
	void testException1() {
		OptionCategoryBean option = new OptionCategoryBean();
		option.setOptionCategoryName("衣類");
		option.setOptionCategoryName("衣類サイズ");
		Integer result = DeleteItemOptionValue.deleteItemOptionValue(option);
		assertEquals(0,result);
	}

//	//失敗テスト
//	@Test
//	void testException2() {
//		OptionCategoryBean option = new OptionCategoryBean();
//		option.setOptionCategoryName("hoge");
//		option.setOptionCategoryValue("fuga");
//		Integer result = DeleteItemOptionValue.deleteItemOptionValue(option);
//		assertEquals(null,result);
//	}
}
