package Services;

import DatabaseManager.DatabaseManager;
import modules.User;
import Repository.UserRepository;

import java.sql.Timestamp;
import java.util.*;

public class UserService {
    private UserRepository manager;
    private Audit log;

    public UserService() {
        this.manager = UserRepository.getInstance();
        this.log = Audit.getInstance();
    }

    public User findById(int id, DatabaseManager db) {
        log.log("Find user by id");
        return manager.findById(id, db);
    }

    public List<User> findAll(DatabaseManager db) {
        log.log("Find all users");
        return manager.findAll(db);
    }

    public void create(int id, String name, String pass, String email, double rating, Timestamp createdAt, DatabaseManager db) {
        User user = new User(id, name, pass, email, rating, createdAt);
        log.log("Creadted User " + user);
        manager.create(user, db);
    }

    public void update(int id, String name, String pass, String email, double rating, Timestamp createdAt, DatabaseManager db) {
        User user = new User(id, name, pass, email, rating, createdAt);
        if (manager.findById(id, db) == null) {
            log.log("invalid update operation " + id);
            return;
        }
        log.log("Updated user with id = " + user.getUserId() + " new user now is " + user);
        manager.update(user, db);
    }

    public void delete(int id, DatabaseManager db) {
        User user = manager.findById(id, db);
        if (user == null) {
            log.log("invalid delete operation " + id);
            return;
        }
        log.log("Deleted user with id = " + id);
        manager.delete(id, db);
    }
}