package test.model.items.itemsSelect;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import bean.ItemBean;
import model.items.itemsSelect.SelectItemAndOptionListByDelFlg;

class TestsSlectItemAndOptionListByDelFlg {
//	//成功テスト
	@Test
	void testSuccess() {
		ArrayList<ItemBean> result = SelectItemAndOptionListByDelFlg.selectItemAndOptionListByDelFlg(0,"衣類");
		assertTrue(result instanceof ArrayList<ItemBean>);
	}

	//失敗テスト(不正なステータス)
	@Test
	void testException1() {
		ArrayList<ItemBean> result = SelectItemAndOptionListByDelFlg.selectItemAndOptionListByDelFlg(-1,"衣類");
		assertEquals(null, result);
	}

	//失敗テスト(不正なステータス)
	@Test
	void testException2() {
		ArrayList<ItemBean> result = SelectItemAndOptionListByDelFlg.selectItemAndOptionListByDelFlg(0,"不正なカテゴリ");
		assertEquals(null, result);
	}
}
