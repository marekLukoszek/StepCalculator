package calculationResults;

import java.util.ArrayList;

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
}
