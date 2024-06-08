package Services;

import DatabaseManager.DatabaseManager;
import Repository.ContestRepository;
import Repository.SubmissionRepository;
import Repository.UserRepository;
import modules.Admin;
import Repository.AuthorRepository;
import modules.Author;
import modules.User;

import java.lang.reflect.AnnotatedType;
import java.sql.Timestamp;
import java.util.*;

public class AuthorService {
    private AuthorRepository manager;
    private Audit log;

    public AuthorService() {
        this.manager = AuthorRepository.getInstance();
        this.log = Audit.getInstance();
    }

    public Author findById(int id, DatabaseManager db) {
        log.log("Find author by id");
        return manager.findById(id, db);
    }

    public List<Author> findAll(DatabaseManager db) {
        log.log("Find all author");
        return manager.findAll(db);
    }

    public void create(int id, String firstname, String lastname, String instit, DatabaseManager db) {
        Author auth = new Author(id, firstname, lastname, instit);
        log.log("Creadted author " + auth);
        manager.create(auth, db);
    }

    public void update(int id, String firstname, String lastname, String instit, DatabaseManager db) {
        Author auth = new Author(id, firstname, lastname, instit);
        if (manager.findById(id, db) == null) {
            log.log("invalid update operation " + id);
            return;
        }
        log.log("Updated author with id = " + auth.getUserId() + " new user now is " + auth);
        manager.update(auth, db);
    }

    public void delete(int id, DatabaseManager db) {
        Author auth = manager.findById(id, db);
        if (auth == null) {
            log.log("invalid delete operation " + id);
            return;
        }
        log.log("Deleted author with id = " + id);
        manager.delete(id, db);
    }
}
