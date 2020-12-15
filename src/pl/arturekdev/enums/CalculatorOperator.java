package pl.arturekdev.enums;

public enum CalculatorOperator {

    ADDITION('+'),
    SUBTRACTION('-'),
    MULTIPLICATION('*'),
    DIVISION('/');

    private final char sign;

    CalculatorOperator(char sign){
        this.sign = sign;
    }

    public char getSign() {
        return sign;
    }
}
