package resultsWriter;

import calculationResults.CalculatorResults;
import calculationResults.Calculators;

import javax.xml.bind.DataBindingException;
import javax.xml.bind.JAXB;
import java.io.File;
import java.util.ArrayList;
import java.util.List;


public class Main {
    private final ArrayList<CalculatorResults> bufferedCalculators = new ArrayList<>();
    public final static String pathToArchiveXml = "E:/PROGRAMOWANIE/Step_podstawowy/Calculator/historicalData.xml";
    private final static String pathToLogFile = "E:/PROGRAMOWANIE/Step_podstawowy/Calculator/recordLog.txt";
    private final RecordLog recordLog;

    public Main(RecordLog recordLog) {
        this.recordLog = recordLog;
    }

    protected void addElement(CalculatorResults calculatorResults){
        bufferedCalculators.add(calculatorResults);
    }


    public void startWriting(CalculatorResults calculatorResults) {
        bufferedCalculators.add(0, calculatorResults);
    }

    public void updateFiles() {
        updateLogFile();
        updateXml();
    }


    public void updateLogFile() {
        recordLog.logFileExistCheck(pathToLogFile);
        List<String> logFileLines = recordLog.readLogFile(pathToLogFile);
        List<String> newLogFileLines = recordLog.updateLogFileLinesArray(bufferedCalculators, logFileLines);
        recordLog.writeNewLogFile(newLogFileLines, pathToLogFile);
    }

    public void updateXml() {
        if (bufferedCalculators.size() != 0) {
            Calculators calculators = readFromXml();
            ArrayList<CalculatorResults> list = new ArrayList<>();
            if (calculators != null && calculators.getCalculatorsArrayList() != null) {
                list.addAll(bufferedCalculators);
                list.addAll(calculators.getCalculatorsArrayList());
                calculators.setCalculatorsArrayList(list);
            } else {
                calculators = new Calculators();
                calculators.setCalculatorsArrayList(new ArrayList<>(bufferedCalculators));
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

    public void writeResultsToXML(Calculators calculators) {
        JAXB.marshal(calculators, new File(pathToArchiveXml));
    }

}
