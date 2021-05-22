package calculationResults;

import calculator.LocalDateTimeAdapter;
import calculator.OperationType;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDateTime;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
public class CalculatorResults {

    @XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
    private LocalDateTime operationDate;
    private List<Double> arguments;
    private OperationType operationType;
    private double result;

    public CalculatorResults(LocalDateTime operationDate, List<Double> arguments, OperationType operationType, double result) {
        this.operationDate = operationDate;
        this.arguments = arguments;
        this.operationType = operationType;
        this.result = result;
    }

    public CalculatorResults() {
    }

    public LocalDateTime getOperationDate() {
        return operationDate;
    }

    public void setOperationDate(LocalDateTime operationDate) {
        this.operationDate = operationDate;
    }

    public List<Double> getArguments() {
        return arguments;
    }

    public void setArguments(List<Double> arguments) {
        this.arguments = arguments;
    }

    public OperationType getOperationType() {
        return operationType;
    }

    public void setOperationType(OperationType operationType) {
        this.operationType = operationType;
    }

    public double getResult() {
        return result;
    }

    public void setResult(double result) {
        this.result = result;
    }

    public void setOperationsType(char actionChoice) {
        if (actionChoice == '+') {
            this.setOperationType(OperationType.ADDITION);
        } else if (actionChoice == '-') {
            this.setOperationType(OperationType.SUBTRACTION);
        } else if (actionChoice == '/') {
            this.setOperationType(OperationType.DIVISION);
        } else if (actionChoice == '*') {
            this.setOperationType(OperationType.MULTIPLICATION);
        }
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i <= getArguments().size() - 1; i++) {
            stringBuilder.append(" ");
            stringBuilder.append((getArguments().get(i).toString()));
            stringBuilder.append(" ");
            stringBuilder.append(getOperationType().getSign());
        }
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        return "Data operacji: " + getOperationDate() + " Operacja:" + stringBuilder + "= " + getResult();

    }
}
