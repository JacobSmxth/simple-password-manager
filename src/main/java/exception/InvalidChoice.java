package exception;

public class InvalidChoice extends RuntimeException {
    public InvalidChoice(String message) {
        super(message);
    }
}
