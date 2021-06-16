package resultsWriter;

import org.junit.jupiter.api.Test;
import org.mockito.internal.verification.Times;

import java.io.PrintWriter;
import java.util.List;

import static org.mockito.Mockito.*;

public class RecordLogTest {
    @Test
    void updateLogFileLinesArray(){
        List<String> logFileLine = List.of("abc", "def", "ghi");
        PrintWriter printWriterMock = mock(PrintWriter.class);
        doNothing().when(printWriterMock).write(logFileLine.get(0));
        doNothing().when(printWriterMock).write(logFileLine.get(1));
        doNothing().when(printWriterMock).write(logFileLine.get(2));

        updateLogFileLinesArray();
        verify(printWriterMock, new Times(3));
    }
}
