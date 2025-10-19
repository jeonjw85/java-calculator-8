package calculator.validator;

public abstract class NumberValidator {
    
    public final void validate(int number) {
        if (!isValid(number)) {
            throw new IllegalArgumentException(getErrorMessage());
        }
    }

    protected abstract boolean isValid(int number);
    protected abstract String getErrorMessage();
}
