package calculator.service;

import calculator.parser.DelimiterParser;
import calculator.parser.DelimiterParserFactory;
import calculator.parser.ParseResult;
import calculator.validator.CompositeNumberValidator;

public class CalculationService {
    private final DelimiterParserFactory parserFactory;
    private final CompositeNumberValidator validator;

    public CalculationService() {
        this.parserFactory = new DelimiterParserFactory();
        this.validator = new CompositeNumberValidator();
    }

    public int calculate(String input) {
        if (isNullOrEmpty(input)) {
            return 0;
        }

        DelimiterParser parser = parserFactory.getParser(input);
        ParseResult parseResult = parser.parse(input);
        
        String[] tokens = parseResult.getNumbers().split(parseResult.getDelimiter());
        return sum(tokens);
    }

    private boolean isNullOrEmpty(String input) {
        return input == null || input.isEmpty();
    }

    private int sum(String[] tokens) {
        int total = 0;
        for (String token : tokens) {
            if (!token.isEmpty()) {
                int number = parseNumber(token);
                validator.validate(number);
                total += number;
            }
        }
        return total;
    }

    private int parseNumber(String token) {
        try {
            return Integer.parseInt(token);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("숫자가 아닌 값이 입력되었습니다.");
        }
    }
}
