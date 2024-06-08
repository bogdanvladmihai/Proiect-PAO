package Repository;

import modules.Task;
import DatabaseManager.DatabaseManager;

import java.sql.*;
import java.util.*;

public class TaskRepository implements RepositoryInterface<Task> {
    private static TaskRepository instance = null;

    private TaskRepository() {}

    public static TaskRepository getInstance() {
        if (instance == null) {
            instance = new TaskRepository();
        }
        return instance;
    }

    private Task createTask(ResultSet res) throws SQLException {
        int id = res.getInt("id");
        String name = res.getString("name");
        String description = res.getString("description");
        String author = res.getString("author");
        String contest = res.getString("contest");
        int dif = res.getInt("dif");
        Timestamp tm = res.getTimestamp("created");
        return new Task(id, name, description, author, contest, dif, tm);
    }

    @Override
    public void create(Task task, DatabaseManager db) {
        String s = "INSERT INTO pao.tasks (name, description, author, contest, dif, created) VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement stmt = db.getStmt(s);
        try {
            stmt.setString(1, task.getName());
            stmt.setString(2, task.getDescription());
            stmt.setString(3, task.getAuthor());
            stmt.setString(4, task.getContest());
            stmt.setInt(5, task.getDifficulty());
            stmt.setTimestamp(6, task.getCreatedAt());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Task findById(int id, DatabaseManager db) {
        String query = "SELECT * FROM pao.tasks WHERE id = " + id;
        ResultSet user = db.executeQuery(query);
        if (user != null) {
            try {
                if (user.next()) {
                    return createTask(user);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public List<Task> findAll(DatabaseManager db) {
        String query = "SELECT * FROM pao.tasks";
        ResultSet users = db.executeQuery(query);
        List<Task> ls = new ArrayList<>();
        if (users != null) {
            try {
                while (users.next()) {
                    ls.add(createTask(users));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return ls;
    }

    @Override
    public void update(Task toAdd, DatabaseManager db) {
        String query = "UPDATE pao.tasks SET name = ?, description = ?, author = ?, contest = ?, dif = ?, created = ? WHERE id = ?";
        PreparedStatement stmt = db.getStmt(query);
        try {
            stmt.setString(1, toAdd.getName());
            stmt.setString(2, toAdd.getDescription());
            stmt.setString(3, toAdd.getAuthor());
            stmt.setString(4, toAdd.getContest());
            stmt.setInt(5, toAdd.getDifficulty());
            stmt.setTimestamp(6, toAdd.getCreatedAt());
            stmt.setInt(7, toAdd.getTaskId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id, DatabaseManager db) {
        String query = "DELETE FROM pao.tasks WHERE used = " + id;
        db.executeUpdate(query);
    }
}
