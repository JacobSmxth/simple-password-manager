package domain;

import java.time.LocalDateTime;

public class Account {
    private int id;
    private String site;
    private String username;
    private String password; // Password managers don't hash passwords as they need to be seen by users
    private String lastChange; // So gson can properly add timestamps

    public Account(String site, String username, String password) {
        this.site = site;
        this.username = username;
        this.password = password;
        this.lastChange = LocalDateTime.now().toString();
    }

    public int getId() {return id;}
    public String getSite() {return site;}
    public String getUsername() {return username;}
    public String getPassword() {return password;}
    public String getLastChange() {return lastChange;}

    public void setId(int id) {this.id = id;}
    public void setSite(String site) {this.site = site;}
    public void setUsername(String username) {this.username = username;}
    public void setPassword(String password) {this.password = password;}
    public void setLastChange(String lastChange) {this.lastChange = lastChange;}
}
