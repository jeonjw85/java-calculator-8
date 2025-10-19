package calculator;

public class CalculatorController {
    private final Calculator calculator;
    private final CalculatorView view;

    public CalculatorController() {
        this.calculator = new Calculator();
        this.view = new CalculatorView();
    }

    public CalculatorController(Calculator calculator, CalculatorView view) {
        this.calculator = calculator;
        this.view = view;
    }

    public void run() {
        try {
            String input = getUserInput();
            int result = processCalculation(input);
            displayResult(result);
        } catch (IllegalArgumentException e) {
            handleError(e);
        }
    }

    private String getUserInput() {
        view.printInputPrompt();
        return view.readInput();
    }

    private int processCalculation(String input) {
        return calculator.calculate(input);
    }

    private void displayResult(int result) {
        view.printResult(result);
    }

    private void handleError(IllegalArgumentException e) {
        throw e;
    }
}
