package christmas.utils.parser;

import christmas.utils.EmptyValidator;
import java.util.function.Function;
import java.util.stream.Stream;

public class NumberParser {

    public static final String ILLEGAL_DATE_EXCEPTION_MESSAGE = "유효하지 않은 날짜입니다. 다시 입력해 주세요.";

    public static int parseStringToInt(final String inputDate) {
        EmptyValidator.validateBlank(inputDate);
        return parse(inputDate, Integer::valueOf);
    }

    private static <R> R parse(final String inputValue, Function<String, R> changeNumber) {
        try {
            return Stream.of(inputValue)
                    .map(String::trim)
                    .map(changeNumber)
                    .findFirst()
                    .orElseThrow(() -> new IllegalArgumentException(ILLEGAL_DATE_EXCEPTION_MESSAGE));
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ILLEGAL_DATE_EXCEPTION_MESSAGE);
        }
    }
}
