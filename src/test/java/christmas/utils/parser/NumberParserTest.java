package christmas.utils.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;

import christmas.view.ErrorMessage;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class NumberParserTest {

    @DisplayName("방문 예정일 입력으로 숫자를 입력하면 예외가 발생하지 않는다.")
    @Test
    void parseNumberInputValue() {
        //given
        String inputValue = "1";

        //when
        int number = NumberParser.parseStringToInt(inputValue);

        //then
        assertEquals(number, 1);
    }

    @DisplayName("방문 예정일 입력으로 공백이 포함된 숫자를 입력하면 예외가 발생하지 않는다.")
    @Test
    void parseNumberInputValueWithSpaces() {
        //given
        String inputValue = "   1   ";

        //when
        int number = NumberParser.parseStringToInt(inputValue);

        //then
        assertEquals(number, 1);
    }

    @DisplayName("방문 예정일 입력으로 숫자를 입력하지 않으면 예외가 발생한다.")
    @ValueSource(strings = {"one", "@", "하나"})
    @ParameterizedTest
    void throwExceptionIfNotNumberInputValue(String inputValue) {
        //given

        //when
        //then
        Assertions.assertThatThrownBy(() -> NumberParser.parseStringToInt(inputValue))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.INPUT_DATE_ERROR_MESSAGE.getMessage());
    }
}