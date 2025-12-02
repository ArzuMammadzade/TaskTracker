package tasktracker;

import java.time.LocalDate;

public final class Task {
    private final int id;
    private final String name;
    private final String assignedTo;
    private final String status; // e.g., "Pending", "Completed"
    private final LocalDate deadline;

    public Task(int id, String name, String assignedTo, String status, LocalDate deadline) {
        this.id = id;
        this.name = name;
        this.assignedTo = assignedTo;
        this.status = status;
        this.deadline = deadline;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getAssignedTo() { return assignedTo; }
    public String getStatus() { return status; }
    public LocalDate getDeadline() { return deadline; }

    @Override
    public String toString() {
        return id + " | " + name + " | " + assignedTo + " | " + status + " | " + deadline;
    }
}
