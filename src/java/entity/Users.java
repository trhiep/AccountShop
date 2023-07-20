package entity;

/**
 *
 * @author tranh
 */
public class Users {
    
    private String username;
    private String password;
    private String fullname;
    private String email;
    private int role;
    private boolean isVerified;

    public Users() {
    }

    public Users(String username, String password, String fullname, String email, int role, boolean isVerified) {
        this.username = username;
        this.password = password;
        this.fullname = fullname;
        this.email = email;
        this.role = role;
        this.isVerified = isVerified;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public boolean isIsVerified() {
        return isVerified;
    }

    public void setIsVerified(boolean isVerified) {
        this.isVerified = isVerified;
    }

    @Override
    public String toString() {
        return "Users{" + "username=" + username + ", password=" + password + ", fullname=" + fullname + ", email=" + email + ", role=" + role + ", isVerified=" + isVerified + '}';
    }

}
