package exception;

public class AccountNotChanged extends RuntimeException {
    public AccountNotChanged(String message) {
        super(message);
    }
}
