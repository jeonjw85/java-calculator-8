package calculator.parser;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("파싱 결과 테스트")
class ParseResultTest {

    @Test
    @DisplayName("구분자와 숫자 문자열을 저장한다")
    void storeDelimiterAndNumbers() {
        // given
        String delimiter = ",|:";
        String numbers = "1,2:3";

        // when
        ParseResult result = new ParseResult(delimiter, numbers);

        // then
        assertThat(result.getDelimiter()).isEqualTo(delimiter);
        assertThat(result.getNumbers()).isEqualTo(numbers);
    }

    @Test
    @DisplayName("불변 객체로 동작한다")
    void immutableObject() {
        // given
        String delimiter = ",|:";
        String numbers = "1,2:3";

        // when
        ParseResult result = new ParseResult(delimiter, numbers);

        // then
        assertThat(result.getDelimiter()).isEqualTo(delimiter);
        assertThat(result.getNumbers()).isEqualTo(numbers);
        // getter로만 접근 가능하고 setter가 없음
    }

    @Test
    @DisplayName("빈 문자열도 저장할 수 있다")
    void storeEmptyStrings() {
        // given
        String delimiter = "";
        String numbers = "";

        // when
        ParseResult result = new ParseResult(delimiter, numbers);

        // then
        assertThat(result.getDelimiter()).isEmpty();
        assertThat(result.getNumbers()).isEmpty();
    }
}
