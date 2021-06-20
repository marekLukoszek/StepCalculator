package resultsWriter;

import calculationResults.CalculatorResults;
import calculationResults.Calculators;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.internal.verification.Times;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

public class RecordLogTest {
    private final static String pathToTestLogFile = "E:/PROGRAMOWANIE/Step_podstawowy/Calculator/testLog.txt";

    @Test
    void writeNewLogFile() throws IOException {
        List<String> logFileLine = List.of("dce", "def", "ghi");

        RecordLog recordLog = new RecordLog();

        recordLog.writeNewLogFile(logFileLine, pathToTestLogFile);
        List<String> testList = null;
        try {
            testList = Files.readAllLines(Path.of(pathToTestLogFile));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Assertions.assertEquals(logFileLine, testList);
        Files.delete(Path.of(pathToTestLogFile));
    }

    @Test
    void updateLogFileLinesArray() {
        RecordLog recordLogMock = mock(RecordLog.class);
        List<CalculatorResults> bufferedCalculators = new ArrayList<>();
        List<String> logFileLine = new ArrayList<>();
        logFileLine.add("abc");
        logFileLine.add("def");
        logFileLine.add("ghi");
        int tempSize = logFileLine.size();
        when(recordLogMock.updateLogFileLinesArray(bufferedCalculators, logFileLine)).thenCallRealMethod();

        Assertions.assertEquals(tempSize + 1, recordLogMock.updateLogFileLinesArray(bufferedCalculators, logFileLine).size());

    }

    @Test
    void readLogFile() throws IOException {
        PrintWriter printWriter = new PrintWriter(pathToTestLogFile);
        printWriter.write("To jest test");
        printWriter.close();

        RecordLog recordLog = new RecordLog();
        Assertions.assertEquals(List.of("To jest test"), recordLog.readLogFile(pathToTestLogFile));

        Files.delete(Path.of(pathToTestLogFile));
    }

    @Test
    void readLogFileWithIncorrectPath() throws IOException {

        RecordLog recordLog = new RecordLog();
        Assertions.assertEquals(new ArrayList<>(), recordLog.readLogFile(pathToTestLogFile));
    }

    @Test
    void createRecordLog() throws IOException {
        RecordLog recordLog = new RecordLog();
        recordLog.createRecordLog(pathToTestLogFile);

        Assertions.assertTrue(Files.exists(Path.of(pathToTestLogFile)));

        Files.delete(Path.of(pathToTestLogFile));
    }

    @Test
    void createRecordLogWithIncorrectPath() throws IOException {
        RecordLog recordLog = new RecordLog();
        recordLog.createRecordLog("E:/PROGRAMOWANI/Step_podstawowy/Calculator/testLog.txt");

        Assertions.assertFalse(Files.exists(Path.of("E:/PROGRAMOWANI/Step_podstawowy/Calculator/testLog.txt")));

    }

    @Test
    void logFileExistCheck(){
        RecordLog recordLogMock = mock(RecordLog.class);
        doCallRealMethod().when(recordLogMock).logFileExistCheck(pathToTestLogFile);
        recordLogMock.logFileExistCheck(pathToTestLogFile);
        verify(recordLogMock, new Times(1)).logFileExistCheck(pathToTestLogFile);

    }
}
