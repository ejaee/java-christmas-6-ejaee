package christmas.domain.order;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MenuNameTest {


    @DisplayName("메뉴판에 있는 메뉴로 객체를 생성할 수 있다.")
    @Test
    void generateMenuItem() {
        // given
        String name = "타파스";

        // when
        MenuName menuName = MenuName.findByName(name);

        // then
        assertThat(menuName).isNotNull();
    }

    @DisplayName("생성된 객체의 정보를 반환할 수 있다.")
    @Test
    void getMenuInfo() {
        // given
        MenuName menuName = MenuName.findByName("제로콜라");

        // when
        String category = menuName.getCategory();
        String name = menuName.getName();
        long price = menuName.getPrice();

        // then
        assertEquals("음료", category);
        assertEquals("제로콜라", name);
        assertEquals(3_000, price);

    }

    @DisplayName("메뉴판에 없는 메뉴로 객체를 생성하면 예외를 발생한다.")
    @Test
    void throwExceptionIfInvalidMenu() {
        // given
        String name = "침대";

        // when
        // then
        assertThatThrownBy(() -> MenuName.findByName(name))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("유효하지 않은 주문입니다. 다시 입력해 주세요.");
    }


}