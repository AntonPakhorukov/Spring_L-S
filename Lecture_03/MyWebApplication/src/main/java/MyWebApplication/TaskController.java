package MyWebApplication;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/task")
public class TaskController {
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }
    @GetMapping // не указываем адрес, он будет доступен по адресу контроллера
    public List<Task> getAllTasks(){
        return taskService.getAllTasks();
    }
    @GetMapping("/{id}") // дополнительно указываем id для передачи в метод
    public Task getById(@PathVariable UUID id){
        // @PathVariable - указываем, что id нужно взять из пути и подставить в id метода
        // отдельно не указываем название переменной, так как оно совпадает в адресе и в методе
        return taskService.getTask(id);
    }
    @PostMapping
    public Task addById(@RequestBody Task task){
        //@RequestBody - так как задача большая и мы не можем принять ее в пути, будем принимать в теле запроса
        return taskService.addTask(task);
    }
    @PutMapping("/{id}")
    public Task updateById(@PathVariable UUID id, @RequestBody Task task){
        return taskService.updateTask(id, task);
    }
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable UUID id){
        taskService.deleteTask(id);
    }


}

