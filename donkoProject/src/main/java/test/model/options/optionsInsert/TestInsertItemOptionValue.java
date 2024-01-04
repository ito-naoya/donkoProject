package test.model.options.optionsInsert;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import bean.OptionCategoryBean;
import model.options.optionsInsert.InsertItemOptionValue;

class TestInsertItemOptionValue {

	//成功テスト
	@Test
	void testSuccess() {
		OptionCategoryBean option = new OptionCategoryBean();
		option.setOptionCategoryName("hoge");
		option.setOptionCategoryValue("fuga");
		Integer result = InsertItemOptionValue.insertItemOptionValue(option);
		assertEquals(1,result);
	}

	//失敗テスト(値の重複)
	@Test
	void testException1() {
		OptionCategoryBean option = new OptionCategoryBean();
		option.setOptionCategoryName("衣類");
		option.setOptionCategoryValue("衣類サイズ");
		Integer result = InsertItemOptionValue.insertItemOptionValue(option);
		assertEquals(0,result);
	}

//	//失敗テスト(sb1のインジェクション)
//	@Test
//	void testException2() {
//		OptionCategoryBean option = new OptionCategoryBean();
//		option.setOptionCategoryName("衣類");
//		option.setOptionCategoryValue("衣類サイズ");
//		Integer result = InsertItemOptionValue.insertItemOptionValue(option);
//		assertEquals(null,result);
//	}
}
