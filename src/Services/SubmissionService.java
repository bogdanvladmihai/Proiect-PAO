package Services;

import DatabaseManager.DatabaseManager;
import Repository.SubmissionRepository;
import modules.Submission;
import modules.User;

import java.sql.Timestamp;
import java.util.*;

public class SubmissionService {
    private SubmissionRepository manager;
    private Audit log;

    public SubmissionService() {
        this.manager = SubmissionRepository.getInstance();
        this.log = Audit.getInstance();
    }

    public Submission findById(int id, DatabaseManager db) {
        log.log("Find submission by id");
        return manager.findById(id, db);
    }

    public List<Submission> findAll(DatabaseManager db) {
        log.log("Find all submission");
        return manager.findAll(db);
    }

    public void create(int id, int userId, int problemId, Timestamp submissionTime, String verdict, DatabaseManager db) {
        Submission sub = new Submission(id, userId, problemId, submissionTime, verdict);
        log.log("Creadted Submission " + sub);
        manager.create(sub, db);
    }

    public void update(int id, int userId, int problemId, Timestamp subTime, String verdict, DatabaseManager db) {
        Submission sub = new Submission(id, userId, problemId, subTime, verdict);
        if (manager.findById(id, db) == null) {
            log.log("invalid update operation " + id);
            return;
        }
        log.log("Updated Submission with id = " + sub.getUserId() + " new user now is " + sub);
        manager.update(sub, db);
    }

    public void delete(int id, DatabaseManager db) {
        Submission sub = manager.findById(id, db);
        if (sub == null) {
            log.log("invalid delete operation " + id);
            return;
        }
        log.log("Deleted Submission with id = " + id);
        manager.delete(id, db);
    }
}
