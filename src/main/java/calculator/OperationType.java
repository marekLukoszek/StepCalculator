package calculator;

public enum OperationType {
    ADDITION("+"),
    SUBTRACTION("-"),
    MULTIPLICATION("*"),
    DIVISION("/"),
    SAMPLE("?");

    private final String sign;

    OperationType(String sign) {
        this.sign = sign;
    }

    public String getSign() {
        return sign;
    }
}
