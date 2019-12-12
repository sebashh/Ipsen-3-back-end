package src.api;

import javax.validation.constraints.NotNull;
import java.security.Principal;

public class User implements Principal {

    @NotNull
    private String id;
    private String userRole;
    private String password;
    private String email;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
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

    @Override
    public String getName() {
        return null;
    }

    public String getUserRole() {
        return userRole;
    }
}
