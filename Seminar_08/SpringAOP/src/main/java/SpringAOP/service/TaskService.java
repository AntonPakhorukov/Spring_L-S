package SpringAOP.service;

import SpringAOP.exception.CustomException;
import SpringAOP.model.Task;
import SpringAOP.repository.TaskRepository;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.Comparator;
import java.util.List;
import java.util.OptionalLong;
import java.util.stream.Collectors;

/**
 * 1. Добавление задачи.
 * 2. Просмотр всех задач.
 * 3. Просмотр задач по статусу (например, "завершена", "в процессе", "не начата").
 * 4. Изменение статуса задачи.
 * 5. Удаление задачи.
 */
@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;

    /**
     * Транзакции
     */

    @Transactional
    public void addTwoTasks(Task task1, Task task2){
        taskRepository.save(task1);
        taskRepository.save(task2);
    }
    @Transactional(rollbackFor = CustomException.class)
    public void someTransactionalMethod(){
        //на какой-то код
    }

    public Task createTask(Task task) {
        task.setDate(String.valueOf(Calendar.getInstance().getTime()));
        return taskRepository.save(task);
    }

    public List<Task> getAllTask() {
        return taskRepository.findAll();
    }

    public Task getTaskById(Long id) {
        return taskRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("No task with id: " + id));
    }

    public List<Task> filterByStatus(Task.Status status) {
        return taskRepository.findAll().stream()
                .filter(task -> task.getStatus().equals(status))
                .collect(Collectors.toList());
    }

    public Task updateTaskByStatus(Long id, Task task) {
        taskRepository.findById(id).get().setStatus(task.getStatus());
        return taskRepository.save(taskRepository.findById(id).get());
    }

    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }

    public List<Task> sortById() {
        return taskRepository
                .findAll()
                .stream()
                .sorted(Comparator.comparing(Task::getId))
                .collect(Collectors.toList());
    }

    public String setDescription(Long id, String description) {
        Task tempTask = taskRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Task not found"));
        tempTask.setDescription(description);
        taskRepository.save(tempTask);
        return description;
    }


}
