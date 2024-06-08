package Repository;

import DatabaseManager.DatabaseManager;
import modules.Author;
import java.sql.*;
import java.util.*;

public class AuthorRepository implements RepositoryInterface<Author> {
    private static AuthorRepository instance = null;

    private AuthorRepository() {}
    public static AuthorRepository getInstance() {
        if (instance == null) {
            instance = new AuthorRepository();
        }
        return instance;
    }

    private Author createAuthor(ResultSet res) throws SQLException {
        int id = res.getInt("id");
        String firstname = res.getString("firstname");
        String lastname = res.getString("lastname");
        String instit = res.getString("instit");
        return new Author(id, firstname, lastname, instit);
    }

    @Override
    public void create(Author aut, DatabaseManager db) {
        String s = "INSERT INTO pao.authors (firstname, lastname, instit) " +
                "VALUES('" + aut.getFirstName() + "', '"
                + aut.getLastName() + "', '"
                + aut.getInstitution() + "')";
        db.executeUpdate(s);
    }

    @Override
    public Author findById(int id, DatabaseManager db) {
        String query = "SELECT * FROM pao.authors WHERE id = " + id;
        ResultSet user = db.executeQuery(query);
        if (user != null) {
            try {
                if (user.next()) {
                    return createAuthor(user);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public List<Author> findAll(DatabaseManager db) {
        String query = "SELECT * FROM pao.authors";
        ResultSet users = db.executeQuery(query);
        List<Author> ls = new ArrayList<>();
        if (users != null) {
            try {
                while (users.next()) {
                    ls.add(createAuthor(users));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return ls;
    }

    @Override
    public void update(Author toAdd, DatabaseManager db) {
        String query = "UPDATE pao.authors SET firstname = ?, lastname = ?, instit = ? WHERE id = ?";
        PreparedStatement stmt = db.getStmt(query);
        try {
            stmt.setString(1, toAdd.getFirstName());
            stmt.setString(2, toAdd.getLastName());
            stmt.setString(3, toAdd.getInstitution());
            stmt.setInt(4, toAdd.getUserId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id, DatabaseManager db) {
        String query = "DELETE FROM pao.authors WHERE used = " + id;
        db.executeUpdate(query);
    }
}
