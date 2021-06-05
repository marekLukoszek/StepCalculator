package historyReview;

import calculationResults.CalculatorResults;
import calculationResults.Calculators;

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
        } while (('1' != chosenReviewModel) && (chosenReviewModel !='2') && (chosenReviewModel !='3') && (chosenReviewModel !='4'));

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


    public static void showFullHistory() {
        System.out.println("Znalezione operacje: ");
        Calculators calculatorsViewer = readFromXml();
        assert calculatorsViewer != null;
        for (CalculatorResults element : calculatorsViewer.getCalculatorsArrayList()) {
            System.out.println(element.toString());
        }
    }

    public void showHistoryFromStartDate() {
        Calculators calculatorsViewer = readFromXml();
        LocalDateTime startDate = LocalDateTime.now().plusDays(1);
        do {
            try {
                startDate = LocalDateTime.parse(enteringDates.enterDate("początkową"), formatter);
            } catch (DateTimeParseException e) {
                System.out.println("Podaj poprawny format daty !!!");
            }
        }
        while (startDate.isAfter(LocalDateTime.now()));

        System.out.println("Znalezione operacje: ");
        assert calculatorsViewer != null;
        for (CalculatorResults element : calculatorsViewer.getCalculatorsArrayList()) {
            if (startDate.isBefore(element.getOperationDate())) {
                System.out.println(element.toString());
            }
        }
    }

    public void showHistoryToEndDate() {
        Calculators calculatorsViewer = readFromXml();
        LocalDateTime endDate = LocalDateTime.now().plusDays(1);
        do {
            try {
                endDate = LocalDateTime.parse(enteringDates.enterDate("końcową"), formatter);
            } catch (DateTimeParseException e) {
                System.out.println("Podaj poprawny format daty !!!");
            }
        }
        while (endDate.isAfter(LocalDateTime.now()));

        System.out.println("Znalezione operacje: ");
        assert calculatorsViewer != null;
        for (CalculatorResults element : calculatorsViewer.getCalculatorsArrayList()) {
            if (endDate.isAfter(element.getOperationDate())) {
                System.out.println(element.toString());
            }
        }
    }

    public void showHistoryBetweenDates() {
        Calculators calculatorsViewer = readFromXml();
        LocalDateTime startDate = LocalDateTime.now().plusDays(1);
        LocalDateTime endDate = LocalDateTime.now().plusDays(1);
        do {
            try {
                startDate = LocalDateTime.parse(enteringDates.enterDate("początkową"), formatter);
            } catch (DateTimeParseException e) {
                System.out.println("Podaj poprawny format daty !!!");
            }
        } while (startDate.isAfter(LocalDateTime.now()));

        do {
            try {
                endDate = LocalDateTime.parse(enteringDates.enterDate("końcową"), formatter);
            } catch (DateTimeParseException e) {
                System.out.println("Podaj poprawny format daty !!!");
            }
        } while (endDate.isAfter(LocalDateTime.now()));

        System.out.println("Znalezione operacje: ");
        assert calculatorsViewer != null;
        for (CalculatorResults element : calculatorsViewer.getCalculatorsArrayList()) {
            if (startDate.isBefore(element.getOperationDate()) && endDate.isAfter(element.getOperationDate())) {
                System.out.println(element.toString());

            }
        }
    }
}


