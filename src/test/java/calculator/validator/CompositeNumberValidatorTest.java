package calculator.validator;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("복합 검증기 테스트")
class CompositeNumberValidatorTest {

    private final CompositeNumberValidator validator = new CompositeNumberValidator();

    @Test
    @DisplayName("양수는 모든 검증을 통과한다")
    void validatePositiveNumber() {
        // given
        int number = 10;

        // when & then
        assertThatCode(() -> validator.validate(number))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("0은 모든 검증을 통과한다")
    void validateZero() {
        // given
        int number = 0;

        // when & then
        assertThatCode(() -> validator.validate(number))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("음수는 검증에 실패한다")
    void throwExceptionForNegativeNumber() {
        // given
        int number = -5;

        // when & then
        assertThatThrownBy(() -> validator.validate(number))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("음수는 입력할 수 없습니다.");
    }

    @Test
    @DisplayName("여러 양수를 연속으로 검증할 수 있다")
    void validateMultiplePositiveNumbers() {
        // given
        int[] numbers = {1, 2, 3, 4, 5};

        // when & then
        for (int number : numbers) {
            assertThatCode(() -> validator.validate(number))
                    .doesNotThrowAnyException();
        }
    }

    @Test
    @DisplayName("큰 숫자도 검증을 통과한다")
    void validateLargeNumber() {
        // given
        int number = Integer.MAX_VALUE;

        // when & then
        assertThatCode(() -> validator.validate(number))
                .doesNotThrowAnyException();
    }
}
