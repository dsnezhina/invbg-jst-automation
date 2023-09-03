package api.dto;

public class Credentials {
    public String email;
    public String password;
    public String domain;

    public Credentials(String email, String password, String domain) {
        this.email = email;
        this.password = password;
        this.domain = domain;
    }
}
