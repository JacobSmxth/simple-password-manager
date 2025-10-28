package exception;

import java.util.Scanner;

public class GlobalExceptionHandler {

    public static void handle(Exception ex) {
        if (ex instanceof AccountNotFound) {
            System.out.println("Warning: " + ex.getMessage());
        } else if (ex instanceof AccountNotChanged) {
            System.out.println("Error: " + ex.getMessage());
        } else if (ex instanceof NumberFormatException) {
            System.out.println("Error: Invalid number format. Please enter valid number");
        } else {
            System.err.println("Unexpected error: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    public static void handleWithRetry(Runnable action, Scanner sc) {
        boolean success = false;

        while (!success) {
            try {
                action.run();
                success = true;
            } catch (Exception ex) {
                handle(ex);

                System.out.print("Would you like to try again? (y/n): ");
                if (!sc.nextLine().equalsIgnoreCase("y")) {
                    System.out.println("Operation cancelled.");
                    break;
                }
            }
        }
    }
}
