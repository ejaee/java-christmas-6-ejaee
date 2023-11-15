package christmas.utils.parser;

import christmas.utils.EmptyValidator;
import christmas.view.ErrorMessage;
import java.util.function.Function;
import java.util.stream.Stream;

public class NumberParser {

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
                    .orElseThrow(
                            () -> new IllegalArgumentException(ErrorMessage.INPUT_DATE_ERROR_MESSAGE.getMessage()));
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ErrorMessage.INPUT_DATE_ERROR_MESSAGE.getMessage());
        }
    }
}
