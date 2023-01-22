package models;

public class Symbol {
    private char symbol;

    public Symbol(char symbol) {
        this.symbol = symbol;
    }

    public char getSymbol() {
        return symbol;
    }

    public Symbol setSymbol(char symbol) {
        this.symbol = symbol;
        return this;
    }
}
