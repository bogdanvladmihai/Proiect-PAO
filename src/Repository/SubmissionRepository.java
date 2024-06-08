package Repository;

import DatabaseManager.DatabaseManager;
import modules.Submission;
import modules.Task;

import java.sql.*;
import java.util.*;

public class SubmissionRepository implements RepositoryInterface<Submission> {
    private static SubmissionRepository instance = null;

    private SubmissionRepository() {}

    public static SubmissionRepository getInstance() {
        if (instance == null) {
            instance = new SubmissionRepository();
        }
        return instance;
    }

    private Submission createSubmission(ResultSet res) throws SQLException {
        int id = res.getInt("id");
        int user = res.getInt("user");
        int problem = res.getInt("problem");
        Timestamp tm = res.getTimestamp("time");
        String ver = res.getString("verdict");
        return new Submission(id, user, problem, tm, ver);
    }

    @Override
    public void create(Submission sub, DatabaseManager db) {
        String s = "INSERT INTO pao.submissions (user, problem, time, verdict) VALUES (?, ?, ?, ?)";
        PreparedStatement stmt = db.getStmt(s);
        try {
            stmt.setInt(1, sub.getUserId());
            stmt.setInt(2, sub.getProblemId());
            stmt.setTimestamp(3, sub.getSubmissionTime());
            stmt.setString(4, sub.getVerdict());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Submission findById(int id, DatabaseManager db) {
        String query = "SELECT * FROM pao.submissions WHERE id = " + id;
        ResultSet user = db.executeQuery(query);
        if (user != null) {
            try {
                if (user.next()) {
                    return createSubmission(user);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public List<Submission> findAll(DatabaseManager db) {
        String query = "SELECT * FROM pao.submissions";
        ResultSet users = db.executeQuery(query);
        List<Submission> ls = new ArrayList<>();
        if (users != null) {
            try {
                while (users.next()) {
                    ls.add(createSubmission(users));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return ls;
    }

    @Override
    public void update(Submission toAdd, DatabaseManager db) {
        String query = "UPDATE pao.submissions SET user = ?, problem = ?, time = ?, verdict = ? WHERE id = ?";
        PreparedStatement stmt = db.getStmt(query);
        try {
            stmt.setInt(1, toAdd.getUserId());
            stmt.setInt(2, toAdd.getProblemId());
            stmt.setTimestamp(3, toAdd.getSubmissionTime());
            stmt.setString(4, toAdd.getVerdict());
            stmt.setInt(5, toAdd.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id, DatabaseManager db) {
        String query = "DELETE FROM pao.submissions WHERE used = " + id;
        db.executeUpdate(query);
    }
}
