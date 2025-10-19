package calculator;

import calculator.service.CalculationService;

public class Calculator {
    private final CalculationService calculationService;

    public Calculator() {
        this.calculationService = new CalculationService();
    }

    public int calculate(String input) {
        return calculationService.calculate(input);
    }
}
