package test.model.items.itemsSelect;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import bean.ItemBean;
import model.items.itemsSelect.SelectItemImageListFromItems;

class TestSelectItemImageListFromItems {

	//存在しない商品IDの場合nullを返す
	@Test
	void doseNotExistIdTest() {
		ItemBean ib = new ItemBean();
		ib.setItemId(0);
		ArrayList<ItemBean> result = SelectItemImageListFromItems.selectItemImageListFromItems(ib);
		assertEquals(null, result);
	}

}
