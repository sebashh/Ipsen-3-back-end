package udemy.core.models;

import com.fasterxml.jackson.annotation.JsonProperty;
//import at.favre.lib.crypto.bcrypt.BCrypt;
import javax.validation.constraints.NotNull;

public class UserModel {

    @NotNull
    @JsonProperty("email")
    private String email;
    @NotNull
    @JsonProperty("password")
    private String encryptedPassword;

    public UserModel(String email, String encryptedPassword) {
        this.email = email;
        this.encryptedPassword = encryptedPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEncryptedPassword() {
        return encryptedPassword;
    }

//    public String setEncryptedPassword(String password) {
//        this.encryptedPassword = BCrypt.with(BCrypt.Version.VERSION_2Y).hashToString(12, getEncryptedPassword().toCharArray());
//        ;
//        return password;
//    }
}
