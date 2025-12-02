package tasktracker;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        // --- Faylları yoxla və yoxdursa yarat ---
        File usersFile = new File("users.txt");
        if (!usersFile.exists()) {
            usersFile.createNewFile();
            // Default istifadəçiləri əlavə et
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(usersFile, true))) {
                bw.write("admin,123,Admin\n");
                bw.write("arzu,456,Member\n");
            }
            System.out.println("users.txt yaradıldı və default istifadəçilər əlavə olundu!");
        }

        File tasksFile = new File("tasks.txt");
        if (!tasksFile.exists()) {
            tasksFile.createNewFile();
            System.out.println("tasks.txt yaradıldı!");
        }

        // --- Login sistemi ---
        Scanner sc = new Scanner(System.in);

        System.out.println("--- Welcome to Task Tracker ---");

        System.out.print("Username: ");
        String username = sc.nextLine();
        System.out.print("Password: ");
        String password = sc.nextLine();

        try {
            String role = UserManager.validateLogin(username, password);
            if (role.equalsIgnoreCase("Admin")) {
                Admin admin = new Admin(username);
                admin.showMenu();
            } else if (role.equalsIgnoreCase("Member")) {
                Member member = new Member(username);
                member.showMenu();
            } else {
                System.out.println("Unknown role!");
            }
        } catch (UserNotFoundException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("Exiting Task Tracker. Goodbye!");
    }
}
