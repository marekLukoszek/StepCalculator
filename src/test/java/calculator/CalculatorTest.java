package calculator;

import calculationResults.CalculatorResults;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

public class CalculatorTest {
    @Test
    void testCalculate(){
        CalculatorResults calculatorResults = new CalculatorResults(null, List.of(10.0,5.0), OperationType.ADDITION, 0.0);
        Assertions.assertEquals(15, Calculator.calculate(calculatorResults));

        calculatorResults.setOperationType(OperationType.MULTIPLICATION);
        Assertions.assertEquals(50,Calculator.calculate(calculatorResults));

        calculatorResults.setOperationType(OperationType.SUBTRACTION);
        Assertions.assertEquals(5, Calculator.calculate(calculatorResults));

        calculatorResults.setOperationType(OperationType.DIVISION);
        Assertions.assertEquals(2, Calculator.calculate(calculatorResults));

    }

    @Test
    void testToString(){
        String result = "Data operacji: 2021-06-20T12:00:00.123456 Operacja: 2.0 + 2.0 = 4.0";

        CalculatorResults calculatorResults = new CalculatorResults(LocalDateTime.parse("2021-06-20T12:00:00.123456"),
                List.of(2.0, 2.0), OperationType.ADDITION, 4.0);

        Assertions.assertEquals(result, calculatorResults.toString());

    }
}
