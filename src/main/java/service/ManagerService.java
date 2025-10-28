package service;

import domain.Account;
import dto.AccountUpdateDTO;
import exception.GlobalExceptionHandler;

import java.util.List;
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
        List<Account> accs = service.listAllAccounts();
        for (int i = 0; i < accs.size(); i++) {
            showAccount(accs.get(i));
            if (i != accs.size() - 1) {
                println(",");
            }
        }
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
            int id = Integer.parseInt(sc.nextLine());
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

    public void closeScanner() {
        sc.close();
    }

}
