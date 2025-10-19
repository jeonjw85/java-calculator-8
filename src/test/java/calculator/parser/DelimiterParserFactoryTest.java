package calculator.parser;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("구분자 파서 팩토리 테스트")
class DelimiterParserFactoryTest {

    private final DelimiterParserFactory factory = new DelimiterParserFactory();

    @Test
    @DisplayName("기본 구분자 입력에 대해 DefaultDelimiterParser를 반환한다")
    void getDefaultParserForBasicInput() {
        // given
        String input = "1,2:3";

        // when
        DelimiterParser parser = factory.getParser(input);

        // then
        assertThat(parser).isInstanceOf(DefaultDelimiterParser.class);
    }

    @Test
    @DisplayName("커스텀 구분자 입력에 대해 CustomDelimiterParser를 반환한다")
    void getCustomParserForCustomDelimiterInput() {
        // given
        String input = "//;\\n1;2;3";

        // when
        DelimiterParser parser = factory.getParser(input);

        // then
        assertThat(parser).isInstanceOf(CustomDelimiterParser.class);
    }

    @Test
    @DisplayName("빈 문자열에 대해 DefaultDelimiterParser를 반환한다")
    void getDefaultParserForEmptyString() {
        // given
        String input = "";

        // when
        DelimiterParser parser = factory.getParser(input);

        // then
        assertThat(parser).isInstanceOf(DefaultDelimiterParser.class);
    }

    @Test
    @DisplayName("숫자만 있는 입력에 대해 DefaultDelimiterParser를 반환한다")
    void getDefaultParserForNumberOnly() {
        // given
        String input = "123";

        // when
        DelimiterParser parser = factory.getParser(input);

        // then
        assertThat(parser).isInstanceOf(DefaultDelimiterParser.class);
    }

    @Test
    @DisplayName("반환된 파서는 해당 입력을 파싱할 수 있다")
    void returnedParserCanParseInput() {
        // given
        String defaultInput = "1,2:3";
        String customInput = "//;\\n1;2;3";

        // when
        DelimiterParser defaultParser = factory.getParser(defaultInput);
        DelimiterParser customParser = factory.getParser(customInput);

        // then
        assertThat(defaultParser.canParse(defaultInput)).isTrue();
        assertThat(customParser.canParse(customInput)).isTrue();
    }
}
