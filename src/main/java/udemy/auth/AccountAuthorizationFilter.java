package udemy.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import udemy.Controllers.AccountController;
import udemy.Controllers.JWTController;

import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.ext.Provider;
import java.io.IOException;


@Provider
@PreMatching
@Priority(Priorities.AUTHORIZATION)
public class AccountAuthorizationFilter implements ContainerRequestFilter {

    private AccountController accountController;

    public AccountAuthorizationFilter() {
    }


    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        if(requestContext.getHeaders().containsKey("authorization")){
            String authString = requestContext.getHeaders().getFirst("authorization");
            String schema = requestContext.getUriInfo().getRequestUri().getScheme();
            AuthModel authModel = new ObjectMapper().readValue(authString, AuthModel.class);
            if(JWTController.authorizeAuthModel(authModel)){
                AuthUser user = new AuthUser(authModel.id, authModel.role);
                SecurityContext securityContext = new AccountSecurityContext(user, schema);
                requestContext.setSecurityContext(securityContext);
            }
            return;
        }
    }
}
