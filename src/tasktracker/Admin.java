package tasktracker;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Admin {
    private final String username;

    public Admin(String username) {
        this.username = username;
    }

    public void showMenu() throws IOException, UserNotFoundException {
        Scanner sc = new Scanner(System.in);
        int choice;
        do {
            System.out.println("\n--- Admin Menu ---");
            System.out.println("1. Add User");
            System.out.println("2. Delete User");
            System.out.println("3. Assign Task");
            System.out.println("4. View All Tasks");
            System.out.println("0. Logout");
            System.out.print("Choice: ");
            choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1 -> {
                    System.out.print("Username: ");
                    String uname = sc.nextLine();
                    System.out.print("Password: ");
                    String pwd = sc.nextLine();
                    System.out.print("Role (Admin/Member): ");
                    String role = sc.nextLine();
                    // --- duplicate-proof addUser ---
                    UserManager.addUser(uname, pwd, role);
                }
                case 2 -> {
                    System.out.print("Username to delete: ");
                    String uname = sc.nextLine();
                    UserManager.deleteUser(uname);
                    System.out.println("User deleted!");
                }
                case 3 -> { // Assign Task
                    System.out.print("Task Name: ");
                    String tname = sc.nextLine();

                    System.out.print("Assign to (username): ");
                    String assignedTo = sc.nextLine();

                    // --- Deadline input ilə try-catch ---
                    java.time.LocalDate deadline = null;
                    while (deadline == null) {
                        System.out.print("Deadline (YYYY-MM-DD): ");
                        String date = sc.nextLine();
                        try {
                            deadline = java.time.LocalDate.parse(date,
                                    java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                        } catch (java.time.format.DateTimeParseException e) {
                            System.out.println("Səhv format! Yenidən yazın (YYYY-MM-DD)");
                        }
                    }

                    int id = TaskManager.getAllTasks().size() + 1;
                    TaskManager.addTask(new Task(id, tname, assignedTo, "Pending", deadline));
                    System.out.println("Task assigned!");
                }
                case 4 -> {
                    List<Task> tasks = TaskManager.getAllTasks();
                    tasks.forEach(System.out::println);
                }
                case 0 -> System.out.println("Logging out...");
                default -> System.out.println("Invalid choice!");
            }
        } while (choice != 0);
    }
}
