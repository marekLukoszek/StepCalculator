package calculator;

import calculationResults.CalculatorResults;

import java.util.List;

public class Calculator {

    public static Double calculate(CalculatorResults calculatorResults, List<Double> arrayList) {
        Double temp = arrayList.get(0);
        if (calculatorResults.getOperationType().equals(OperationType.ADDITION)) {
            for (int i = 1; i < arrayList.size(); i++) {
                temp += arrayList.get(i);
            }
        } else if (calculatorResults.getOperationType().equals(OperationType.SUBTRACTION)) {
            for (int i = 1; i < arrayList.size(); i++) {
                temp -= arrayList.get(i);
            }
        } else if (calculatorResults.getOperationType().equals(OperationType.MULTIPLICATION)) {
            for (int i = 1; i < arrayList.size(); i++) {
                temp = temp * arrayList.get(i);
            }
        } else if (calculatorResults.getOperationType().equals(OperationType.DIVISION)) {
            for (int i = 1; i < arrayList.size(); i++) {
                temp = temp / arrayList.get(i);
            }
        }
        return temp;
    }
}


