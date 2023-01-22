package exceptions;

public class BoardSizeInvalidException extends RuntimeException{
    public BoardSizeInvalidException(String message) {
        super(message);
    }
}
