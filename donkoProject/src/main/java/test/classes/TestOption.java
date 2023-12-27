package test.classes;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import bean.ItemBean;
import classes.Option;

class TestOption {
	ItemBean itemBean;
    String[] secondIds;

    @BeforeEach
    void setUp() {
        itemBean = new ItemBean();
        secondIds = new String[1];
    }

    @Nested
    class CheckRegistItemOptionDetailTests {
        @Test
        void shouldReturnNullWhenItemIdIsEmpty() {//itemIdが空値
        	secondIds[0]="1";
            ItemBean result = Option.checkRegistItemOptionDetail("", itemBean, "1",secondIds);
            assertNull(result);
        }
        @Test
        void shouldReturnNullWhenItemIdIsNotNumeric() {//itemIdが数値以外
        	secondIds[0]="1";
            ItemBean result = Option.checkRegistItemOptionDetail("abc", itemBean, "1",secondIds);
            assertNull(result);
        }
        @Nested
        class WhenFirstOptionId {
            @Test
            void shouldReturnNullWhenFirstOptionIdIsNull() {//incrementIdがnull
            	secondIds[0]="1";
                ItemBean result = Option.checkRegistItemOptionDetail("123", itemBean, null, secondIds);
                assertNotNull(result);
                assertEquals(0, result.getItemFirstOptionIncrementId());
            }
            @Test
            void shouldReturnNullWhenFirstOptionIdIsNotNumeric() {//incrementIdが数値以外
            	secondIds[0]="1";
                ItemBean result = Option.checkRegistItemOptionDetail("123", itemBean, "abc", secondIds);
                assertNull(result);
            }
        }

        @Nested
        class WhenSecondOptionId {
            @Test
            void shouldReturnNullWhenSecondOptionIdArrayIsNull() {//incrementIdがnull
                ItemBean result = Option.checkRegistItemOptionDetail("123", itemBean, "1", null);
                assertNotNull(result);
                assertEquals(0, result.getItemSecondOptionIncrementId());
            }

            @Test
            void shouldReturnNullWhenSecondOptionIdIsNotNumeric() {//incrementIdが文字列
            	secondIds[0]="abc";
                ItemBean result = Option.checkRegistItemOptionDetail("123", itemBean, "1", secondIds);
                assertNull(result);
            }
        }

        @Test
        void shouldSetValuesCorrectlyWhenValidInput() {//正常系
        	 secondIds[0]="2";
            ItemBean result = Option.checkRegistItemOptionDetail("123", itemBean, "1", secondIds);
            assertNotNull(result);
            assertEquals(123, result.getItemId());
            assertEquals(1, result.getItemFirstOptionIncrementId());
            assertEquals(2, result.getItemSecondOptionIncrementId());
        }
    }
}
