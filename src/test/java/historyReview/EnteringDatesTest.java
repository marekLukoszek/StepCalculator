package historyReview;

import dataReader.DataReader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class EnteringDatesTest {

    @Test
    void enterDateTest(){
        DataReader dataReaderMock = Mockito.mock(DataReader.class);
        Mockito.when(dataReaderMock.stringScanner()).thenReturn("1234");
        EnteringDates enteringDates = new EnteringDates(dataReaderMock);
        Assertions.assertEquals("1234", enteringDates.enterDate("start"));
    }

    @Test
    void chooseReviewModel(){
        DataReader dataReaderMock = Mockito.mock(DataReader.class);
        Mockito.when(dataReaderMock.charScanner()).thenReturn('1');
        EnteringDates enteringDates = new EnteringDates(dataReaderMock);
        Assertions.assertEquals('1', enteringDates.chooseReviewModel());

    }
}
