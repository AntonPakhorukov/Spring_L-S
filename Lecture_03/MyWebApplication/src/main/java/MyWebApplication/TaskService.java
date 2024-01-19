package MyWebApplication;

import org.springframework.stereotype.Service;
import org.yaml.snakeyaml.util.EnumUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class TaskService {
    private final List<Task> tasks = new ArrayList<>();
    public List<Task> getAllTasks(){
        return tasks;
    }
    public Task getTask(UUID uuid) {
        return tasks.stream().filter(task -> task.getId().equals(uuid)).findFirst().orElse(null);
    }
    public Task addTask(Task task){
        tasks.add(task);
        return task;
    }
    public void deleteTask(UUID uuid){
        tasks.removeIf(task -> task.getId().equals(uuid)); // удаляет элемент из коллекции по определенному условию
    }
    public Task updateTask(UUID uuid, Task task){
        Task actualTask = getTask(uuid);
        if (actualTask != null) {
            actualTask.setDescription(task.getDescription());
            actualTask.setName(task.getName());
            actualTask.setStatus(task.getStatus());
            actualTask.setCompletionTime(task.getCompletionTime());
        }
        return actualTask;
    }
}
