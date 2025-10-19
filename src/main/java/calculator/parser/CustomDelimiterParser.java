package calculator.parser;

public class CustomDelimiterParser implements DelimiterParser {
    private static final String DEFAULT_DELIMITER = ",|:";

    @Override
    public ParseResult parse(String input) {
        int delimiterEndIndex = findDelimiterEndIndex(input);
        validateDelimiterFormat(delimiterEndIndex);
        
        boolean isLiteralBackslashN = input.charAt(delimiterEndIndex) == '\\';
        String customDelimiter = extractCustomDelimiter(input, delimiterEndIndex);
        String numbers = extractNumbers(input, delimiterEndIndex, isLiteralBackslashN);
        
        String delimiter = DEFAULT_DELIMITER + "|" + escapeRegex(customDelimiter);
        return new ParseResult(delimiter, numbers);
    }

    @Override
    public boolean canParse(String input) {
        return input.startsWith("//");
    }

    private int findDelimiterEndIndex(String input) {
        int index = input.indexOf("\\n");
        if (index == -1) {
            index = input.indexOf("\n");
        }
        return index;
    }

    private void validateDelimiterFormat(int delimiterEndIndex) {
        if (delimiterEndIndex == -1) {
            throw new IllegalArgumentException("잘못된 입력 형식입니다.");
        }
    }

    private String extractCustomDelimiter(String input, int delimiterEndIndex) {
        return input.substring(2, delimiterEndIndex);
    }

    private String extractNumbers(String input, int delimiterEndIndex, boolean isLiteralBackslashN) {
        if (isLiteralBackslashN) {
            return input.substring(delimiterEndIndex + 2);
        }
        return input.substring(delimiterEndIndex + 1);
    }

    private String escapeRegex(String str) {
        return str.replaceAll("([\\\\+*?\\[\\](){}|^$.#])", "\\\\$1");
    }
}
