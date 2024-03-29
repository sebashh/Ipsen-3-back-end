package udemy.core.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;

public class ExtendThisUser {
    @NotNull
    @JsonProperty("id")
    public int id;
    @NotNull
    @JsonProperty("email")
    public String email;
    @NotNull
    @JsonProperty("password")
    public String password;
    @JsonProperty
    public String lastLogin;
//

    ExtendThisUser(){}

    public ExtendThisUser(int id, String email, String password, String lastLogin){
        this.id = id;
        this.email = email;
        this.password = password;
        this.lastLogin = lastLogin.substring(0, 10);
    }
}
