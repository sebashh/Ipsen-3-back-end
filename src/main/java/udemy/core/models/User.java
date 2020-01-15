package udemy.core.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import javassist.bytecode.ByteArray;

import javax.validation.constraints.NotNull;

public class User {

    @JsonProperty
    public String study;
    @JsonProperty
    public ByteArray picture_company;
    @NotNull
    @JsonProperty
    public String name_company;
    @NotNull
    @JsonProperty
    public String description_company;
    @NotNull
    @JsonProperty
    public String email_user;
    @NotNull
    @JsonProperty
    public String password_user;

    public User(){

    }

    public User(String study, ByteArray picture_company, String name_company, String description_company, String email_user, String password_user) {
        this.study = study;
        this.picture_company = picture_company;
        this.name_company = name_company;
        this.description_company = description_company;
        this.email_user = email_user;
        this.password_user = password_user;
    }
}
