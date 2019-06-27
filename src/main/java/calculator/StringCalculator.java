package calculator;

import org.apache.commons.lang3.StringUtils;

import java.util.regex.Pattern;
import java.util.stream.Stream;

public class StringCalculator {

    private static final String REGEX_SPLIT_STRING = "[:,]";
    private static final String CUSTOM_SEPARATOR_PREFIX = "//";
    private static final String CUSTOM_SEPARATOR_SUFFIX = "\n";
    private static final String CUSTOM_SEPARATOR_REGEX = "//(.)\\n(.*)";
    private static final String EMPTY_STRING = "";
    private static final String STRING_FORMAT = "%s|%s";
    private static final int ZERO = 0;

    public int splitAndSum(String string) {

        if (StringUtils.isEmpty(string)) {
            return ZERO;
        }

        validate(string);

        return sum(split(string));
    }

    private void validate(String string) {
        if (hasNegativeNumber(string))
            throw new IllegalArgumentException();
    }

    private boolean hasNegativeNumber(String string) {
        return string.contains("-");
    }

    private int sum(String[] string) {
        return Stream.of(string).filter(s -> !s.isEmpty()).mapToInt(Integer::parseInt).sum();
    }

    private String[] split(String string) {
        if (hasCustomSeparator(string)) {
            return string
                    .replace(CUSTOM_SEPARATOR_PREFIX, EMPTY_STRING)
                    .replace(CUSTOM_SEPARATOR_SUFFIX, EMPTY_STRING)
                    .split(String.format(STRING_FORMAT, REGEX_SPLIT_STRING, getCustomSeparator(string)));
        }
        return string.split(REGEX_SPLIT_STRING);
    }

    private String getCustomSeparator(String string) {
        return string.substring(string.indexOf(CUSTOM_SEPARATOR_PREFIX) + 2, string.indexOf(CUSTOM_SEPARATOR_SUFFIX));
    }

    private boolean hasCustomSeparator(String string) {
        return Pattern.compile(CUSTOM_SEPARATOR_REGEX)
                .matcher(string)
                .find();
    }
}
