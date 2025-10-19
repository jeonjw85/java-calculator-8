package calculator.validator;

public class NegativeNumberValidator extends NumberValidator {
    
    @Override
    protected boolean isValid(int number) {
        return number >= 0;
    }

    @Override
    protected String getErrorMessage() {
        return "음수는 입력할 수 없습니다.";
    }
}
