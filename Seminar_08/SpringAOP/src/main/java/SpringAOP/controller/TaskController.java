package SpringAOP.controller;

import SpringAOP.model.Task;
import SpringAOP.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
public class TaskController {
    @Autowired
    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/login")
    public String loginEndpoint(){
        return "You need enter login and password";
    }

    @GetMapping("/tasks")
    public List<Task> getAllTask() {
        return taskService.getAllTask();
    }

    @PostMapping("/tasks")
    public Task createTask(@RequestBody Task task) {
        return taskService.createTask(task);
    }

    @GetMapping("/tasks/{id}")
    public Task getTaskById(@PathVariable Long id) {
        return taskService.getTaskById(id);
    }

    @GetMapping("/tasks/filter/{status}")
    public List<Task> filterByStatus(@PathVariable Task.Status status) {
        return taskService.filterByStatus(status);
    }

    @GetMapping("/tasks/sort")
    public List<Task> sortById() {
        return taskService.sortById();
    }

    @PutMapping("/updateStatus/{id}")
    public Task updateTaskByStatus(@PathVariable Long id, @RequestBody Task task) {
        return taskService.updateTaskByStatus(id, task);
    }

    @DeleteMapping("tasks/{id}")
    public void deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
    }

    @PutMapping("tasks/{id}/{description}")
    public String setDescription(@PathVariable Long id, @PathVariable String description){
        return taskService.setDescription(id, description);
    }
    @PostMapping("tasks/")
    public void addTwoTasks(@RequestBody Task task1, @RequestBody Task task2){
        taskService.addTwoTasks(task1, task2);
    }
}