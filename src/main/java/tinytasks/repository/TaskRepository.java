package tinytasks.repository;

import tinytasks.model.Task;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

/**
 * In-memory repository for tasks.
 * Simulates a database using a HashMap.
 */
@Repository
public class TaskRepository {

    private final Map<Long, Task> tasks = new HashMap<>();
    private final AtomicLong counter = new AtomicLong(0);

    // Get all tasks
    public List<Task> findAll() {
        return new ArrayList<>(tasks.values());
    }

    // Find by ID
    public Optional<Task> findById(Long id) {
        return Optional.ofNullable(tasks.get(id));
    }

    // Save (create or update)
    public Task save(Task task) {
        if (task.getId() == null) {
            task.setId(counter.incrementAndGet());
        }
        tasks.put(task.getId(), task);
        return task;
    }

    // Delete by ID
    public boolean delete(Long id) {
        return tasks.remove(id) != null;

    }
}