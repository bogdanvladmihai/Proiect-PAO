package Services;

import DatabaseManager.DatabaseManager;
import Repository.UserRepository;
import modules.Task;
import Repository.TaskRepository;
import modules.User;

import java.sql.Timestamp;
import java.util.*;

public class TaskService {
    private TaskRepository manager;
    private Audit log;

    public TaskService() {
        this.manager = TaskRepository.getInstance();
        this.log = Audit.getInstance();
    }

    public Task findById(int id, DatabaseManager db) {
        log.log("Find task by id");
        return manager.findById(id, db);
    }

    public List<Task> findAll(DatabaseManager db) {
        log.log("Find all tasks");
        return manager.findAll(db);
    }

    public void create(int id, String name, String description, String author, String contest, int dif, Timestamp time, DatabaseManager db) {
        Task task = new Task(id, name, description, author, contest, dif, time);
        log.log("Creadted task " + task);
        manager.create(task, db);
    }

    public void update(int id, String name, String description, String author, String contest, int dif, Timestamp tm, DatabaseManager db) {
        Task task = new Task(id, name, description, author, contest, dif, tm);
        if (manager.findById(id, db) == null) {
            log.log("invalid update operation " + id);
            return;
        }
        log.log("Updated task with id = " + task.getTaskId() + " new user now is " + task);
        manager.update(task, db);
    }

    public void delete(int id, DatabaseManager db) {
        Task task = manager.findById(id, db);
        if (task == null) {
            log.log("invalid delete operation " + id);
            return;
        }
        log.log("Deleted task with id = " + id);
        manager.delete(id, db);
    }
}
