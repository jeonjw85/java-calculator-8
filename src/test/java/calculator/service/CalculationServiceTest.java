package calculator.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("계산 서비스 테스트")
class CalculationServiceTest {

    private final CalculationService service = new CalculationService();

    @Test
    @DisplayName("빈 문자열은 0을 반환한다")
    void calculateEmptyString() {
        // given
        String input = "";

        // when
        int result = service.calculate(input);

        // then
        assertThat(result).isEqualTo(0);
    }

    @Test
    @DisplayName("null은 0을 반환한다")
    void calculateNull() {
        // given
        String input = null;

        // when
        int result = service.calculate(input);

        // then
        assertThat(result).isEqualTo(0);
    }

    @Test
    @DisplayName("숫자 하나는 그 숫자를 반환한다")
    void calculateSingleNumber() {
        // given
        String input = "5";

        // when
        int result = service.calculate(input);

        // then
        assertThat(result).isEqualTo(5);
    }

    @Test
    @DisplayName("쉼표 구분자로 숫자를 더한다")
    void calculateWithCommaDelimiter() {
        // given
        String input = "1,2,3";

        // when
        int result = service.calculate(input);

        // then
        assertThat(result).isEqualTo(6);
    }

    @Test
    @DisplayName("콜론 구분자로 숫자를 더한다")
    void calculateWithColonDelimiter() {
        // given
        String input = "1:2:3";

        // when
        int result = service.calculate(input);

        // then
        assertThat(result).isEqualTo(6);
    }

    @Test
    @DisplayName("쉼표와 콜론을 함께 사용할 수 있다")
    void calculateWithMixedDelimiters() {
        // given
        String input = "1,2:3";

        // when
        int result = service.calculate(input);

        // then
        assertThat(result).isEqualTo(6);
    }

    @Test
    @DisplayName("커스텀 구분자로 숫자를 더한다")
    void calculateWithCustomDelimiter() {
        // given
        String input = "//;\\n1;2;3";

        // when
        int result = service.calculate(input);

        // then
        assertThat(result).isEqualTo(6);
    }

    @Test
    @DisplayName("커스텀 구분자와 기본 구분자를 함께 사용할 수 있다")
    void calculateWithCustomAndDefaultDelimiters() {
        // given
        String input = "//;\\n1;2,3:4";

        // when
        int result = service.calculate(input);

        // then
        assertThat(result).isEqualTo(10);
    }

    @Test
    @DisplayName("음수는 예외를 발생시킨다")
    void throwExceptionForNegativeNumber() {
        // given
        String input = "-1,2,3";

        // when & then
        assertThatThrownBy(() -> service.calculate(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("음수는 입력할 수 없습니다.");
    }

    @Test
    @DisplayName("숫자가 아닌 값은 예외를 발생시킨다")
    void throwExceptionForNonNumericValue() {
        // given
        String input = "1,a,3";

        // when & then
        assertThatThrownBy(() -> service.calculate(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("숫자가 아닌 값이 입력되었습니다.");
    }

    @Test
    @DisplayName("실제 줄바꿈 문자를 사용한 커스텀 구분자로 계산한다")
    void calculateWithActualNewlineDelimiter() {
        // given
        String input = "//;\n1;2;3";

        // when
        int result = service.calculate(input);

        // then
        assertThat(result).isEqualTo(6);
    }

    @Test
    @DisplayName("특수문자를 커스텀 구분자로 사용할 수 있다")
    void calculateWithSpecialCharacterDelimiter() {
        // given
        String input = "//*\\n1*2*3";

        // when
        int result = service.calculate(input);

        // then
        assertThat(result).isEqualTo(6);
    }

    @Test
    @DisplayName("빈 토큰은 무시한다")
    void ignoreEmptyTokens() {
        // given
        String input = "1,,2,:3";

        // when
        int result = service.calculate(input);

        // then
        assertThat(result).isEqualTo(6);
    }

    @Test
    @DisplayName("큰 숫자들을 더할 수 있다")
    void calculateLargeNumbers() {
        // given
        String input = "1000,2000,3000";

        // when
        int result = service.calculate(input);

        // then
        assertThat(result).isEqualTo(6000);
    }

    @Test
    @DisplayName("0들만 있는 경우 0을 반환한다")
    void calculateAllZeros() {
        // given
        String input = "0,0,0";

        // when
        int result = service.calculate(input);

        // then
        assertThat(result).isEqualTo(0);
    }
}
