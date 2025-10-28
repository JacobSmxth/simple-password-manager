package exception;

public class AccountNotFound extends RuntimeException {
    public AccountNotFound(int id) {
        super("Account not found with id: " + id);
    }
}
