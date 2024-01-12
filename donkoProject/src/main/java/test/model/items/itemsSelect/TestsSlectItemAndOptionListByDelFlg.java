package test.model.items.itemsSelect;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import bean.ItemBean;
import model.items.itemsSelect.SelectItemAndOptionListByDelFlg;

class TestsSelectItemAndOptionListByDelFlg {
    // 成功テスト
    @Test
    void testSuccess() {
        ArrayList<ItemBean> result = SelectItemAndOptionListByDelFlg.selectItemAndOptionListByDelFlg(0, "衣類", "asc", "");
        assertTrue(result instanceof ArrayList<?>);
        assertFalse(result.isEmpty()); // 結果が空でないことを確認
    }

    // 失敗テスト(不正なステータス)
    @Test
    void testException1() {
        ArrayList<ItemBean> result = SelectItemAndOptionListByDelFlg.selectItemAndOptionListByDelFlg(-1, "衣類", "asc", "");
        assertNull(result); // 結果がnullであることを確認
    }

    // 失敗テスト(不正なカテゴリ)
    @Test
    void testException2() {
        ArrayList<ItemBean> result = SelectItemAndOptionListByDelFlg.selectItemAndOptionListByDelFlg(0, "不正なカテゴリ", "asc", "");
        assertNull(result); // 結果がnullであることを確認
    }
}
