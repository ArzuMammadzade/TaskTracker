package tasktracker;

import java.io.*;
import java.time.LocalDate;
import java.util.*;

public class TaskManager {
    private static final String FILE_NAME = "tasks.txt";

    public static void addTask(Task task) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
            bw.write(task.getId() + "," + task.getName() + "," + task.getAssignedTo() + "," +
                    task.getStatus() + "," + task.getDeadline() + "\n");
        }
    }

    public static List<Task> getAllTasks() throws IOException {
        List<Task> tasks = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                tasks.add(new Task(
                        Integer.parseInt(parts[0]),
                        parts[1],
                        parts[2],
                        parts[3],
                        LocalDate.parse(parts[4])
                ));
            }
        }
        return tasks;
    }

    public static void markTaskCompleted(int tid) {
        
    }

    // markCompleted, assignTask methodları əlavə ediləcək
}
