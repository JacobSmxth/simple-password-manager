package dto;

public class AccountUpdateDTO {
    private String site;
    private String username;
    private String password;

    public String getSite() {return site;}
    public String getUsername() {return username;}
    public String getPassword() {return password;}
    public void setSite(String site) {this.site = site;}
    public void setUsername(String username) {this.username = username;}
    public void setPassword(String password) {this.password = password;}
}
