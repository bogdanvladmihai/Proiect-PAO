package Services;

import DatabaseManager.DatabaseManager;
import Repository.AdminRepository;
import modules.Admin;

import java.sql.Timestamp;
import java.util.*;

public class AdminService {
    private AdminRepository manager;
    private Audit log;

    public AdminService() {
        this.manager = AdminRepository.getInstance();
        this.log = Audit.getInstance();
    }

    public Admin findById(int id, DatabaseManager db) {
        log.log("Find admin by id");
        return manager.findById(id, db);
    }

    public List<Admin> findAll(DatabaseManager db) {
        log.log("Find all admins");
        return manager.findAll(db);
    }

    public void create(int id, String name, String pass, String email, double rating, Timestamp createdAt, String role, DatabaseManager db) {
        Admin user = new Admin(id, name, pass, email, rating, createdAt, role);
        log.log("Creadted admin " + user);
        manager.create(user, db);
    }

    public void update(int id, String name, String pass, String email, double rating, Timestamp createdAt, String role, DatabaseManager db) {
        Admin user = new Admin(id, name, pass, email, rating, createdAt, role);
        if (manager.findById(id, db) == null) {
            log.log("invalid update operation " + id);
            return;
        }
        log.log("Updated admin with id = " + user.getUserId() + " new user now is " + user);
        manager.update(user, db);
    }

    public void delete(int id, DatabaseManager db) {
        Admin user = manager.findById(id, db);
        if (user == null) {
            log.log("invalid delete operation " + id);
            return;
        }
        log.log("Deleted admin with id = " + id);
        manager.delete(id, db);
    }
}
