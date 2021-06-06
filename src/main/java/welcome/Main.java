package welcome;

import dataReader.DataReader;
import historyReview.EnteringDates;

import java.util.Scanner;


public class Main {
    private final historyReview.Main historyMain;
    private final resultsWriter.Main resultsWriter;

    public Main(historyReview.Main historyMain, resultsWriter.Main resultsWriter) {
        this.historyMain = historyMain;
        this.resultsWriter = resultsWriter;
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        DataReader dataReader = new DataReader(scanner);
        EnteringDates enteringDates = new EnteringDates(dataReader);
        resultsWriter.Main resultsWriter = new resultsWriter.Main();
        Main main = new Main(new historyReview.Main(enteringDates, resultsWriter), resultsWriter);
        Thread thread = main.createThreadUpdateXml();
        thread.start();
        main.mainMenu();
    }

    public void mainMenu() {
        char userChoice;
        DataReader dataReader = new DataReader(new Scanner(System.in));
        do {
            userChoice = getMainChoice(dataReader);
            proceedMainChoice(userChoice);

        } while (!('0' == userChoice));
    }

    public static char getMainChoice(DataReader dataReader) {
        System.out.println();
        System.out.println("Proszę wybrać tryb działania aplikacji:");
        System.out.println("1 : Kalkulator");
        System.out.println("2 : Przeglądanie historii");
        System.out.println("0 : Wyjście z aplikacji");
        return dataReader.charScanner();
    }


    public void proceedMainChoice(char userChoice) {
        if ('1' == userChoice) {
            dataReader.Main.startCounting();

        } else if ('2' == userChoice) {
            historyMain.startSearching();

        } else if ('0' == userChoice) {
            System.exit(0);
        } else {
            System.out.println("Niewłaściwa odpowiedź, spróbuj jeszcze raz");
        }
    }

    public Thread createThreadUpdateXml() {
        return new Thread(() -> {
            try {
                do {
                    Thread.sleep(10000);
                    resultsWriter.updateXml();
                } while (true);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                e.printStackTrace();
            }
        });
    }

}

