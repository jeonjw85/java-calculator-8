package calculator.parser;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("커스텀 구분자 파서 테스트")
class CustomDelimiterParserTest {

    private final CustomDelimiterParser parser = new CustomDelimiterParser();

    @Test
    @DisplayName("커스텀 구분자로 시작하는 입력을 파싱할 수 있다")
    void canParseCustomDelimiterInput() {
        // given
        String input = "//;\\n1;2;3";

        // when
        boolean result = parser.canParse(input);

        // then
        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("기본 구분자 입력은 파싱할 수 없다")
    void cannotParseDefaultInput() {
        // given
        String input = "1,2:3";

        // when
        boolean result = parser.canParse(input);

        // then
        assertThat(result).isFalse();
    }

    @Test
    @DisplayName("세미콜론을 커스텀 구분자로 파싱한다")
    void parseWithSemicolonDelimiter() {
        // given
        String input = "//;\\n1;2;3";

        // when
        ParseResult result = parser.parse(input);

        // then
        assertThat(result.getDelimiter()).contains(";");
        assertThat(result.getNumbers()).isEqualTo("1;2;3");
    }

    @Test
    @DisplayName("실제 줄바꿈 문자를 사용한 커스텀 구분자를 파싱한다")
    void parseWithActualNewline() {
        // given
        String input = "//;\n1;2;3";

        // when
        ParseResult result = parser.parse(input);

        // then
        assertThat(result.getDelimiter()).contains(";");
        assertThat(result.getNumbers()).isEqualTo("1;2;3");
    }

    @Test
    @DisplayName("특수문자를 커스텀 구분자로 파싱한다")
    void parseWithSpecialCharacterDelimiter() {
        // given
        String input = "//*\\n1*2*3";

        // when
        ParseResult result = parser.parse(input);

        // then
        assertThat(result.getDelimiter()).contains("\\*");
        assertThat(result.getNumbers()).isEqualTo("1*2*3");
    }

    @Test
    @DisplayName("잘못된 형식의 입력은 예외를 발생시킨다")
    void throwExceptionForInvalidFormat() {
        // given
        String input = "//;";

        // when & then
        assertThatThrownBy(() -> parser.parse(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("잘못된 입력 형식입니다.");
    }

    @Test
    @DisplayName("구분자만 있고 숫자가 없는 경우도 파싱할 수 있다")
    void parseWithDelimiterOnly() {
        // given
        String input = "//;\\n";

        // when
        ParseResult result = parser.parse(input);

        // then
        assertThat(result.getDelimiter()).contains(";");
        assertThat(result.getNumbers()).isEqualTo("");
    }

    @Test
    @DisplayName("여러 문자로 된 커스텀 구분자를 파싱한다")
    void parseWithMultiCharacterDelimiter() {
        // given
        String input = "//:::\\n1:::2:::3";

        // when
        ParseResult result = parser.parse(input);

        // then
        assertThat(result.getDelimiter()).contains(":::");
        assertThat(result.getNumbers()).isEqualTo("1:::2:::3");
    }
}
