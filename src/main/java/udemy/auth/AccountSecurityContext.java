package udemy.auth;

import javax.ws.rs.core.SecurityContext;
import java.security.Principal;

public class AccountSecurityContext implements SecurityContext {

    private String schema;
    private AuthUser user;

    public AccountSecurityContext(AuthUser user, String schema) {
        this.user = user;
        this.schema = schema;
    }

    @Override
    public Principal getUserPrincipal() {
        return user;
    }

    @Override
    public boolean isUserInRole(String s) {
        return user.hasRole(s);
    }

    @Override
    public boolean isSecure() {
        return false;
    }

    @Override
    public String getAuthenticationScheme() {
        return schema;
    }
}
