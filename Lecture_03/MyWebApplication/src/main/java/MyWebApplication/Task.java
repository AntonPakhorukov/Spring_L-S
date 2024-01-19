package MyWebApplication;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.UUID;

public class Task {
    public enum Status{ // используем enum в качестве статуса задачи
        TO_DO, // сделать
        IN_PROGRESS, // в процессе выполнения
        DONE // выполнено
    }
    private UUID id; // UUID позволяет генерировать id автоматически
    private String name; // имя задачи
    private String description; // описание задачи
    private Status status; // статус задачи
    private LocalDateTime completionTime; // время выполнения задачи

    public Task(String name, String description) {
        this.id = UUID.randomUUID(); // указываем, что каждый раз id будет рандомным
        this.name = name;
        this.description = description;
        this.status = Status.TO_DO;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public LocalDateTime getCompletionTime() {
        return completionTime;
    }

    public void setCompletionTime(LocalDateTime completionTime) {
        this.completionTime = completionTime;
    }
}
