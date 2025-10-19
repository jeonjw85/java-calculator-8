package calculator.parser;

public interface DelimiterParser {
    ParseResult parse(String input);
    boolean canParse(String input);
}
