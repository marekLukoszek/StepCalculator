package calculationResults;

import calculator.OperationType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CalculatorResultsTest {
    @Test
    void testSetOperationsType(){
        CalculatorResults calculatorResults = new CalculatorResults(null, null, null, 0);

        calculatorResults.setOperationsType('+');
        Assertions.assertEquals(OperationType.ADDITION, calculatorResults.getOperationType());

        calculatorResults.setOperationsType('-');
        Assertions.assertEquals(OperationType.SUBTRACTION, calculatorResults.getOperationType());

        calculatorResults.setOperationsType('*');
        Assertions.assertEquals(OperationType.MULTIPLICATION, calculatorResults.getOperationType());

        calculatorResults.setOperationsType('/');
        Assertions.assertEquals(OperationType.DIVISION, calculatorResults.getOperationType());
        }

    }

