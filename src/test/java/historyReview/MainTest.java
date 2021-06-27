package historyReview;

import calculationResults.CalculatorResults;
import calculationResults.Calculators;
import calculator.OperationType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.internal.verification.Times;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import static org.mockito.Mockito.*;

public class MainTest {

    @Test
    void readDate() {
        EnteringDates enteringDatesMock = mock((EnteringDates.class));
        when(enteringDatesMock.enterDate(Mockito.anyString())).thenReturn("29.05.2021 12:00:00");
        Main main = new Main(enteringDatesMock, null);
        Assertions.assertEquals(LocalDateTime.of(2021, 5, 29, 12, 0, 0), main.readDate("STRING"));
    }

    @Test
    void readDateWithIncorrectValues() {
        EnteringDates enteringDatesMock = mock((EnteringDates.class));
        when(enteringDatesMock.enterDate(Mockito.anyString())).then(new Answer<String>() {
            int counter = 0;

            @Override
            public String answer(InvocationOnMock invocationOnMock) {
                counter++;
                if (counter == 1) {
                    return "abcd";
                } else if (counter == 2) {
                    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");
                    return LocalDateTime.now().plusDays(1).format(dateTimeFormatter);
                } else {
                    return "29.05.2021 12:00:00";
                }
            }
        });
        Main main = new Main(enteringDatesMock, null);
        Assertions.assertEquals(LocalDateTime.of(2021, 5, 29, 12, 0, 0), main.readDate("startowa"));
        verify(enteringDatesMock, new Times(3)).enterDate(Mockito.anyString());
    }

    @Test
    void printResults() {
        ArrayList<CalculatorResults> testArrayList = new ArrayList<>();
        testArrayList.add(new CalculatorResults(LocalDateTime.now(), new ArrayList<>(), OperationType.ADDITION, 10));
        testArrayList.add(new CalculatorResults(LocalDateTime.now().plusDays(1), new ArrayList<>(), OperationType.ADDITION, 11));
        testArrayList.add(new CalculatorResults(LocalDateTime.now().minusDays(1), new ArrayList<>(), OperationType.ADDITION, 12));
        Calculators calculators = new Calculators(testArrayList);
        Main mainMock = mock(Main.class);
        Mockito.doCallRealMethod().when(mainMock).printResults(Mockito.eq(calculators), Mockito.nullable(LocalDateTime.class), Mockito.nullable(LocalDateTime.class));

        mainMock.printResults(calculators, LocalDateTime.now().minusDays(20), LocalDateTime.now().plusDays(5));
        verify(mainMock, new Times(3)).printText(Mockito.anyString());

        mainMock.printResults(calculators, null, LocalDateTime.now().plusDays(5));
        verify(mainMock, new Times(6)).printText(Mockito.anyString());

        mainMock.printResults(calculators, LocalDateTime.now().minusDays(20), null);
        verify(mainMock, new Times(9)).printText(Mockito.anyString());

        mainMock.printResults(calculators, LocalDateTime.now().minusMinutes(5), null);
        verify(mainMock, new Times(11)).printText(Mockito.anyString());

        mainMock.printResults(calculators, null, null);
        verify(mainMock, new Times(14)).printText(Mockito.anyString());
    }

    @Test
    void printResultWithIncorrectParameters() {
        ArrayList<CalculatorResults> testArrayList = new ArrayList<>();
        testArrayList.add(new CalculatorResults(LocalDateTime.now(), new ArrayList<>(), OperationType.ADDITION, 10));
        testArrayList.add(new CalculatorResults(LocalDateTime.now().plusDays(1), new ArrayList<>(), OperationType.ADDITION, 11));
        testArrayList.add(new CalculatorResults(LocalDateTime.now().minusDays(1), new ArrayList<>(), OperationType.ADDITION, 12));
        Calculators calculators = new Calculators(testArrayList);
        Main mainMock = mock(Main.class);
        Mockito.doCallRealMethod().when(mainMock).printResults(Mockito.nullable(Calculators.class), Mockito.nullable(LocalDateTime.class), Mockito.nullable(LocalDateTime.class));

        mainMock.printResults(calculators, LocalDateTime.now().plusDays(1), LocalDateTime.now());
        verify(mainMock, new Times(1)).printText("Data początkowa jest późniejsza niż końcowa");

        mainMock.printResults(null, LocalDateTime.now().minusDays(1), LocalDateTime.now());
        verify(mainMock, new Times(1)).printText("Parametr CalculatorViewer jest nullem");
    }

    @Test
    void showHistoryBetweenDates() {
        EnteringDates enteringDatesMock = Mockito.mock(EnteringDates.class);
        resultsWriter.Main resultsWriterMock = Mockito.mock(resultsWriter.Main.class);

        Main historyReview = new Main(enteringDatesMock, resultsWriterMock);
        Main historyReviewSpy = spy(historyReview);
        doNothing().when(historyReviewSpy).printResults(any(), any(), any());
        LocalDateTime startDate = LocalDateTime.of(2021, 4, 1, 12, 0, 0);
        LocalDateTime endDate = LocalDateTime.of(2021, 6, 1, 12, 0, 0);

        doReturn(startDate).when(historyReviewSpy).readDate("startową");
        doReturn(endDate).when(historyReviewSpy).readDate("końcową");

        Calculators calculators = new Calculators(new ArrayList<>());
        when(resultsWriterMock.readFromXml()).thenReturn(calculators);

        historyReviewSpy.showHistoryBetweenDates();
        verify(historyReviewSpy, new Times(1)).printResults(calculators, startDate, endDate);
    }

