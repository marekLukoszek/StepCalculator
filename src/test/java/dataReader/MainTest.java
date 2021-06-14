package dataReader;

import calculationResults.CalculatorResults;
import calculator.Calculator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.mockito.internal.verification.Times;

import javax.xml.crypto.Data;
import java.time.LocalDateTime;
import java.util.List;

import static calculator.OperationType.ADDITION;
import static org.mockito.Mockito.*;

public class MainTest {

    @Test
    void operationsTest(){
        DataReader dataReaderMock = mock(DataReader.class);
        List<Double> argumentsList = List.of(2.0,2.0);
        when(dataReaderMock.inputUnknownNumberOfArguments()).thenReturn(argumentsList);

        CalculatorResults calculatorResults = new CalculatorResults();
        calculatorResults.setOperationsType('+');
        Calculator calculatorMock = mock(Calculator.class);
        when(calculatorMock.calculate(calculatorResults)).thenReturn(4.0);

        Main main = new Main(calculatorMock, dataReaderMock, null);

        main.operations(calculatorResults, argumentsList);
        Assertions.assertEquals(4, calculatorResults.getResult());
        Assertions.assertEquals(argumentsList, calculatorResults.getArguments());
        verify(calculatorMock, new Times(1)).calculate(calculatorResults);
    }

    @Test
    void unknownNumberOfArgumentsOperation(){
        DataReader dataReaderMock = mock(DataReader.class);
        List<Double> argumentsList = List.of(1.0, 2.0, 3.0, 4.0);
        when(dataReaderMock.inputUnknownNumberOfArguments()).thenReturn(argumentsList);


        CalculatorResults calculatorResults = new CalculatorResults();
        Main main = spy(new Main(null, dataReaderMock, null));
        doNothing().when(main).operations(calculatorResults, argumentsList);
        main.unknownNumberOfArgumentsOperation(calculatorResults);
        verify(main, new Times(1)).operations(calculatorResults, argumentsList);

    }
    @Test
    void higherNumberOfArgumentsOperation(){
        DataReader dataReaderMock = mock(DataReader.class);
        List<Double> argumentsList = List.of(1.0, 2.0, 3.0);
        when(dataReaderMock.inputArguments(3)).thenReturn(argumentsList);
        when(dataReaderMock.inputNumberWhenMoreThanTwoArguments()).thenReturn(3);

        CalculatorResults calculatorResults = new CalculatorResults();
        Main main = spy(new Main(null, dataReaderMock, null));
        doNothing().when(main).operations(calculatorResults, argumentsList);
        main.higherNumberOfArgumentsOperation(calculatorResults);
        verify(main, new Times(1)).operations(calculatorResults, argumentsList);

    }

    @Test
    void twoArgumentsOperation(){
        DataReader dataReaderMock = mock(DataReader.class);
        List<Double> argumentsList = List.of(1.0, 2.0);
        when(dataReaderMock.inputArguments(2)).thenReturn(argumentsList);

        CalculatorResults calculatorResults = new CalculatorResults();
        Main main = spy(new Main(null, dataReaderMock, null));
        doNothing().when(main).operations(calculatorResults, argumentsList);
        main.twoArgumentsOperation(calculatorResults);
        verify(main, new Times(1)).operations(calculatorResults, argumentsList);

    }

//    @Test
//    void startCounting(){
//        DataReader dataReaderMock = mock(DataReader.class);
//        when(dataReaderMock.getOperationChoice()).thenReturn('+');
//        when(dataReaderMock.setNumberOfArguments()).thenReturn('1');
//
//        resultsWriter.Main resultsWriterMock = mock(resultsWriter.Main.class);
//
//        CalculatorResults calculatorResults = new CalculatorResults(LocalDateTime.now(), List.of(2.0, 2.0),
//                ADDITION, 4);
//
//        Main main = spy(new Main(null, dataReaderMock, resultsWriterMock));
//        doNothing().when(main).operations(any(CalculatorResults.class), anyList());
//
//        ArgumentCaptor<CalculatorResults> argumentCaptor = ArgumentCaptor.forClass(CalculatorResults.class);
//
//        main.startCounting();
//        verify(resultsWriterMock, new Times(1)).startWriting(argumentCaptor.capture());
//        CalculatorResults results = argumentCaptor.getValue();
//        Assertions.assertEquals(calculatorResults.getArguments(), results.getArguments());
//    }

}
