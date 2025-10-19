package calculator.validator;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("음수 검증기 테스트")
class NegativeNumberValidatorTest {

    private final NegativeNumberValidator validator = new NegativeNumberValidator();

    @Test
    @DisplayName("양수는 검증을 통과한다")
    void validatePositiveNumber() {
        // given
        int number = 5;

        // when & then
        assertThatCode(() -> validator.validate(number))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("0은 검증을 통과한다")
    void validateZero() {
        // given
        int number = 0;

        // when & then
        assertThatCode(() -> validator.validate(number))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("음수는 예외를 발생시킨다")
    void throwExceptionForNegativeNumber() {
        // given
        int number = -1;

        // when & then
        assertThatThrownBy(() -> validator.validate(number))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("음수는 입력할 수 없습니다.");
    }

    @Test
    @DisplayName("큰 음수도 예외를 발생시킨다")
    void throwExceptionForLargeNegativeNumber() {
        // given
        int number = -100;

        // when & then
        assertThatThrownBy(() -> validator.validate(number))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("음수는 입력할 수 없습니다.");
    }

    @Test
    @DisplayName("큰 양수도 검증을 통과한다")
    void validateLargePositiveNumber() {
        // given
        int number = 1000000;

        // when & then
        assertThatCode(() -> validator.validate(number))
                .doesNotThrowAnyException();
    }
}