    @Test
    void showHistoryToEndDate() {
        EnteringDates enteringDatesMock = Mockito.mock(EnteringDates.class);
        resultsWriter.Main resultsWriterMock = Mockito.mock(resultsWriter.Main.class);

        Main historyReview = new Main(enteringDatesMock, resultsWriterMock);
        Main historyReviewSpy = spy(historyReview);
        doNothing().when(historyReviewSpy).printResults(any(), any(), any());
        LocalDateTime endDate = LocalDateTime.of(2021, 6, 1, 12, 0, 0);

        doReturn(endDate).when(historyReviewSpy).readDate("końcową");

        Calculators calculators = new Calculators(new ArrayList<>());
        when(resultsWriterMock.readFromXml()).thenReturn(calculators);

        historyReviewSpy.showHistoryToEndDate();
        verify(historyReviewSpy, new Times(1)).printResults(calculators, null, endDate);
    }

    @Test
    void showHistoryFromStartDate() {
        EnteringDates enteringDatesMock = Mockito.mock(EnteringDates.class);
        resultsWriter.Main resultsWriterMock = Mockito.mock(resultsWriter.Main.class);

        Main historyReview = new Main(enteringDatesMock, resultsWriterMock);
        Main historyReviewSpy = spy(historyReview);
        doNothing().when(historyReviewSpy).printResults(any(), any(), any());
        LocalDateTime startDate = LocalDateTime.of(2021, 4, 1, 12, 0, 0);

        doReturn(startDate).when(historyReviewSpy).readDate("startową");

        Calculators calculators = new Calculators(new ArrayList<>());
        when(resultsWriterMock.readFromXml()).thenReturn(calculators);

        historyReviewSpy.showHistoryFromStartDate();
        verify(historyReviewSpy, new Times(1)).printResults(calculators, startDate, null);
    }

    @Test
    void showFullHistory() {
        EnteringDates enteringDatesMock = mock(EnteringDates.class);
        resultsWriter.Main resultsWriterMock = mock(resultsWriter.Main.class);

        Main historyReview = new Main(enteringDatesMock, resultsWriterMock);
        Main historyReviewSpy = spy(historyReview);
        doNothing().when(historyReviewSpy).printResults(any(), any(), any());

        Calculators calculators = new Calculators(new ArrayList<>());
        when(resultsWriterMock.readFromXml()).thenReturn(calculators);

        historyReviewSpy.showFullHistory();
        verify(historyReviewSpy, new Times(1)).printResults(calculators, null, null);
    }

    @Test
    void startSearching() {
        resultsWriter.Main resultsWriterMock = mock(resultsWriter.Main.class);
        EnteringDates enteringDatesMock = mock(EnteringDates.class);
        when(enteringDatesMock.chooseReviewModel()).thenAnswer(new Answer<Character>() {
            int counter = 0;

            @Override
            public Character answer(InvocationOnMock invocationOnMock) throws Throwable {
                counter++;
                if (counter == 1) {
                    return 'A';
                } else if (counter == 2) {
                    return '5';
                } else {
                    return '1';
                }
            }
        });
        when(enteringDatesMock.enterDate(anyString())).thenReturn("2019.02.01 12:00:00");
        Main historyReviewMain = new Main(enteringDatesMock, resultsWriterMock);
        historyReviewMain.startSearching();
        verify(enteringDatesMock, new Times(3)).chooseReviewModel();
    }

    @Test
    void startSearchingWithProperValue2() {
        resultsWriter.Main resultsWriterMock = mock(resultsWriter.Main.class);
        EnteringDates enteringDatesMock = mock(EnteringDates.class);

        when(enteringDatesMock.chooseReviewModel()).thenReturn('2');
        when(enteringDatesMock.enterDate(anyString())).thenReturn("01.02.2019 12:00:00");

        Main historyReviewMain = new Main(enteringDatesMock, resultsWriterMock);
        historyReviewMain.startSearching();
        verify(enteringDatesMock, new Times(1)).chooseReviewModel();
        Assertions.assertEquals("01.02.2019 12:00:00", enteringDatesMock.enterDate(anyString()));
    }

    @Test
    void startSearchingWithProperValue3() {
        resultsWriter.Main resultsWriterMock = mock(resultsWriter.Main.class);
        EnteringDates enteringDatesMock = mock(EnteringDates.class);

        when(enteringDatesMock.chooseReviewModel()).thenReturn('3');
        when(enteringDatesMock.enterDate(anyString())).thenReturn("01.02.2019 12:00:00");

        Main historyReviewMain = new Main(enteringDatesMock, resultsWriterMock);
        historyReviewMain.startSearching();
        verify(enteringDatesMock, new Times(1)).chooseReviewModel();
        Assertions.assertEquals("01.02.2019 12:00:00", enteringDatesMock.enterDate(anyString()));
    }

    @Test
    void startSearchingWithProperValue4() {
        resultsWriter.Main resultsWriterMock = mock(resultsWriter.Main.class);
        EnteringDates enteringDatesMock = mock(EnteringDates.class);

        when(enteringDatesMock.chooseReviewModel()).thenReturn('4');
        when(enteringDatesMock.enterDate(anyString())).thenReturn("01.02.2019 12:00:00");

        Main historyReviewMain = new Main(enteringDatesMock, resultsWriterMock);
        historyReviewMain.startSearching();
        verify(enteringDatesMock, new Times(1)).chooseReviewModel();
        Assertions.assertEquals("01.02.2019 12:00:00", enteringDatesMock.enterDate(anyString()));

    }

    @Test
   void startWriting(){
        
    }
}


