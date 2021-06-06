package historyReview;

import calculationResults.CalculatorResults;
import calculationResults.Calculators;
import jdk.jfr.StackTrace;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import static resultsWriter.Main.readFromXml;


public class Main {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");

    private final EnteringDates enteringDates;

    public Main(EnteringDates enteringDates) {
        this.enteringDates = enteringDates;
    }

    public void startSearching() {
        char chosenReviewModel;
        do {
            chosenReviewModel = enteringDates.chooseReviewModel();
            if (chosenReviewModel != '1' && chosenReviewModel != '2' && chosenReviewModel != '3' && chosenReviewModel != '4') {
                System.out.println("Niepoprawny wybór. Spróbój jeszcze raz");

            }
        } while (('1' != chosenReviewModel) && (chosenReviewModel != '2') && (chosenReviewModel != '3') && (chosenReviewModel != '4'));

        switch (chosenReviewModel) {
            case '1': {
                showFullHistory();
                break;
            }
            case '2': {
                showHistoryFromStartDate();
                break;
            }
            case '3': {
                showHistoryToEndDate();
                break;
            }
            case '4': {
                showHistoryBetweenDates();
                break;
            }
            case '0': {
                System.exit(0);
            }
        }
    }


    public void showFullHistory() {
        System.out.println("Znalezione operacje: ");
        Calculators calculatorsViewer = readFromXml();
        printResults(calculatorsViewer, null, null);
    }

    public void showHistoryFromStartDate() {
        Calculators calculatorsViewer = readFromXml();
        LocalDateTime startDate = readDate("startową");

        System.out.println("Znalezione operacje: ");
        printResults(calculatorsViewer, startDate, null);
    }

    public void showHistoryToEndDate() {
        Calculators calculatorsViewer = readFromXml();
        LocalDateTime endDate = readDate("końcową");
        System.out.println("Znalezione operacje: ");
        printResults(calculatorsViewer, null, endDate);
    }

    public void showHistoryBetweenDates() {
        Calculators calculatorsViewer = readFromXml();
        LocalDateTime startDate = readDate("startową");
        LocalDateTime endDate = readDate("końcową");
        System.out.println("Znalezione operacje: ");
        printResults(calculatorsViewer,startDate,endDate);

    }

    public LocalDateTime readDate(String startOrEnd) {
        LocalDateTime date = null;
        do {
            try {
                date = LocalDateTime.parse(enteringDates.enterDate(startOrEnd), formatter);
            } catch (DateTimeParseException e) {
                System.out.println("Podaj poprawny format daty !!!");
            }
        } while (date == null || date.isAfter(LocalDateTime.now()));
        return date;
    }

    public void printResults(Calculators calculatorsViewer, LocalDateTime startDate, LocalDateTime endDate){
        if(calculatorsViewer == null){
            System.out.println("Parametr CalculatorViewer jest nullem");
            return;
        }
        if(startDate != null && endDate != null && startDate.isAfter(endDate)){
            System.out.println("Data początkowa jest późniejsza niż końcowa");
            return;
        }
        for (CalculatorResults element : calculatorsViewer.getCalculatorsArrayList()) {
            if ((startDate == null || startDate.isBefore(element.getOperationDate())) && (endDate == null || endDate.isAfter(element.getOperationDate()))) {
                System.out.println(element);
            }
        }

    }
}


