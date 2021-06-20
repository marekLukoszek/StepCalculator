package resultsWriter;

import calculationResults.CalculatorResults;


import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class RecordLog {

    public RecordLog() {
    }

    public void logFileExistCheck(String pathToLogFile) {
        File file = new File(pathToLogFile);
        if (!file.exists()) {
            createRecordLog(pathToLogFile);
        }
    }

    public void createRecordLog(String pathToLogFile) {
        try (PrintWriter printWriter = new PrintWriter(pathToLogFile)) {
            printWriter.write("RECORD LOG UTWORZONY: " + LocalDateTime.now().toString());

        } catch (FileNotFoundException e) {
            System.out.println("Ścieżka dostępu nieprawidłowa");
        }
    }

    public List<String> readLogFile(String pathToLogFile) {
        List<String> logFileLines = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(pathToLogFile))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                logFileLines.add(line);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Nie znaleziono pliku");
            e.printStackTrace();
            return new ArrayList<>();
        } catch (IOException e) {
            System.out.println("Bład podczas odczytu");
            e.printStackTrace();
            return new ArrayList<>();
        }
        return logFileLines;
    }

    public List<String> updateLogFileLinesArray(List<CalculatorResults> bufferedCalculators,
                                                       List<String> logFileLinesArray) {
        if (bufferedCalculators.size() > 0) {
            logFileLinesArray.add(0, LocalDateTime.now() + " - Zapisano operacji: "
                    + bufferedCalculators.size());
        } else {
            logFileLinesArray.add(0, LocalDateTime.now()
                    + " - Nie znaleziono operacji do zapisu");
        }
        return logFileLinesArray;
    }

    public void writeNewLogFile(List<String> logFileLine, String pathToLogFile) {
        try (PrintWriter printWriter = new PrintWriter(pathToLogFile)) {
            for (String element : logFileLine) {
                printWriter.write(element);
                printWriter.write(System.lineSeparator());
            }
        } catch (FileNotFoundException e) {
            System.out.println("Ścieżka dostępu nieprawidłowa");
        }
    }
}

