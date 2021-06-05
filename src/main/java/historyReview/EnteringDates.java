package historyReview;

import dataReader.DataReader;

public class EnteringDates {
    private final DataReader dataReader;

    public EnteringDates(DataReader dataReader) {
        this.dataReader = dataReader;
    }

    public String enterDate(String startOrEnd) {
        System.out.println(String.format("Proszę wprowadzić datę %s", startOrEnd));
        System.out.println("Data powinna być wprowadzona w formacie 'DD.MM.YYYY hh:mm:ss', gdzie");
        System.out.println("DD - dzień, MM - miesiąc, YYYY - rok");
        System.out.println("hh - godzina, mm - minuty, ss - sekundy");
        return dataReader.stringScanner();
    }

    public char chooseReviewModel() {
        System.out.println("Wybierz tryb przeglądania historii: ");
        System.out.println("1: Pełna historia");
        System.out.println("2: Historia od zadanej daty do teraz");
        System.out.println("3: Historia od początku do zadanej daty");
        System.out.println("4: Historia pomiędzy dwoma zadanymi datami");
        System.out.println("0: Wyjście z aplikacji");
        return dataReader.charScanner();
    }
}
