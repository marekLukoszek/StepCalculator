package calculationResults;

import java.util.ArrayList;
import java.util.Objects;

public class Calculators {
    private ArrayList<CalculatorResults> calculatorArrayList;

    public Calculators() {
    }

    public Calculators(ArrayList<CalculatorResults> calculatorArrayList) {
        this.calculatorArrayList = calculatorArrayList;
    }

    public ArrayList<CalculatorResults> getCalculatorsArrayList() {
        return calculatorArrayList;
    }

    public void setCalculatorsArrayList(ArrayList<CalculatorResults> calculatorArrayList) {
        this.calculatorArrayList = calculatorArrayList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Calculators that = (Calculators) o;
        return Objects.equals(calculatorArrayList, that.calculatorArrayList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(calculatorArrayList);
    }

    @Override
    public String toString() {
        return "Calculators{" +
                "calculatorArrayList=" + calculatorArrayList +
                '}';
    }
}
