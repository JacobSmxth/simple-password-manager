import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class AccountService {
    List<Account> accounts = new ArrayList<>();

    public List<Account> listAllAccounts() {
        return accounts;
    }

    public Account addAccount(Account account) {
        account.setId(accounts.size() + 1);
        accounts.add(account);
        return account;
    }

    public void deleteAccount(int id) {
        boolean found = false;
        for (Account acc : accounts) {
            if (acc.getId() == id) {
                found = true;
                accounts.remove(acc);
                break;
            }
        }
        if (!found) {
            throw new RuntimeException("Not found");
        }

    }

    public Account changeAccount(int id, AccountUpdateDTO dto) { // Plan to make an accountDTO thing
        Account account = null;
        boolean changed = false;
        boolean found = false;
        for (Account acc: accounts) { // Did this instead of accounts.get(index) because after a delete the id might not stay the same. And manually tracking indexes might be just as slow as this
            if (acc.getId() == id) {
                found = true;
                account = acc;
                break;
            }
        }
        if (!found) {
            throw new RuntimeException("Not found");
        }

        try {
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
