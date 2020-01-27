package udemy.Controllers;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import udemy.auth.AuthModel;

import java.time.Duration;
import java.util.Date;

public class JWTController {

    final static Algorithm algorithm = Algorithm.HMAC256(Secrets.JWT_SECRET_KEY);

    public static boolean authorizeAuthModel(AuthModel model){
        String jwt = generateJWTToken(model.iat, model.exp, model.id, model.role);
        String temp[] = jwt.split("\\.");
        String authToken = temp[temp.length - 1];
        return authToken.equals(model.authToken);
    }

    private static String generateJWTToken(Date iat, Date exp, String id, String role){
        return JWT.create()
                .withIssuedAt(iat)
                .withExpiresAt(exp)
                .withClaim("id", id)
                .withClaim("role", role)
                .sign(algorithm);
    }

    public static AuthModel generateAuthModel(String id, String role){
        Date iat = new Date();
        Date exp = Date.from(new Date().toInstant().plus(Duration.ofMinutes(60)));
        String jwt = generateJWTToken(iat, exp, id, role);
        String temp[] = jwt.split("\\.");
        String authToken = temp[temp.length - 1];
        return new AuthModel(iat, exp, id, role, authToken);
    }
}
