package christmas.utils.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Map;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class KeyValueParserTest {

    @DisplayName("형식에 맞게 입력하면 예외가 발생하지 않는다.")
    @Test
    void parseStringToInt() {
        //given
        String inputValue = "칠면조구이-1,에그타르트-1,초코케이크-2,제로콜라-1";

        //when
        Map<String, Integer> parsedValue = KeyValueParser.parseStringToInt(inputValue);

        //then
        assertEquals(4, parsedValue.size());

        assertTrue(parsedValue.entrySet().stream()
                .allMatch(entry ->
                        entry.getKey().equals("칠면조구이") && entry.getValue().equals(1) ||
                                entry.getKey().equals("에그타르트") && entry.getValue().equals(1) ||
                                entry.getKey().equals("초코케이크") && entry.getValue().equals(2) ||
                                entry.getKey().equals("제로콜라") && entry.getValue().equals(1)
                ));
    }

    @DisplayName("형식에 맞지 않게 입력하면 예외가 발생한다.")
    @ValueSource(strings = {"티본스테이크", "티본스테이크-", "티본스테이크^1", "티본스테이크-1&제로콜라-2"})
    @ParameterizedTest
    void throwExceptionIfIllegalForm(String inputValue) {
        //given

        //when
        //then
        Assertions.assertThatThrownBy(() -> KeyValueParser.parseStringToInt(inputValue))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(KeyValueParser.ILLEGAL_ORDER_EXCEPTION_MESSAGE);
    }

    @DisplayName("key 값이 중복될 경우 예외가 발생한다.")
    @Test
    void throwExceptionIfIllegalKey() {
        //given
        String inputValue = "티본스테이크-1,티본스테이크-2";

        //when
        //then
        Assertions.assertThatThrownBy(() -> KeyValueParser.parseStringToInt(inputValue))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(KeyValueParser.ILLEGAL_ORDER_EXCEPTION_MESSAGE);
    }

}