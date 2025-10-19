package calculator.parser;

public class DefaultDelimiterParser implements DelimiterParser {
    private static final String DEFAULT_DELIMITER = ",|:";

    @Override
    public ParseResult parse(String input) {
        return new ParseResult(DEFAULT_DELIMITER, input);
    }

    @Override
    public boolean canParse(String input) {
        return !input.startsWith("//");
    }
}
