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
        println("site: " + acc.getSite() +",");
        println("username: " + acc.getUsername() + ",");
        println("password: " + acc.getPassword() + ",");
        println("lastChange: " + acc.getLastChange());
        println("}");
    }

    public void showAllAccounts() {
        for (Account acc : service.accounts) {
          showAccount(acc);
        }
    }

    public void addAccount() {
        Account newAcc = service.addAccount(new Account(sc.nextLine(), sc.nextLine(), sc.nextLine()));
        println("New account added.");
        showAccount(newAcc);
    }
}
