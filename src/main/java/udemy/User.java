package udemy;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;
import java.security.Principal;

public class User implements Principal {

    @JsonProperty
    private String email;
    @JsonProperty
    private String password;
    @JsonProperty
    private String userrole;

    public User(String email, String password, String userrole) {
        this.email = email;
        this.password = password;
        this.userrole = userrole;
    }

    public User() {
    }

    public void setUserRole(String userrole) {
        this.userrole = userrole;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @JsonIgnore
    @Override
    public String getName() {
        return null;
    }

    public String getUserrole() {
        return userrole;
    }
}
