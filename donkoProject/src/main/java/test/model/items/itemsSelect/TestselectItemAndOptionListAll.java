package test.model.items.itemsSelect;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import bean.ItemBean;
import model.items.itemsSelect.SelectItemAndOptionListAll;

class TestselectItemAndOptionListAll {
//	//成功テスト
	@Test
	void testSuccess() {
		ArrayList<ItemBean> result = SelectItemAndOptionListAll.selectItemAndOptionListAll("衣類");
		assertTrue(result instanceof ArrayList<ItemBean>);
	}

	//失敗テスト
	@Test
	void testException1() {
		ArrayList<ItemBean> result = SelectItemAndOptionListAll.selectItemAndOptionListAll("不正なカテゴリ");
		assertEquals(null, result);
	}

//	//失敗テスト(空値で手動でexceptionを発生)
//	@Test
//	void testException2() {
//		ArrayList<ItemBean> result = SelectItemAndOptionListAll.selectItemAndOptionListAll("");
//		assertEquals(null, result);
//	}
}
