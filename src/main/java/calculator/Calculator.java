package calculator;

import calculationResults.CalculatorResults;

import java.util.List;

public class Calculator {

    public Double calculate(CalculatorResults calculatorResults) {
        List<Double> argumentsList = calculatorResults.getArguments();
        Double temp = argumentsList.get(0);
        if (calculatorResults.getOperationType().equals(OperationType.ADDITION)) {
            for (int i = 1; i < argumentsList.size(); i++) {
                temp += argumentsList.get(i);
            }
        } else if (calculatorResults.getOperationType().equals(OperationType.SUBTRACTION)) {
            for (int i = 1; i < argumentsList.size(); i++) {
                temp -= argumentsList.get(i);
            }
        } else if (calculatorResults.getOperationType().equals(OperationType.MULTIPLICATION)) {
            for (int i = 1; i < argumentsList.size(); i++) {
                temp = temp * argumentsList.get(i);
            }
        } else if (calculatorResults.getOperationType().equals(OperationType.DIVISION)) {
            for (int i = 1; i < argumentsList.size(); i++) {
                temp = temp / argumentsList.get(i);
            }
        }
        return temp;
    }
}


