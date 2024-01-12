package test.model.options.optionsInsert;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import bean.OptionCategoryBean;
import model.options.optionsInsert.InsertItemOptionName;

class TestInsertItemOptionName {

//	//成功テスト
//	@Test
//	void testSuccess() {
//		OptionCategoryBean option = new OptionCategoryBean();
//		option.setOptionCategoryName("hoge");
//		Integer result = InsertItemOptionName.insertItemOptionName(option);
//		assertEquals(1,result);
//	}
//
//	//失敗テスト(値の重複)
//	@Test
//	void testException1() {
//		OptionCategoryBean option = new OptionCategoryBean();
//		option.setOptionCategoryName("衣類サイズ");
//		Integer result = InsertItemOptionName.insertItemOptionName(option);
//		assertEquals(0,result);
//	}

	//失敗テスト(sb1のインジェクション)
	@Test
	void testException2() {
		OptionCategoryBean option = new OptionCategoryBean();
		option.setOptionCategoryName("衣類サイズ");
		Integer result = InsertItemOptionName.insertItemOptionName(option);
		assertEquals(null,result);
	}
}
