package resultsWriter;

import calculationResults.CalculatorResults;
import calculationResults.Calculators;
import calculator.OperationType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MainTest {

    @Test
    void updateXml(){
        Main mainMock = mock(Main.class);
        CalculatorResults calculatorResults = new CalculatorResults(LocalDateTime.of(2021, 3,2,12,00,00),
                List.of(2.0,2.0), OperationType.ADDITION, 4.0);
        ArrayList<CalculatorResults> calculatorResultsList = new ArrayList<>();
        calculatorResultsList.add(calculatorResults);
        Calculators calculators = new Calculators();
        calculators.setCalculatorsArrayList(calculatorResultsList);

        when(mainMock.readFromXml()).thenReturn(calculators);
        //mainMock.readFromXml();
        Assertions.assertEquals(1, calculators.getCalculatorsArrayList().size());

    }
}
