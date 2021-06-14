package dataReader;

import calculationResults.CalculatorResults;
import calculator.Calculator;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

public class Main {
    private final Calculator calculator;
    private final DataReader dataReader;
    private final resultsWriter.Main resultsWriter;

    public Main(Calculator calculator, DataReader dataReader, resultsWriter.Main resultsWriter) {
        this.calculator = calculator;
        this.dataReader = dataReader;
        this.resultsWriter = resultsWriter;
    }

    public void startCounting() {
        System.out.println();
        System.out.println("Rozpoczynamy liczenie !!!");
        CalculatorResults calculatorResults = new CalculatorResults();
        calculatorResults.setOperationDate(LocalDateTime.now());
        char actionChoice = dataReader.getOperationChoice();
        //ustawiam w kalkulatorze typ działania
        calculatorResults.setOperationsType(actionChoice);

        char enteringChoice = dataReader.setNumberOfArguments();
        switch (enteringChoice) {
            //działania na 2 parametrach
            case '1': {
                twoArgumentsOperation(calculatorResults);
                break;
            }
            //działania na podanej liczbie argumentów
            case '2': {
                higherNumberOfArgumentsOperation(calculatorResults);
                break;
            }
            // działania na dowolnej ilości parametrów
            case '3': {
                unknownNumberOfArgumentsOperation(calculatorResults);
                break;
            }
            default: {
                System.out.println("Wybrałeś niepoprawną wartość");
            }
        }
        resultsWriter.startWriting(calculatorResults);
    }

    protected void twoArgumentsOperation(CalculatorResults calculatorResults){
        operations(calculatorResults, dataReader.inputArguments(2) );
    }

    protected void higherNumberOfArgumentsOperation(CalculatorResults calculatorResults){
        operations(calculatorResults, dataReader.inputArguments(dataReader.inputNumberWhenMoreThanTwoArguments()));
    }

    protected void unknownNumberOfArgumentsOperation(CalculatorResults calculatorResults){
        operations(calculatorResults, dataReader.inputUnknownNumberOfArguments());
    }

    protected void operations(CalculatorResults calculatorResults, List<Double> argumentsList){
        calculatorResults.setArguments(argumentsList);
        double result = calculator.calculate(calculatorResults);
        System.out.println("Wynik kalkulacji to: " + result);
        calculatorResults.setResult(result);

    }
}

