package resultsWriter;

import calculationResults.CalculatorResults;
import calculationResults.Calculators;
import calculator.OperationType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.internal.verification.Times;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

public class MainTest {

    @Test
    void updateXml() {
        Main main = spy(new Main(null));

        CalculatorResults calculatorResults = new CalculatorResults(LocalDateTime.of(2021, 3, 2, 12, 0, 0),
                List.of(2.0, 2.0), OperationType.ADDITION, 4.0);
        ArrayList<CalculatorResults> calculatorResultsList = new ArrayList<>();
        calculatorResultsList.add(calculatorResults);
        Calculators calculators = new Calculators();
        calculators.setCalculatorsArrayList(calculatorResultsList);

        CalculatorResults bufferedCalculators = new CalculatorResults(LocalDateTime.of(2020, 3, 2, 12, 0, 0),
                List.of(3.0, 3.0), OperationType.ADDITION, 6.0);

        main.addElement(bufferedCalculators);

        Calculators expectedResult = new Calculators();
        expectedResult.setCalculatorsArrayList(new ArrayList<>(List.of(bufferedCalculators, calculatorResults)));

        when(main.readFromXml()).thenReturn(calculators);

        main.updateXml();
        verify(main, new Times(1)).writeResultsToXML(expectedResult);

    }

    @Test
    void updateXmlElseOption() {

        Main main = spy(new Main(null));

        CalculatorResults bufferedCalculators = new CalculatorResults(LocalDateTime.of(2020, 3, 2, 12, 0, 0),
                List.of(3.0, 3.0), OperationType.ADDITION, 6.0);

        main.addElement(bufferedCalculators);

        Calculators expectedResult = new Calculators();
        expectedResult.setCalculatorsArrayList(new ArrayList<>(List.of(bufferedCalculators)));

        when(main.readFromXml()).thenReturn(null);

        main.updateXml();
        verify(main, new Times(1)).writeResultsToXML(expectedResult);

    }

    @Test
    void updateLogFile(){
        RecordLog recordLogMock = mock(RecordLog.class);
        Main main = spy(new Main(recordLogMock));
        List<String> testList = List.of("abc", "def", "ghi");
        when(recordLogMock.readLogFile(anyString())).thenReturn(testList);

        List<String> testList2 = List.of("xxx", "yyy", "zzz");
        when(recordLogMock.updateLogFileLinesArray(anyList(), eq(testList))).thenReturn(testList2);

        main.updateLogFile();

        verify(recordLogMock, new Times(1)).logFileExistCheck(anyString());
        verify(recordLogMock, new Times(1)).updateLogFileLinesArray(anyList(), eq(testList));
        verify(recordLogMock, new Times(1)).writeNewLogFile(eq(testList2), anyString());

    }
    @Test
    void updateFiles(){
        Main mainMock = mock(Main.class);

        doCallRealMethod().when(mainMock).updateFiles();

        mainMock.updateFiles();
        verify(mainMock, new Times(1)).updateLogFile();
        verify(mainMock, new Times(1)).updateXml();

    }
}
