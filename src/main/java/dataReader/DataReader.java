package dataReader;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DataReader {
    private final Scanner myScanner;

    public DataReader(Scanner scanner) {
        this.myScanner = scanner;
    }

    public char charScanner() {
        String temp;
        do {
            try {
                System.out.println("Proszę podać swój wybór (tylko 1 cyfra lub znak):");
                temp = myScanner.nextLine();
            } catch (StringIndexOutOfBoundsException e) {
                return '9';
            }
        } while (temp.length() > 1);
        return temp.charAt(0);
    }

    public char getOperationChoice() {
        char temp;
        do {
            System.out.println();
            System.out.println("Proszę wybrać rodzaj operacji");
            System.out.println("+ : dodawanie");
            System.out.println("- : odejmowanie");
            System.out.println("* : mnożenie");
            System.out.println("/ : dzielenie");
            System.out.println("0 : wyjście z programu");
            temp = charScanner();
            if (!('+' == (temp) || ('-' == (temp)) || ('*' == temp) || ('/' == temp) || ('0' == temp))) {
                System.out.println();
                System.out.println("Podaj prawidłowy symbol działania !!!");
            } else if ('0' == temp) {
                System.exit(0);
            }
        } while (!('+' == (temp) || ('-' == (temp)) || ('*' == temp) || ('/' == temp)));
        return temp;
    }

    public char setNumberOfArguments() {
        char temp;
        do {
            System.out.println();
            System.out.println("Proszę wybrać tryb wprowadzania argumentów operacji:");
            System.out.println("1 : Dwa argumenty");
            System.out.println("2 : Więcej niż dwa argumenty (np. 1+3+5+7 dla 4 argumentów)");
            System.out.println("3 : Nieokreślona liczba argumentów (aplikacja przyjmuje argumenty, aż użytkownik nie " +
                    "przerwie wprowadzania)");
            System.out.println("0 : wyjście z aplikacji");
            temp = charScanner();
            if (!('1' == (temp) || ('2' == (temp)) || ('3' == temp) || ('0' == temp))) {
                System.out.println();
                System.out.println("Niewłaściwa odpowiedź, spróbuj jeszcze raz");
            } else if ('0' == temp) {
                System.exit(0);
            }
        } while (!('1' == (temp) || ('2' == (temp)) || ('3' == temp)));
        return temp;
    }

    public int inputNumberWhenMoreThanTwoArguments() {
        int argumentsNumber = 0;
        do {
            try {
                System.out.println("Podaj swoją liczbę argumentów (więcej niż 2)");
                argumentsNumber = Integer.parseInt(stringScanner());
            } catch (NumberFormatException e) {
                System.out.println("To nie jest liczba !!!");
            }
        } while (argumentsNumber <= 2);
        return argumentsNumber;
    }

    public List<Double> inputArguments(int argumentsNumber) {
        List<Double> argumentsList = new ArrayList<>();
        for (int i = 1; i <= argumentsNumber; i++) {
            System.out.println(String.format("Podaj argument nr %s", i));
            do {
                try {
                    argumentsList.add(Double.parseDouble(stringScanner()));
                } catch (NumberFormatException e) {
                    System.out.println("Wpisałeś niedopuszczalny parametr (nie będący liczbą)");
                    System.out.println("Spróbuj ponownie...");
                }
            } while (!(argumentsList.size() == i));
        }
        return argumentsList;
    }


    public ArrayList<Double> inputUnknownNumberOfArguments() {
        ArrayList<Double> argumentsList = new ArrayList<>();
        int i = 1;
        String parameter;
        do {
            System.out.println(String.format("Podaj argument nr %s ('X' przerywa wprowadzanie, " +
                    "musisz podać przynajmniej 1 parametr)", i));
            parameter = stringScanner();
            if (!"x".equalsIgnoreCase(parameter)) {
                try {
                    argumentsList.add(Double.parseDouble(parameter));
                    i++;
                } catch (NumberFormatException e) {
                    System.out.println("Wpisałeś niedopuszczalny parametr (nie będący liczbą)");
                    System.out.println("Spróbuj ponownie...");
                }
            }
        } while (!"x".equalsIgnoreCase(parameter) || !(argumentsList.size() > 0));
        return argumentsList;
    }

    public String stringScanner() {
        return myScanner.nextLine();
    }
}
