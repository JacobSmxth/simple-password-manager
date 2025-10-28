package service;

import domain.Account;
import dto.AccountUpdateDTO;
import exception.GlobalExceptionHandler;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class ManagerService {
    AccountService service = new AccountService();
    Scanner sc = new Scanner(System.in);


    public static void print(String msg) {
        System.out.print(msg);
    }

    public static void println(String msg) {
        System.out.println(msg);
    }

    public void showAccount(Account acc) {
        print("id: " + acc.getId());
        println(" {");
        println("site: \'" + acc.getSite() + "\",");
        println("username: \"" + acc.getUsername() + "\",");
        println("password: \"" + acc.getPassword() + "\",");
        println("lastChange: " + acc.getLastChange() + "");
        print("}");
    }

    public void showAllAccounts() {
        println("\n=== All Accounts ===\n");
        Map<Long, Account> accs = service.listAllAccounts();

        if (accs.isEmpty()) {
            println("No accounts found.");
            return;
        }

        Account[] accountArray = accs.values().toArray(new Account[0]);

        for (int i = 0; i < accountArray.length; i++) {
            showAccount(accountArray[i]);
            if (i != accountArray.length - 1) {
                println(",");
            }
        }
        println("");
    }

    public void addAccount() {
        println("Adding new account:");
        print("Enter account site: ");
        String site = sc.nextLine();
        print("Enter account username: ");
        String username = sc.nextLine();
        print("Enter account password: ");
        String password = sc.nextLine();

        println("New account added.");
        try {
            showAccount(service.addAccount(new Account(site, username, password)));
        } catch (Exception ex) {
            GlobalExceptionHandler.handle(ex);
        }
    }

    public void changeAccount() {
        showAllAccounts();
        println("\n");

        AccountUpdateDTO dto = new AccountUpdateDTO();
        GlobalExceptionHandler.handleWithRetry(() -> {
            print("Enter account id: ");
            Long id = Long.parseLong(sc.nextLine());
            service.checkId(id);
            print("Would you like to change site? (y/n): ");
            if (sc.nextLine().equalsIgnoreCase("y")) {
                print("Enter new site name: ");
                dto.setSite(sc.nextLine());
            }
            print("Would you like to change username? (y/n): ");
            if (sc.nextLine().equalsIgnoreCase("y")) {
                print("Enter new username: ");
                dto.setUsername(sc.nextLine());
            }
            print("Would you like to change password? (y/n): ");
            if (sc.nextLine().equalsIgnoreCase("y")) {
                print("Enter new password: ");
                dto.setPassword(sc.nextLine());
            }
            service.changeAccount(id, dto);
            System.out.println("domain.Account has been updated.");
        }, sc);
    }

    public void deleteAccount() {
        showAllAccounts();
        println("\n");

        if (service.listAllAccounts().isEmpty()) {
            println("No accounts to delete");
            return;
        }

        GlobalExceptionHandler.handleWithRetry(() -> {
            print("Enter account id: ");
            Long id = Long.parseLong(sc.nextLine());
            Account acc = service.getAccount(id);
            print("Are you sure you want to delete " + acc.getSite() + "'s account? (y/n): ");
            if(sc.nextLine().equalsIgnoreCase("y")) {
                service.deleteAccount(id);
                println("Account deleted.");
            } else {
                println("Account not deleted");
            }
        }, sc);
    }

    public void closeScanner() {
        sc.close();
    }

}
