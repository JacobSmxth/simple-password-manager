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
        boolean changed = false;
        for (Account acc: accounts) {
            if (acc.getId() == id) {
                account = acc;
            } else {
                throw new RuntimeException("Not found");
            }
        }

        try {
            assert account != null;
            if (dto.getSite() != null) {
                account.setSite(dto.getSite());
                changed = true;
            }

            if (dto.getUsername() != null) {
                account.setUsername(dto.getUsername());
                changed = true;
            }

            if (dto.getPassword() != null) {
                account.setPassword(dto.getPassword());
                changed = true;
            }

            if (changed) {
                account.setLastChange(LocalDateTime.now().toString());
            } else {
                throw new RuntimeException("Nothing different");
            }
        } catch (AssertionError ex) {
            System.out.println("Account not found");
        }

        return account;
    }
}
