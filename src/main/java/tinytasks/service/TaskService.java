package tinytasks.service;


import tinytasks.model.Task;
import tinytasks.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Handles the business logic for tasks.
 */
@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public Task createTask(String title, String description) {
        if (title == null || title.trim().length() < 3) {
            throw new IllegalArgumentException("Title is required and must be at least 3 characters long.");
        }
        Task task = new Task(null, title.trim(), description != null ? description.trim() : "", false);
        return taskRepository.save(task);
    }

    public Optional<Task> toggleTask(Long id) {
        Optional<Task> optionalTask = taskRepository.findById(id);
        optionalTask.ifPresent(task -> {
            task.setDone(!task.isDone());
            taskRepository.save(task);
        });
        return optionalTask;
    }

    public boolean deleteTask(Long id) {
        return taskRepository.delete(id);
    }
}
