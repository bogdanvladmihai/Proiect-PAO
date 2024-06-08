package DatabaseManager;

import java.sql.*;

public class DatabaseManager {
    private static final String dbUrl = "jdbc:mysql://localhost:3306";
    private static final String dbUser = "root";
    private static final String dbPass = "";
    private static Connection connection;
    private static DatabaseManager instance = null;

    private DatabaseManager() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(dbUrl, dbUser, dbPass);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static DatabaseManager getInstance() {
        if (instance == null) {
            instance = new DatabaseManager();
        }
        return instance;
    }

    public ResultSet executeQuery(String query)
    {
        try {
            PreparedStatement stnt = connection.prepareStatement(query);
            return stnt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void executeUpdate(String query)
    {
        try {
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public PreparedStatement getStmt(String query) {
        try {
            PreparedStatement stm = connection.prepareStatement(query);
            return stm;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
