package calculator;

import camp.nextstep.edu.missionutils.Console;

public class CalculatorView {
    private static final String INPUT_PROMPT = "덧셈할 문자열을 입력해 주세요.";
    private static final String RESULT_FORMAT = "결과 : %d";

    public void printInputPrompt() {
        System.out.println(INPUT_PROMPT);
    }

    public String readInput() {
        return Console.readLine();
    }

    public void printResult(int result) {
        System.out.println(String.format(RESULT_FORMAT, result));
    }

    public void printError(String message) {
        System.out.println(message);
    }
}
