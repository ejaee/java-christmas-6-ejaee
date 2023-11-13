package christmas.utils.parser;

import christmas.utils.EmptyValidator;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class KeyValueParser {

    public static final String ILLEGAL_ORDER_EXCEPTION_MESSAGE = "유효하지 않은 주문입니다. 다시 입력해 주세요.";
    public static final String PAIR_SEPARATOR = ",";
    public static final String KEY_VALUE_SEPARATOR = "-";

    public static Map<String, Integer> parseStringToInt(final String inputOrder) {
        EmptyValidator.validateBlank(inputOrder);
        return parse(inputOrder);
    }

    private static Map<String, Integer> parse(final String inputOrder) {
        Map<String, Integer> parsedMap = new HashMap<>();

        Arrays.stream(inputOrder.split(PAIR_SEPARATOR, -1))
                .map(pair -> pair.split(KEY_VALUE_SEPARATOR, -1))
                .forEach(pair -> processPair(parsedMap, pair));

        return parsedMap;
    }

    private static void processPair(Map<String, Integer> parsedMap, String[] pair) {
        String key = pair[0].trim();
        validatePair(parsedMap, pair, key);
        int value = parseInt(pair[1]);
        parsedMap.put(key, value);
    }

    private static void validatePair(Map<String, Integer> parsedMap, String[] pair, String key) {
        validatePairLength(pair);
        validateDuplicatedKey(parsedMap, key);
    }

    private static void validateDuplicatedKey(Map<String, Integer> parsedMap, String key) {
        if (isDuplicated(parsedMap, key)) {
            throw new IllegalArgumentException(ILLEGAL_ORDER_EXCEPTION_MESSAGE);
        }
    }

    private static boolean isDuplicated(Map<String, Integer> parsedMap, String key) {
        return parsedMap.containsKey(key);
    }

    private static void validatePairLength(String[] pair) {
        if (pair.length < 2) {
            throw new IllegalArgumentException(ILLEGAL_ORDER_EXCEPTION_MESSAGE);
        }
    }

    private static int parseInt(String value) {
        try {
            return Integer.parseInt(value.trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ILLEGAL_ORDER_EXCEPTION_MESSAGE);
        }
    }
}
