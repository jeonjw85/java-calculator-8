package calculator.parser;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("기본 구분자 파서 테스트")
class DefaultDelimiterParserTest {

    private final DefaultDelimiterParser parser = new DefaultDelimiterParser();

    @Test
    @DisplayName("기본 구분자로 시작하지 않는 입력을 파싱할 수 있다")
    void canParseDefaultInput() {
        // given
        String input = "1,2:3";

        // when
        boolean result = parser.canParse(input);

        // then
        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("커스텀 구분자로 시작하는 입력은 파싱할 수 없다")
    void cannotParseCustomDelimiterInput() {
        // given
        String input = "//;\\n1;2;3";

        // when
        boolean result = parser.canParse(input);

        // then
        assertThat(result).isFalse();
    }

    @Test
    @DisplayName("쉼표와 콜론을 구분자로 파싱한다")
    void parseWithDefaultDelimiters() {
        // given
        String input = "1,2:3";

        // when
        ParseResult result = parser.parse(input);

        // then
        assertThat(result.getDelimiter()).isEqualTo(",|:");
        assertThat(result.getNumbers()).isEqualTo("1,2:3");
    }

    @Test
    @DisplayName("빈 문자열도 파싱할 수 있다")
    void parseEmptyString() {
        // given
        String input = "";

        // when
        ParseResult result = parser.parse(input);

        // then
        assertThat(result.getDelimiter()).isEqualTo(",|:");
        assertThat(result.getNumbers()).isEqualTo("");
    }

    @Test
    @DisplayName("숫자만 있는 입력도 파싱할 수 있다")
    void parseSingleNumber() {
        // given
        String input = "5";

        // when
        ParseResult result = parser.parse(input);

        // then
        assertThat(result.getDelimiter()).isEqualTo(",|:");
        assertThat(result.getNumbers()).isEqualTo("5");
    }
}
