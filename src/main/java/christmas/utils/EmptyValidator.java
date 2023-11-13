package christmas.utils;

import org.junit.platform.commons.util.StringUtils;

public class EmptyValidator {

    public static final String INPUT_BLANK_MESSAGE = "입력 값이 비어있습니다. 다시 입력해 주세요.";

    public static void validateBlank(final String inputValue) {
        if (StringUtils.isBlank(inputValue)) {
            throw new IllegalArgumentException(INPUT_BLANK_MESSAGE);
        }
    }
}
