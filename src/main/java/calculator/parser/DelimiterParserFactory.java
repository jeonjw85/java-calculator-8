package calculator.parser;

import java.util.Arrays;
import java.util.List;

public class DelimiterParserFactory {
    private final List<DelimiterParser> parsers;

    public DelimiterParserFactory() {
        this.parsers = Arrays.asList(
            new CustomDelimiterParser(),
            new DefaultDelimiterParser()
        );
    }

    public DelimiterParser getParser(String input) {
        return parsers.stream()
            .filter(parser -> parser.canParse(input))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("적절한 파서를 찾을 수 없습니다."));
    }
}
