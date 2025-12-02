package tasktracker;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserManager {
    private static final String FILE_NAME = "users.txt";

    // --- Login yoxlama metodu ---
    public static String validateLogin(String username, String password)
            throws UserNotFoundException, IOException {
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            throw new UserNotFoundException("users.txt tapılmadı!");
        }

        try (Scanner sc = new Scanner(file)) {
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                if (line.trim().isEmpty()) continue; // boş sətrləri ötür
                String[] parts = line.split(",");
                if (parts.length < 3) continue; // format səhvlərini ötür
                if (parts[0].equals(username) && parts[1].equals(password)) {
                    return parts[2]; // role qaytarır
                }
            }
        }

        throw new UserNotFoundException("Invalid username or password!");
    }

    // --- Yeni istifadəçi əlavə et ---
    public static void addUser(String username, String password, String role) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
            bw.write(username + "," + password + "," + role + "\n");
        }
    }

    // --- İstifadəçi sil ---
    public static void deleteUser(String username) throws IOException, UserNotFoundException {
        File file = new File(FILE_NAME);
        if (!file.exists()) throw new UserNotFoundException("users.txt tapılmadı!");

        List<String> lines = new ArrayList<>();
        boolean found = false;

        try (Scanner sc = new Scanner(file)) {
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                if (line.trim().isEmpty()) continue;
                String[] parts = line.split(",");
                if (parts[0].equals(username)) {
                    found = true; // silinəcək
                } else {
                    lines.add(line); // saxla
                }
            }
        }

        if (!found) throw new UserNotFoundException("İstifadəçi tapılmadı: " + username);

        // Faylı yenilə
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
            for (String l : lines) {
                bw.write(l);
                bw.newLine();
            }
        }
    }

    // --- Bütün istifadəçiləri oxu ---
    public static List<User> getAllUsers() throws IOException {
        List<User> users = new ArrayList<>();
        File file = new File(FILE_NAME);
        if (!file.exists()) return users;

        try (Scanner sc = new Scanner(file)) {
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                if (line.trim().isEmpty()) continue;
                String[] parts = line.split(",");
                if (parts.length < 3) continue;
                users.add(new User(parts[0], parts[2])); // username, role
            }
        }

        return users;
    }
}
