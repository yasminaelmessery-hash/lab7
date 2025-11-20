package Lab_7;


public abstract class User {
    
    private final String id; 
    private final String email;
    private final String hashedPassword;
    private final String role;

    public User(String id, String username , String email, String hashedPassword, String role) {
        this.id = id;
        this.email = email;
        this.hashedPassword = hashedPassword;
        this.role = role;
    }
    
    public String getId() { return id; }
    public String getEmail() { return email; }
    public String getHashedPassword() { return hashedPassword; }
    public String getRole() { return role; }
    public abstract String getDetailSummary();
}