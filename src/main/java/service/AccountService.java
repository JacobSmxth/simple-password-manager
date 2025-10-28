package service;

import domain.Account;
import dto.AccountUpdateDTO;
import exception.AccountNotChanged;
import exception.AccountNotFound;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class AccountService {
    List<Account> accounts = new ArrayList<>(); // I should switch to hashMap
    int counter = 0;

    public AccountService() {
        // loadAccounts function
        // loadCounter function
    }

    public List<Account> listAllAccounts() {
        return accounts;
    }

    public Account addAccount(Account account) {
        counter++;
        account.setId(counter);
        accounts.add(account);
        return account;
    }

    public boolean checkId(int id) {
        for (Account acc : accounts) {
            if (acc.getId() == id) {
                return true;
            }
        }
        throw new AccountNotFound(id);
    }

    public void deleteAccount(int id) throws AccountNotFound{
        boolean found = false;
        for (Account acc : accounts) {
            if (acc.getId() == id) {
                found = true;
                accounts.remove(acc);
                break;
            }
        }
        if (!found) {
            throw new AccountNotFound(id);
        }

    }

    public Account changeAccount(int id, AccountUpdateDTO dto) { // Plan to make an accountDTO thing
        Account account = null;
        boolean changed = false;
        boolean found = false;
        for (Account acc : accounts) { // Did this instead of accounts.get(index) because after a delete the id might not stay the same. And manually tracking indexes might be just as slow as this
            if (acc.getId() == id) {
                found = true;
                account = acc;
                break;
            }
        }
        if (!found) {
            throw new AccountNotFound(id);
        }

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
