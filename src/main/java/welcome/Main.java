package welcome;

import dataReader.DataReader;
import historyReview.EnteringDates;

import java.util.Scanner;

import static resultsWriter.Main.updateXml;

public class Main {
    private final historyReview.Main historyMain;

    public Main(historyReview.Main historyMain) {
        this.historyMain = historyMain;
    }

    public static void main(String[] args) {

            Thread thread = createThreadUpdateXml();
            thread.start();

            new Main(new historyReview.Main(new EnteringDates(new DataReader(new Scanner(System.in))))).mainMenu();

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

        public static Thread createThreadUpdateXml() {
            return new Thread(() -> {
                try {
                    do {
                        Thread.sleep(10000);
                        updateXml();
                    } while (true);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    e.printStackTrace();
                }
            });
        }

}

