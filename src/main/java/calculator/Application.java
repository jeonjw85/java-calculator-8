package calculator;

import camp.nextstep.edu.missionutils.Console;

public class Application {
    public static void main(String[] args) {
        System.out.println("덧셈할 문자열을 입력해 주세요.");
        String input = Console.readLine();
        int result = calculate(input);
        System.out.println("결과 : " + result);
    }

    private static int calculate(String input) {
        if (input == null || input.isEmpty()) {
            return 0;
        }

        String delimiter = ",|:";
        String numbers = input;

        if (input.startsWith("//")) {
            int delimiterEndIndex = input.indexOf("\\n");
            if (delimiterEndIndex == -1) {
                delimiterEndIndex = input.indexOf("\n");
            }
            if (delimiterEndIndex == -1) {
                throw new IllegalArgumentException("잘못된 입력 형식입니다.");
            }
            boolean isLiteralBackslashN = input.charAt(delimiterEndIndex) == '\\';
            String customDelimiter = input.substring(2, delimiterEndIndex);
            delimiter = ",|:|" + escapeRegex(customDelimiter);
            if (isLiteralBackslashN) {
                numbers = input.substring(delimiterEndIndex + 2);
            } else {
                numbers = input.substring(delimiterEndIndex + 1);
            }
        }

        String[] tokens = numbers.split(delimiter);
        return sum(tokens);
    }

    private static String escapeRegex(String str) {
        return str.replaceAll("([\\\\+*?\\[\\](){}|^$.#])", "\\\\$1");
    }

    private static int sum(String[] tokens) {
        int total = 0;
        for (String token : tokens) {
            if (!token.isEmpty()) {
                int number = parseNumber(token);
                if (number < 0) {
                    throw new IllegalArgumentException("음수는 입력할 수 없습니다.");
                }
                total += number;
            }
        }
        return total;
    }

    private static int parseNumber(String token) {
        try {
            return Integer.parseInt(token);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("숫자가 아닌 값이 입력되었습니다.");
        }
    }
}
