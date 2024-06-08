package Repository;

import com.mysql.cj.protocol.x.ContinuousInputStream;
import modules.Contest;
import DatabaseManager.DatabaseManager;

import java.sql.*;
import java.util.*;

public class ContestRepository implements RepositoryInterface<Contest> {
    private static ContestRepository instance = null;

    private ContestRepository() {}

    public static ContestRepository getInstance() {
        if (instance == null) {
            instance = new ContestRepository();
        }
        return instance;
    }

    private Contest createContest(ResultSet res) throws SQLException {
        int id = res.getInt("id");
        String name = res.getString("name");
        Timestamp tm = res.getTimestamp("start");
        double duration = res.getDouble("duration");
        return new Contest(id, name, tm, duration);
    }

    @Override
    public void create(Contest con, DatabaseManager db) {
        String s = "INSERT INTO pao.contest (name, start, duration) VALUES (?, ?, ?)";
        PreparedStatement stmt = db.getStmt(s);
        try {
            stmt.setString(1, con.getName());
            stmt.setTimestamp(2, con.getStartTime());
            stmt.setDouble(3, con.getDuration());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Contest findById(int id, DatabaseManager db) {
        String query = "SELECT * FROM pao.contests WHERE id = " + id;
        ResultSet user = db.executeQuery(query);
        if (user != null) {
            try {
                if (user.next()) {
                    return createContest(user);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public List<Contest> findAll(DatabaseManager db) {
        String query = "SELECT * FROM pao.contests";
        ResultSet users = db.executeQuery(query);
        List<Contest> ls = new ArrayList<>();
        if (users != null) {
            try {
                while (users.next()) {
                    ls.add(createContest(users));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return ls;
    }

    @Override
    public void update(Contest toAdd, DatabaseManager db) {
        String query = "UPDATE pao.contests SET name = ?, start = ?, duration = ? WHERE id = ?";
        PreparedStatement stmt = db.getStmt(query);
        try {
            stmt.setString(1, toAdd.getName());
            stmt.setTimestamp(2, toAdd.getStartTime());
            stmt.setDouble(3, toAdd.getDuration());
            stmt.setInt(4, toAdd.getContestId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id, DatabaseManager db) {
        String query = "DELETE FROM pao.contests WHERE used = " + id;
        db.executeUpdate(query);
    }
}
