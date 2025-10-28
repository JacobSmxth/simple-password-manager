package service;

import domain.Account;
import dto.AccountUpdateDTO;
import exception.AccountNotChanged;
import exception.AccountNotFound;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class AccountService {
    private Long counter = 0L;
    private Map<Long, Account> accounts = new HashMap<>(); // I should switch to hashMap

    public AccountService() {
        // loadAccounts function
        // loadCounter function
    }

    public Map<Long, Account> listAllAccounts() {
        return accounts;
    }

    public Account addAccount(Account account) {
        account.setId(++counter);
        accounts.put(counter, account);
        return account;
    }

    public void checkId(Long id) {
        boolean result = accounts.containsKey(id);
        if (!result) {
            throw new AccountNotFound(id);
        }
    }

    public void deleteAccount(Long id) throws AccountNotFound{
        Account removed = accounts.remove(id);
        if (removed == null) {
            throw new AccountNotFound(id);
        }
    }

    public Account changeAccount(Long id, AccountUpdateDTO dto) { // Plan to make an accountDTO thing
        Account account = accounts.get(id);
        if (account == null) {
            throw new AccountNotFound(id);
        }

        boolean changed = false;

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
            throw new AccountNotChanged("Account already contains those values");
        }

        return account;
    }
}
