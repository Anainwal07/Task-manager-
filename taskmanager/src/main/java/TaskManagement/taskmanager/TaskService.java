package TaskManagement.taskmanager;

import java.util.List;
import java.util.Optional;

public interface TaskService {
    List<Task> findAll();

    void save(Task task);

    void delete(Task task);

    Optional<Task> findById(Integer id);

    void update(Task task);

}
