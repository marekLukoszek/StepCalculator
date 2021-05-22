package resultsWriter;

import calculationResults.CalculatorResults;
import calculator.Calculator;

import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class RecordLog {
    private final static String pathToLogFile = "E:/PROGRAMOWANIE/Step/Calculator/recordLog.txt";

    public static void logFileExistCheck() {
        File file = new File(pathToLogFile);
        if (!file.exists()) {
            createRecordLog();
        }
    }

    public static void createRecordLog() {
        try (PrintWriter printWriter = new PrintWriter(pathToLogFile)) {
            printWriter.write("RECORD LOG UTWORZONY: " + LocalDateTime.now().toString());

        } catch (FileNotFoundException e) {
            System.out.println("Ścieżka dostępu nieprawidłowa");
        }
    }

    public static List<String> readLogFile() {
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

    public static List<String> updateLogFileLinesArray(ArrayList<CalculatorResults> bufferedCalculators,
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

    public static void writeNewLogFile(List<String> logFileLine) {
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

