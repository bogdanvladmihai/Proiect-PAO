package Repository;

import modules.Admin;
import DatabaseManager.DatabaseManager;

import java.sql.*;
import java.util.*;

public class AdminRepository implements RepositoryInterface<Admin> {
    private static AdminRepository instance = null;

    private AdminRepository() {}
    public static AdminRepository getInstance() {
        if (instance == null) {
            instance = new AdminRepository();
        }
        return instance;
    }

    private Admin createAdmin(ResultSet res) throws SQLException {
        int id = res.getInt("id");
        String user = res.getString("user");
        String pass = res.getString("pass");
        String email = res.getString("email");
        double rating = res.getDouble("rating");
        Timestamp ts = res.getTimestamp("created");
        String adminRole = res.getString("role");
        return new Admin(id, user, pass, email, rating, ts, adminRole);
    }

    @Override
    public void create(Admin user, DatabaseManager db) {
        String s = "INSERT INTO pao.admins (user, pass, email, role, created) " +
                "VALUES('" + user.getUsername() + "', '"
                + user.getPassowrd() + "', '"
                + user.getEmail() + "', '"
                + user.getAdminRole() + "', '"
                + "CURRENT_TIMESTAMP" + "')";
        db.executeUpdate(s);
    }

    @Override
    public Admin findById(int id, DatabaseManager db) {
        String query = "SELECT * FROM pao.admins WHERE id = " + id;
        ResultSet user = db.executeQuery(query);
        if (user != null) {
            try {
                if (user.next()) {
                    return createAdmin(user);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public List<Admin> findAll(DatabaseManager db) {
        String query = "SELECT * FROM pao.admins";
        ResultSet users = db.executeQuery(query);
        List<Admin> ls = new ArrayList<>();
        if (users != null) {
            try {
                while (users.next()) {
                    ls.add(createAdmin(users));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return ls;
    }

    @Override
    public void update(Admin toAdd, DatabaseManager db) {
        String query = "UPDATE pao.admins SET user = ?, pass = ?, email = ?, rating = ?, created_at = ?, role = ? WHERE id = ?";
        PreparedStatement stmt = db.getStmt(query);
        try {
            stmt.setString(1, toAdd.getUsername());
            stmt.setString(2, toAdd.getPassowrd());
            stmt.setString(3, toAdd.getEmail());
            stmt.setDouble(4, toAdd.getRating());
            stmt.setTimestamp(5, toAdd.getCreatedAt());
            stmt.setString(6, toAdd.getAdminRole());
            stmt.setInt(7, toAdd.getUserId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id, DatabaseManager db) {
        String query = "DELETE FROM pao.admins WHERE used = " + id;
        db.executeUpdate(query);
    }
}
