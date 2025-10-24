package tinytasks.controller;

import tinytasks.model.Task;
import tinytasks.service.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * REST API for TinyTasks
 */
@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    // GET /api/tasks
    @GetMapping
    public List<Task> getAllTasks() {
        return taskService.getAllTasks();
    }

    // POST /api/tasks
    @PostMapping
    public ResponseEntity<?> createTask(@RequestBody Map<String, String> body) {
        try {
            String title = body.get("title");
            String description = body.get("description");
            Task task = taskService.createTask(title, description);
            return ResponseEntity.status(HttpStatus.CREATED).body(task);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    // PUT /api/tasks/{id}/toggle
    @PutMapping("/{id}/toggle")
    public ResponseEntity<?> toggleTask(@PathVariable Long id) {
        return taskService.toggleTask(id)
                .<ResponseEntity<?>>map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(Map.of("error", "Not found")));
    }

    // DELETE /api/tasks/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTask(@PathVariable Long id) {
        boolean deleted = taskService.deleteTask(id);
        if (deleted) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("error", "Not found"));
        }
    }
}
