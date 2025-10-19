package calculator.parser;

public class ParseResult {
    private final String delimiter;
    private final String numbers;

    public ParseResult(String delimiter, String numbers) {
        this.delimiter = delimiter;
        this.numbers = numbers;
    }

    public String getDelimiter() {
        return delimiter;
    }

    public String getNumbers() {
        return numbers;
    }
}
