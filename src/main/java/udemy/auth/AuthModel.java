package udemy.auth;

import java.util.Date;

public class AuthModel {
    public Date iat;
    public Date exp;
    public String id;
    public String role;
    public String authToken;

    public AuthModel(){

    }

    public AuthModel(Date iat, Date exp, String id, String role, String authToken){
        this.iat = iat;
        this.exp = exp;
        this.id = id;
        this. role = role;
        this. authToken = authToken;

    }
}
