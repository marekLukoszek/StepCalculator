package welcome;

import org.junit.jupiter.api.Test;
import org.mockito.internal.verification.Times;

import static org.mockito.Mockito.*;

public class MainTest {

    @Test
    void testCreateThreadUpdateXml() throws InterruptedException {
        resultsWriter.Main resultsWriterMock = mock(resultsWriter.Main.class);
        Main main = new Main(null, resultsWriterMock, null);

        Thread thread = main.createThreadUpdateXml();

        thread.start();
        Thread.sleep(9500);
        verify(resultsWriterMock, times(0)).updateFiles();
        Thread.sleep(1000);
        verify(resultsWriterMock, times(1)).updateFiles();
        thread.interrupt();
    }

    @Test
    void proceedMainChoice() {
        dataReader.Main dataReaderMainMock = mock(dataReader.Main.class);
        historyReview.Main historyReviewMainMock = mock(historyReview.Main.class);
        Main main = new Main(historyReviewMainMock, null, dataReaderMainMock);

        main.proceedMainChoice('1');
        verify(dataReaderMainMock).startCounting();
        verify(historyReviewMainMock, times(0)).startSearching();

        main.proceedMainChoice('2');
        verify(dataReaderMainMock).startCounting();
        verify(historyReviewMainMock).startSearching();

        main.proceedMainChoice('3');
        verify(dataReaderMainMock, times(1)).startCounting();
        verify(historyReviewMainMock, times(1)).startSearching();
    }
}
