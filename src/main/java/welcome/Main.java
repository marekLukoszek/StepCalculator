package welcome;

import static dataReader.DataReader.charScanner;
import static resultsWriter.Main.updateXml;

public class Main {

    public static void main(String[] args) {

            Thread thread = createThreadUpdateXml();
            thread.start();

            mainMenu();

        }

        public static void mainMenu() {
            char userChoice;
            do {
                userChoice = getMainChoice();
                proceedMainChoice(userChoice);

            } while (!('0' == userChoice));
        }

        public static char getMainChoice() {
            System.out.println();
            System.out.println("Proszę wybrać tryb działania aplikacji:");
            System.out.println("1 : Kalkulator");
            System.out.println("2 : Przeglądanie historii");
            System.out.println("0 : Wyjście z aplikacji");
            return charScanner();
        }


        public static void proceedMainChoice(char userChoice) {
            if ('1' == userChoice) {
                dataReader.Main.startCounting();

            } else if ('2' == userChoice) {
                historyReview.Main.startSearching();

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

