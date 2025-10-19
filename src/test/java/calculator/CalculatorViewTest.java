package calculator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("계산기 뷰 테스트")
class CalculatorViewTest {

    private CalculatorView view;
    private ByteArrayOutputStream outputStream;

    @BeforeEach
    void setUp() {
        view = new CalculatorView();
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
    }

    @Test
    @DisplayName("입력 프롬프트를 출력한다")
    void printInputPrompt() {
        // when
        view.printInputPrompt();

        // then
        String output = outputStream.toString();
        assertThat(output).contains("덧셈할 문자열을 입력해 주세요.");
    }

    @Test
    @DisplayName("결과를 올바른 형식으로 출력한다")
    void printResult() {
        // given
        int result = 6;

        // when
        view.printResult(result);

        // then
        String output = outputStream.toString();
        assertThat(output).contains("결과 : 6");
    }

    @Test
    @DisplayName("0을 결과로 출력할 수 있다")
    void printZeroResult() {
        // given
        int result = 0;

        // when
        view.printResult(result);

        // then
        String output = outputStream.toString();
        assertThat(output).contains("결과 : 0");
    }

    @Test
    @DisplayName("큰 숫자를 결과로 출력할 수 있다")
    void printLargeResult() {
        // given
        int result = 123456;

        // when
        view.printResult(result);

        // then
        String output = outputStream.toString();
        assertThat(output).contains("결과 : 123456");
    }

    @Test
    @DisplayName("에러 메시지를 출력할 수 있다")
    void printError() {
        // given
        String errorMessage = "오류가 발생했습니다.";

        // when
        view.printError(errorMessage);

        // then
        String output = outputStream.toString();
        assertThat(output).contains(errorMessage);
    }
}
