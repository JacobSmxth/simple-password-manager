import java.time.LocalDateTime;

public class Account {
    private String site;
    private String username;
    private String password; // Password managers don't hash passwords as they need to be seen by users
    private String addedAt; // So gson can properly add timestamps

    public Account(String site, String username, String password) {
        this.site = site;
        this.username = username;
        this.password = password;
        this.addedAt = LocalDateTime.now().toString();
    }

    public String getSite() {return site;}
    public String getUsername() {return username;}
    public String getPassword() {return password;}
    public String getAddedAt() {return addedAt;}

    public void setSite(String site) {this.site = site;}
    public void setUsername(String username) {this.username = username;}
    public void setPassword(String password) {this.password = password;}
    public void setAddedAt(String addedAt) {this.addedAt = addedAt;}
}
