package christmas.domain.order;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import christmas.view.ErrorMessage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MenuCountTest {

    @DisplayName("메뉴 개수를 통해 객체를 생성할 수 있다.")
    @Test
    void generateMenuItem() {
        // given
        long count = 30;

        // when
        MenuCount menuCount = MenuCount.of(count);

        // then
        assertThat(menuCount).isNotNull();
    }

    @DisplayName("0 이하의 메뉴 개수로 객체를 생성하면 예외를 발생한다.")
    @Test
    void throwExceptionIfInvalidMenu() {
        // given
        long count = 0;

        // when
        // then
        assertThatThrownBy(() -> MenuCount.of(count))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.MENU_ERROR_MESSAGE.getMessage());
    }
}