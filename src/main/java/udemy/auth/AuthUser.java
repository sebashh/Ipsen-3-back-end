package udemy.auth;
import java.security.Principal;

public class AuthUser implements Principal {

    private final String name;
    private final String role;

    public AuthUser(String name, String role)
    {
        this.name = name;
        this.role = role;
    }

    public boolean hasRole(String role){
        return this.role.equals(role);
    }

    @Override
    public String getName() {
        return name;
    }
}
