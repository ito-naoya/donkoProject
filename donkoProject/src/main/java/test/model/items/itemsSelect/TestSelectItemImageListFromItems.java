package test.model.items.itemsSelect;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import bean.ItemBean;
import model.items.itemsSelect.SelectItemImageListFromItems;

class TestSelectItemImageListFromItems {

	//成功テスト
	@Test
	void testSuccess() {
		ItemBean ib = new ItemBean();
		ib.setItemId(1);
		ArrayList<ItemBean> result = SelectItemImageListFromItems.selectItemImageListFromItems(ib);
		assertTrue(result instanceof ArrayList<ItemBean>);
	}

}
