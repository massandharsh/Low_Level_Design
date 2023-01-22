package exceptions;

public class NoUndoPossibleException extends RuntimeException{
    public NoUndoPossibleException(String message) {
        super(message);
    }
}
