package tasktracker;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Member {
    private final String username;

    public Member(String username) {
        this.username = username;
    }

    public void showMenu() throws IOException {
        Scanner sc = new Scanner(System.in);
        int choice;
        do {
            System.out.println("\n--- Member Menu ---");
            System.out.println("1. View My Tasks");
            System.out.println("2. Mark Task Completed");
            System.out.println("3. Add Progress Note");
            System.out.println("0. Logout");
            System.out.print("Choice: ");

            // input-u təhlükəsiz almaq üçün
            while (!sc.hasNextInt()) {
                System.out.println("Invalid input! Enter a number.");
                sc.next(); // discard invalid input
            }
            choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1 -> {
                    // Member yalnız öz task-larını görür
                    List<Task> tasks = TaskManager.getAllTasks();
                    tasks.stream()
                            .filter(t -> t.getAssignedTo().equals(username))
                            .forEach(System.out::println);
                }
                case 2 -> {
                    System.out.print("Task ID to mark completed: ");
                    while (!sc.hasNextInt()) {
                        System.out.println("Invalid input! Enter a valid Task ID.");
                        sc.next();
                    }
                    int tid = sc.nextInt();
                    sc.nextLine();
                    try {
                        TaskManager.markTaskCompleted(tid);
                        System.out.println("Task marked as completed!");
                    } catch (Exception e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                }
                case 3 -> {
                    System.out.print("Task ID: ");
                    while (!sc.hasNextInt()) {
                        System.out.println("Invalid input! Enter a valid Task ID.");
                        sc.next();
                    }
                    int tid = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Progress note: ");
                    String note = sc.nextLine();
                    try {
                        ProgressLogger.addProgress(username, tid, note);
                        System.out.println("Progress note added!");
                    } catch (Exception e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                }
                case 0 -> System.out.println("Logging out...");
                default -> System.out.println("Invalid choice!");
            }
        } while (choice != 0);
    }
}
