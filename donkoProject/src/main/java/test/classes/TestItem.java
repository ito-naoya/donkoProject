package test.classes;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import bean.ItemBean;
import classes.Item;

public class TestItem {

    ItemBean testItem;
    @BeforeEach
    void setUp() {
        testItem = new ItemBean();
    }

    //getItemAndOptionListAllのテスト
    @Nested
    class GetItemAndOptionListAllTests {
        @Test
        void returnsAllItemsWhenCategoryIsAll() {// "全ての商品"を指定した場合、空値に変換されて結果が出力
            ArrayList<ItemBean> result = Item.getItemAndOptionListAll("全ての商品");
            assertNotNull(result);
        }
    }

    //getItemAndOptionListAllのテスト
    @Nested
    class getItemAndOptionListByDelFlg {
        @Test
        void returnsAllItemsWhenCategoryIsAll() {// "全ての商品"を指定した場合、空値に変換されて結果が出力
            ArrayList<ItemBean> result = Item.getItemAndOptionListByDelFlg(0,"全ての商品");
            assertNotNull(result);
        }
    }

    //checkRegistItemDetailのテスト
    @Nested
    class PriceTests {
        @Test
        void shouldHandleCommasInPrice() {//正常型
            ItemBean updatedItem = Item.checkRegistItemDetail(testItem, "12,345", "10");
            assertEquals(12345, updatedItem.getItemPrice());
        }
        @Test
        void shouldHandleInvalidPrice() {//不正な文字列
            ItemBean updatedItem = Item.checkRegistItemDetail(testItem, "invalid", "10");
            assertEquals(-1, updatedItem.getItemPrice());
        }
        @Test
        void shouldHandleEmptyPrice() {//空値
            ItemBean updatedItem = Item.checkRegistItemDetail(testItem, "", "10");
            assertEquals(-1, updatedItem.getItemPrice());
        }
    }

    @Nested
    class StockTests {
        @Test
        void shouldProcessValidStock() {//正常型
            ItemBean updatedItem = Item.checkRegistItemDetail(testItem, "12,345", "5");
            assertEquals(5, updatedItem.getItemStock());
        }
        @Test
        void shouldHandleInvalidStock() {//不正な文字列
            ItemBean updatedItem = Item.checkRegistItemDetail(testItem, "12,345", "invalid");
            assertEquals(-1, updatedItem.getItemStock());
        }
        @Test
        void shouldHandleEmptyStock() {//空値
            ItemBean updatedItem = Item.checkRegistItemDetail(testItem, "12,345", "");
            assertEquals(-1, updatedItem.getItemStock());
        }
    }
}
