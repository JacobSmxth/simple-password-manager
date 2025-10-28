import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ManagerService {
    List<Account> accounts = new ArrayList<>();

    public List<Account> showAccounts() {
        return accounts;
    }

    public Account addAccount(Account account) {
        account.setId(accounts.size() + 1);
        accounts.add(account);
        return account;
    }

    public void deleteAccount(int id) {
        for (Account acc : accounts) {
            if (acc.getId() == id) {
                accounts.remove(acc);
            } else {
                throw new RuntimeException("Not found");
            }
        }
    }

    public Account changeAccount(int id, AccountUpdateDTO dto) { // Plan to make an accountDTO thing
        Account account = null;
        for (Account acc: accounts) {
            if (acc.getId() == id) {
                account = acc;
            } else {
                throw new RuntimeException("Not found");
            }
        }

        if (dto.getSite() != null) {
            account.setSite(dto.getSite());
        }

        if (dto.getUsername() != null) {
            account.setUsername(dto.getUsername());
        }

        if (dto.getPassword() != null) {
            account.setPassword(dto.getPassword());
        }

        account.setLastChange(LocalDateTime.now().toString());

        return account;
    }
}
