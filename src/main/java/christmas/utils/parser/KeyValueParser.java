package christmas.utils.parser;

import christmas.utils.EmptyValidator;

import christmas.view.ErrorMessage;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class KeyValueParser {

    public static final String PAIR_SEPARATOR = ",";
    public static final String KEY_VALUE_SEPARATOR = "-";

    public static Map<String, Long> parseStringToMap(final String inputOrder) {
        EmptyValidator.validateBlank(inputOrder);
        return parse(inputOrder);
    }

    private static Map<String, Long> parse(final String inputOrder) {
        Map<String, Long> parsedMap = new HashMap<>();

        Arrays.stream(inputOrder.split(PAIR_SEPARATOR, -1))
                .map(pair -> pair.split(KEY_VALUE_SEPARATOR, -1))
                .forEach(pair -> processPair(parsedMap, pair));

        return parsedMap;
    }

    private static void processPair(Map<String, Long> parsedMap, String[] pair) {
        String key = pair[0].trim();
        validatePair(parsedMap, pair, key);
        long value = parseLong(pair[1]);
        parsedMap.put(key, value);
    }

    private static void validatePair(Map<String, Long> parsedMap, String[] pair, String key) {
        validatePairLength(pair);
        validateDuplicatedKey(parsedMap, key);
    }

    private static void validateDuplicatedKey(Map<String, Long> parsedMap, String key) {
        if (isDuplicated(parsedMap, key)) {
            throw new IllegalArgumentException(ErrorMessage.MENU_ERROR_MESSAGE.getMessage());
        }
    }

    private static boolean isDuplicated(Map<String, Long> parsedMap, String key) {
        return parsedMap.containsKey(key);
    }

    private static void validatePairLength(String[] pair) {
        if (pair.length < 2) {
            throw new IllegalArgumentException(ErrorMessage.MENU_ERROR_MESSAGE.getMessage());
        }
    }

    private static long parseLong(String value) {
        try {
            return Long.parseLong(value.trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ErrorMessage.MENU_ERROR_MESSAGE.getMessage());
        }
    }
}
