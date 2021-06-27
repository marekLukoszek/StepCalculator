package calculator;

public enum OperationType {
    ADDITION("+"),
    SUBTRACTION("-"),
    MULTIPLICATION("*"),
    DIVISION("/");

    private final String sign;

    OperationType(String sign) {
        this.sign = sign;
    }

    public String getSign() {
        return sign;
    }
}
