package Services;

import DatabaseManager.DatabaseManager;
import Repository.ContestRepository;
import Repository.SubmissionRepository;
import modules.Admin;
import Repository.AdminRepository;
import modules.Contest;
import modules.Submission;
import modules.Task;

import java.sql.Timestamp;
import java.util.*;

public class ContestService {
    private ContestRepository manager;
    private Audit log;

    public ContestService() {
        this.manager = ContestRepository.getInstance();
        this.log = Audit.getInstance();
    }

    public Contest findById(int id, DatabaseManager db) {
        log.log("Find contest by id");
        return manager.findById(id, db);
    }

    public List<Contest> findAll(DatabaseManager db) {
        log.log("Find all contests");
        return manager.findAll(db);
    }

    public void create(int id, String name, Timestamp tm, double duration, DatabaseManager db) {
        Contest con = new Contest(id, name, tm, duration);
        log.log("Creadted contest " + con);
        manager.create(con, db);
    }

    public void update(int id, String name, Timestamp tm, double duration, DatabaseManager db) {
        Contest con = new Contest(id, name, tm, duration);
        if (manager.findById(id, db) == null) {
            log.log("invalid update operation " + id);
            return;
        }
        log.log("Updated contest with id = " + con.getContestId() + " new user now is " + con);
        manager.update(con, db);
    }

    public void delete(int id, DatabaseManager db) {
        Contest sub = manager.findById(id, db);
        if (sub == null) {
            log.log("invalid delete operation " + id);
            return;
        }
        log.log("Deleted contest with id = " + id);
        manager.delete(id, db);
    }
}
