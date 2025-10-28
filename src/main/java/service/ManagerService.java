package service;

import domain.Account;
import dto.AccountUpdateDTO;
import exception.GlobalExceptionHandler;
import exception.InvalidChoice;

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

    public boolean showMenu() {
        println("\n=== Password Manager ===\n");
        println("1. Add Account");
        println("2. Delete Account");
        println("3. Change Account Values");
        println("4. List Accounts");
        println("5. Exit");
        println("");
        return takeChoice();
    }

    public boolean takeChoice() {
        print("Make a choice: ");
        int choice = Integer.parseInt(sc.nextLine());
        if (choice < 1 || choice > 5) {
            throw new InvalidChoice("Choice must be between 1 and 5.");
        }
        return processChoice(choice);
    }

    public boolean processChoice(int choice) {
        switch (choice) {
            case 1:
                addAccount();
                break;
            case 2:
                deleteAccount();
                break;
            case 3:
                changeAccount();
                break;
            case 4:
                showAllAccounts();
                break;
            case 5:
                closeScanner();
                println("Goodbye!");
                return false;
            default:
                println("How'd you get here?!");
                break;
        }
        return true;
    }

    public void run() {
        GlobalExceptionHandler.handleWithRetry(() -> {
            boolean running = true;
            while (running) {
                running = showMenu();
            }
        }, sc);

    }

    public void closeScanner() {
        sc.close();
    }

}
