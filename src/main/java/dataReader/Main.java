package dataReader;

import calculationResults.CalculatorResults;
import calculator.Calculator;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void startCounting() {
        System.out.println();
        System.out.println("Rozpoczynamy liczenie !!!");
        DataReader dataReader = new DataReader();
        CalculatorResults calculatorResults = new CalculatorResults();
        calculatorResults.setOperationDate(LocalDateTime.now());
        char actionChoice = dataReader.getOperationChoice();
        //ustawiam w kalkulatorze typ działania
        calculatorResults.setOperationsType(actionChoice);

        char enteringChoice = dataReader.setNumberOfArguments();
        switch (enteringChoice) {
            //działania na 2 parametrach
            case '1': {
                calculatorResults.setArguments(dataReader.inputArguments(2));
                double result = Calculator.calculate(calculatorResults, calculatorResults.getArguments());
                System.out.println("Wynik kalkulacji to: " + result);
                calculatorResults.setResult(result);
                break;
            }
            //działania na podanej liczbie argumentów
            case '2': {
                int argumentsNumber = dataReader.inputNumberWhenMoreThanTwoArguments();
                List<Double> argumentsList = dataReader.inputArguments(argumentsNumber);
                calculatorResults.setArguments(argumentsList);
                double result = Calculator.calculate(calculatorResults, argumentsList);
                System.out.println("Wynik kalkulacji to: " + result);
                calculatorResults.setResult(result);
                break;
            }
            // działania na dowolnej ilości parametrów
            case '3': {
                ArrayList<Double> argumentsList = dataReader.inputUnknownNumberOfArguments();
                calculatorResults.setArguments(argumentsList);
                double result = Calculator.calculate(calculatorResults, argumentsList);
                System.out.println("Wynik kalkulacji to: " + result);
                calculatorResults.setResult(result);
            }

        }
        resultsWriter.Main.startWriting(calculatorResults);
    }
}

