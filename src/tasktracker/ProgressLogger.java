package tasktracker;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ProgressLogger {

    public static void addProgress(String username, int taskId, String note) {
        String fileName = "progress_" + username + ".txt";
        File file = new File(fileName);

        try {
            if (!file.exists()) {      // Fayl yoxdursa yarad
                file.createNewFile();
                System.out.println(fileName + " yaradıldı!");
            }

            try (BufferedWriter bw = new BufferedWriter(new FileWriter(file, true))) {
                String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                bw.write(timestamp + " | Task " + taskId + " | " + note + "\n");
            }
        } catch (IOException e) {
            System.out.println("Progress yazılarkən xəta: " + e.getMessage());
        }
    }
}
