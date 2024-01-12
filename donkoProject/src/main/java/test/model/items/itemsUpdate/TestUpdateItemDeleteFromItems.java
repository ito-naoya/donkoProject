package test.model.items.itemsUpdate;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import model.items.itemsUpdate.UpdateItemDeleteFromItems;

class TestUpdateItemDeleteFromItems {

    //成功テスト
    @Test
    void testSuccess() {
        String[] itemStatus = {"1", "2", "3"};
        Boolean result = UpdateItemDeleteFromItems.deleteItemFromItems(itemStatus);
        assertTrue(result);
    }

    //失敗テスト
    @Test
    void testExceptionWithEmptyArray() {
        String[] itemStatus = {}; // 空の配列
        Boolean result = UpdateItemDeleteFromItems.deleteItemFromItems(itemStatus);
        assertFalse(result);
    }
}
