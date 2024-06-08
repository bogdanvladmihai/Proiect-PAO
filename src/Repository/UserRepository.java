package Repository;

import modules.User;
import DatabaseManager.DatabaseManager;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepository implements RepositoryInterface<User> {
    private static UserRepository instance = null;

    private UserRepository() {}
    public static UserRepository getInstance() {
        if (instance == null) {
            instance = new UserRepository();
        }
        return instance;
    }

    private User createUser(ResultSet res) throws SQLException {
        int id = res.getInt("id");
        String user = res.getString("user");
        String pass = res.getString("pass");
        String email = res.getString("email");
        double rating = res.getDouble("rating");
        Timestamp ts = res.getTimestamp("created");
        return new User(id, user, pass, email, rating, ts);
    }

    @Override
    public void create(User user, DatabaseManager db) {
        String s = "INSERT INTO pao.users (user, pass, email, created) " +
                "VALUES('" + user.getUsername() + "', '"
                + user.getPassowrd() + "', '"
                + user.getEmail() + "', '"
                + "CURRENT_TIMESTAMP" + "')";
        db.executeUpdate(s);
    }

    @Override
    public User findById(int id, DatabaseManager db) {
        String query = "SELECT * FROM pao.users WHERE id = " + id;
        ResultSet user = db.executeQuery(query);
        if (user != null) {
            try {
                if (user.next()) {
                    return createUser(user);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public List<User> findAll(DatabaseManager db) {
        String query = "SELECT * FROM pao.users";
        ResultSet users = db.executeQuery(query);
        List<User> ls = new ArrayList<>();
        if (users != null) {
            try {
                while (users.next()) {
                    ls.add(createUser(users));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return ls;
    }

    @Override
    public void update(User toAdd, DatabaseManager db) {
        String query = "UPDATE pao.users SET user = ?, pass = ?, email = ?, rating = ?, created_at = ? WHERE id = ?";
        PreparedStatement stmt = db.getStmt(query);
        try {
            stmt.setString(1, toAdd.getUsername());
            stmt.setString(2, toAdd.getPassowrd());
            stmt.setString(3, toAdd.getEmail());
            stmt.setDouble(4, toAdd.getRating());
            stmt.setTimestamp(5, toAdd.getCreatedAt());
            stmt.setInt(6, toAdd.getUserId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id, DatabaseManager db) {
        String query = "DELETE FROM pao.users WHERE used = " + id;
        db.executeUpdate(query);
    }
}
