import java.util.ArrayList;
import java.util.List;

public class ManagerService {
    List<Account> accounts = new ArrayList<>();

    public List<Account> showAccounts() {
        return accounts;
    }

    public Account addAccount(Account account) {
        accounts.add(account);
        return account;
    }

    public void deleteAccount(Account account) {
        accounts.remove(account);
    }

    public Account changeAccount(Account account) { // Plan to make an accountDTO thing
        return account;
    }
}
