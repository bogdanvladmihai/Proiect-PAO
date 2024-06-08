package modules;

import java.sql.Timestamp;

public class Admin extends User {
    private String adminRole;

    public Admin(int id, String username, String password, String email, double rating, Timestamp createdAt, String adminRole) {
        super(id, username, password, email, rating, createdAt);
        this.adminRole = adminRole;
    }

    public String getAdminRole() {
        return adminRole;
    }
    public void setAdminRole(String adminRole) {
        this.adminRole = adminRole;
    }

    @Override
    public String toString() {
        return "Admin " +
                "[userId=" + userId + ", " +
                "username=" + username + ", " +
                "passowrd=" + passowrd + ", " +
                "email=" + email + ", " +
                "rating=" + rating + ", " +
                "created at=" + createdAt + "," +
                "adminRole=" + adminRole + "]";
    }
}