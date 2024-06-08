package modules;
import java.sql.Timestamp;

public class User {
    protected int userId;
    protected String username;
    protected String passowrd;
    protected String email;
    protected double rating;
    protected Timestamp createdAt;

    public User(int userId, String username, String passowrd, String email, double rating, Timestamp createdAt) {
        this.userId = userId;
        this.username = username;
        this.passowrd = passowrd;
        this.email = email;
        this.rating = rating;
        this.createdAt = createdAt;
    }

    public int getUserId() {
        return userId;
    }
    public String getUsername() {
        return username;
    }
    public String getPassowrd() {
        return passowrd;
    }
    public String getEmail() {
        return email;
    }
    public double getRating() {
        return rating;
    }
    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setUser_id(int userId) {
        this.userId = userId;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public void setPassowrd(String passowrd) {
        this.passowrd = passowrd;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setRating(double rating) {
        this.rating = rating;
    }
    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "User " +
                "[userId=" + userId + ", " +
                "username=" + username + ", " +
                "passowrd=" + passowrd + ", " +
                "email=" + email + ", " +
                "rating=" + rating + ", " +
                "created at=" + createdAt + "]";
    }
}
