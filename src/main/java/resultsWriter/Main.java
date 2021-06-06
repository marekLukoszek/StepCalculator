package resultsWriter;

import calculationResults.CalculatorResults;
import calculator.Calculator;
import calculationResults.Calculators;

import javax.xml.bind.DataBindingException;
import javax.xml.bind.JAXB;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static resultsWriter.RecordLog.*;


public class Main {
    public static ArrayList<CalculatorResults> bufferedCalculators = new ArrayList<>();
    public static final String pathToArchiveXml = "E:/PROGRAMOWANIE/Step_podstawowy/Calculator/historicalData.xml";

    public static void startWriting(CalculatorResults calculatorResults) {
        bufferedCalculators.add(0, calculatorResults);
    }

    public void updateXml() {

        //aktualizuje recordFile
        logFileExistCheck();
        List<String> logFileLines = readLogFile();
        List<String> newLogFileLines = updateLogFileLinesArray(bufferedCalculators, logFileLines);
        writeNewLogFile(newLogFileLines);
        //aktualizuję xml jeśli w buforze jest nowa kalkulacja
        if (bufferedCalculators.size() != 0) {
            Calculators calculators = readFromXml();
            ArrayList<CalculatorResults> list;
            if (calculators != null && calculators.getCalculatorsArrayList() != null) {
                list = calculators.getCalculatorsArrayList();
                list.addAll(0, bufferedCalculators);
                calculators.setCalculatorsArrayList(list);
            } else {
                calculators = new Calculators();
                calculators.setCalculatorsArrayList(bufferedCalculators);
            }

            writeResultsToXML(calculators);
            bufferedCalculators.clear();

        }
    }

    public Calculators readFromXml() {
        try {
            return JAXB.unmarshal(new File(pathToArchiveXml), Calculators.class);
        } catch (DataBindingException e) {
            System.out.println("Plik nie istnieje lub jego struktura jest niepoprawna " + pathToArchiveXml);
            return null;
        }
    }

    public static void writeResultsToXML(Calculators calculators) {
        JAXB.marshal(calculators, new File(pathToArchiveXml));
    }

}
