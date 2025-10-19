package calculator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("계산기 테스트 (Facade)")
class CalculatorTest {

    private Calculator calculator;

    @BeforeEach
    void setUp() {
        calculator = new Calculator();
    }

    @Test
    @DisplayName("빈 문자열은 0을 반환한다")
    void calculateEmptyString() {
        // given
        String input = "";

        // when
        int result = calculator.calculate(input);

        // then
        assertThat(result).isEqualTo(0);
    }

    @Test
    @DisplayName("null은 0을 반환한다")
    void calculateNull() {
        // given
        String input = null;

        // when
        int result = calculator.calculate(input);

        // then
        assertThat(result).isEqualTo(0);
    }

    @Test
    @DisplayName("기본 구분자로 숫자를 더한다")
    void calculateWithDefaultDelimiters() {
        // given
        String input = "1,2:3";

        // when
        int result = calculator.calculate(input);

        // then
        assertThat(result).isEqualTo(6);
    }

    @Test
    @DisplayName("커스텀 구분자로 숫자를 더한다")
    void calculateWithCustomDelimiter() {
        // given
        String input = "//;\\n1;2;3";

        // when
        int result = calculator.calculate(input);

        // then
        assertThat(result).isEqualTo(6);
    }

    @Test
    @DisplayName("음수 입력 시 예외를 발생시킨다")
    void throwExceptionForNegativeNumber() {
        // given
        String input = "-1,2,3";

        // when & then
        assertThatThrownBy(() -> calculator.calculate(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("음수는 입력할 수 없습니다.");
    }

    @Test
    @DisplayName("숫자가 아닌 값 입력 시 예외를 발생시킨다")
    void throwExceptionForNonNumericValue() {
        // given
        String input = "1,a,3";

        // when & then
        assertThatThrownBy(() -> calculator.calculate(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("숫자가 아닌 값이 입력되었습니다.");
    }

    @Test
    @DisplayName("복잡한 입력도 올바르게 계산한다")
    void calculateComplexInput() {
        // given
        String input = "//;\\n1;2,3:4";

        // when
        int result = calculator.calculate(input);

        // then
        assertThat(result).isEqualTo(10);
    }
}
