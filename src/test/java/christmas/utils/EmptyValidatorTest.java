package christmas.utils;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class EmptyValidatorTest {

    @DisplayName("입력 값이 공백이면 예외가 발생한다.")
    @ValueSource(strings = {"", " "})
    @ParameterizedTest
    void throwExceptionIfInputBlank(String inputValue) {
        //given

        //when
        //then
        Assertions.assertThatThrownBy(() -> EmptyValidator.validateBlank(inputValue))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(EmptyValidator.INPUT_BLANK_MESSAGE);
    }

    @DisplayName("입력 값이 null 이면 예외가 발생한다.")
    @Test
    void throwExceptionIfInputNull() {
        //given
        String inputValue = null;

        //when
        //then
        Assertions.assertThatThrownBy(() -> EmptyValidator.validateBlank(inputValue))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(EmptyValidator.INPUT_BLANK_MESSAGE);
    }
}