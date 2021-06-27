package dataReader;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.internal.verification.Times;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import javax.xml.crypto.Data;
import java.util.List;
import java.util.Scanner;

import static org.mockito.Mockito.*;

public class DataReaderTest {

    @Test
    void inputArguments() {
        DataReader dataReaderMock = mock(DataReader.class);

        when(dataReaderMock.inputArguments(1)).thenCallRealMethod();
        when(dataReaderMock.stringScanner()).thenAnswer(new Answer<String>() {
            int counter = 0;

            @Override
            public String answer(InvocationOnMock invocationOnMock) throws Throwable {
                counter++;
                if (counter == 1) {
                    return "ABC";
                } else if (counter == 2) {
                    return "212a";
                } else {
                    return "3.5";
                }
            }
        });
        Assertions.assertEquals(List.of(3.5), dataReaderMock.inputArguments(1));
        verify(dataReaderMock, new Times(3)).stringScanner();
    }

    @Test
    void inputNumberWhenMoreThanTwoArguments() {
        DataReader dataReaderMock = mock(DataReader.class);

        when(dataReaderMock.inputNumberWhenMoreThanTwoArguments()).thenCallRealMethod();
        when(dataReaderMock.stringScanner()).thenAnswer(new Answer<String>() {
            int counter = 0;

            @Override
            public String answer(InvocationOnMock invocationOnMock) throws Throwable {
                counter++;
                if (counter == 1) {
                    return "ABC";
                } else if (counter == 2) {
                    return "1.5";
                } else if (counter == 3) {
                    return "1";
                } else {
                    return "4";
                }
            }
        });

        Assertions.assertEquals(4, dataReaderMock.inputNumberWhenMoreThanTwoArguments());
        verify(dataReaderMock, new Times(4)).stringScanner();
    }

    @Test
    void setNumberOfArguments() {
        DataReader dataReaderMock = mock(DataReader.class);

        when(dataReaderMock.setNumberOfArguments()).thenCallRealMethod();
        when(dataReaderMock.charScanner()).thenAnswer(new Answer<Character>() {
            int counter = 0;

            @Override
            public Character answer(InvocationOnMock invocationOnMock) throws Throwable {
                counter++;
                if (counter == 1) {
                    return '-';
                } else if (counter == 2) {
                    return 'A';
                } else if (counter == 3) {
                    return '7';
                } else {
                    return '1';
                }
            }
        });
        Assertions.assertEquals('1', dataReaderMock.setNumberOfArguments());
        verify(dataReaderMock, new Times(4)).charScanner();
    }

    @Test
    void inputUnknownNumberOfArguments() {
        DataReader dataReaderMock = mock(DataReader.class);

        when(dataReaderMock.inputUnknownNumberOfArguments()).thenCallRealMethod();
        when(dataReaderMock.stringScanner()).thenAnswer(new Answer<String>() {
            int counter = 0;

            @Override
            public String answer(InvocationOnMock invocationOnMock) throws Throwable {
                counter++;
                if (counter == 1) {
                    return "abc";
                } else if (counter == 2) {
                    return "2.5";
                } else if (counter == 3) {
                    return "3.5";
                } else if (counter == 4) {
                    return "123.y0";
                } else if (counter == 5) {
                    return "x";
                } else {
                    return "4.5";
                }
            }
        });
        Assertions.assertEquals(List.of(2.5, 3.5), dataReaderMock.inputUnknownNumberOfArguments());
        verify(dataReaderMock, new Times(5)).stringScanner();
    }

    @Test
    void getOperationChoice() {
        DataReader dataReaderMock = mock(DataReader.class);

        when(dataReaderMock.getOperationChoice()).thenCallRealMethod();
        when(dataReaderMock.charScanner()).thenAnswer(new Answer<Character>() {
            int counter = 0;

            @Override
            public Character answer(InvocationOnMock invocationOnMock) throws Throwable {
                counter++;
                if (counter == 1) {
                    return 'a';
                } else if (counter == 2) {
                    return '1';
                } else if (counter == 3) {
                    return '_';
                } else if (counter == 4) {
                    return ' ';
                } else {
                    return '+';
                }
            }
        });
        Assertions.assertEquals('+', dataReaderMock.getOperationChoice());
        verify(dataReaderMock, new Times(5)).charScanner();
    }

    @Test
    void getOperationChoiceWithProperValue() {
        testMethod('-');
        testMethod('*');
        testMethod('/');

    }

    void testMethod(char character) {
        DataReader dataReaderMock = mock(DataReader.class);

        when(dataReaderMock.getOperationChoice()).thenCallRealMethod();
        when(dataReaderMock.charScanner()).thenReturn(character);

        Assertions.assertEquals(character, dataReaderMock.getOperationChoice());
        verify(dataReaderMock, new Times(1)).charScanner();
    }

    @Test
    void testStringScanner() {

        Scanner scannerMock = mock(Scanner.class);
        when(scannerMock.nextLine()).thenReturn("Test scannera");

        DataReader dataReader = new DataReader(scannerMock);

        Assertions.assertEquals("Test scannera", dataReader.stringScanner());
    }

    @Test
    void testCharScanner(){
        Scanner scannerMock = mock(Scanner.class);
        when(scannerMock.nextLine()).thenReturn("","123");

        DataReader dataReader = new DataReader(scannerMock);
        Assertions.assertEquals('9', dataReader.charScanner());
        Assertions.assertEquals('1', dataReader.charScanner());
    }
}
