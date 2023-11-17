package christmas.constant;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GiftTest {

    @DisplayName("할인 전 총주문 금액을 기준으로 증정 이벤트를 얻는다.")
    @Test
    void getGiveaway() {
        // given
        Gift gift = Gift.getGift(120_000);
        Gift noneGift = Gift.getGift(119_000);

        // when
        // then
        assertEquals(Gift.CHAMPAGNE, gift);
        assertEquals(Gift.NONE, noneGift);
    }

    @DisplayName("이벤트 이름을 통해 해당되는 증정 이벤트를 반환한다.")
    @Test
    void findByEventName() {
        // given
        Gift gift = Gift.findByEventName("증정 이벤트");

        // when
        // then
        assertEquals(Gift.CHAMPAGNE, gift);
    }

    @DisplayName("증정 이벤트의 값을 반환한다.")
    @Test
    void getGiftInfo() {
        // given
        Gift gift = Gift.findByEventName("증정 이벤트");

        // when
        String giveaway = gift.getGiveaway();
        long profits = gift.getProfits();

        // then
        assertEquals("샴페인 1개", giveaway);
        assertEquals(25_000, profits);
    }
}