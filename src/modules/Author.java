package modules;

public class Author {
    private int userId;
    private String firstName;
    private String lastName;
    private String institution;

    public Author(int userId, String firstName, String lastName, String institution) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.institution = institution;
    }

    public int getUserId() {
        return userId;
    }
    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public String getInstitution() {
        return institution;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public void setInstitution(String institution) {
        this.institution = institution;
    }

    @Override
    public String toString() {
        return "Author[" +
                "userId=" + userId +
                "firstName=" + firstName +
                "lastName=" + lastName +
                "institution=" + institution + "]";
    }
}
