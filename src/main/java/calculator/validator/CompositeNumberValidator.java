package calculator.validator;

import java.util.Arrays;
import java.util.List;

public class CompositeNumberValidator {
    private final List<NumberValidator> validators;

    public CompositeNumberValidator() {
        this.validators = Arrays.asList(
            new NegativeNumberValidator()
        );
    }

    public void validate(int number) {
        validators.forEach(validator -> validator.validate(number));
    }
}
