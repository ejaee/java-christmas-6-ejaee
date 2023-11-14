package christmas.utils;

import christmas.view.ErrorMessage;
import org.junit.platform.commons.util.StringUtils;

public class EmptyValidator {

    public static void validateBlank(final String inputValue) {
        if (StringUtils.isBlank(inputValue)) {
            throw new IllegalArgumentException(ErrorMessage.INPUT_BLANK_ERROR_MESSAGE.getMessage());
        }
    }
}
