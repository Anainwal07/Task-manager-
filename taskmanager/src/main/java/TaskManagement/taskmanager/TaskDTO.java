package TaskManagement.taskmanager;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class TaskDTO {

    private Integer id;
    @NotEmpty(message = "The task is required")
    private String task;

    @NotEmpty(message = "The priority is required")
    private String priority;

    @Size(min = 10, message = "The description should be at least 10 characters")
    @Size(max = 150, message = "The description cannot be more than 150 characters")
    private String description;

    private String status = "Pending";

    // Getters and Setters
    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }
}
