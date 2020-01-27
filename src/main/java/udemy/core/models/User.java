package udemy.core.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import javassist.bytecode.ByteArray;

import javax.validation.constraints.NotNull;
import java.util.List;

public class User {

    @JsonProperty
    public Integer study;
    @JsonProperty
    public ByteArray picture_company;
    @JsonProperty
    public String name_company;
    @NotNull
    @JsonProperty
    public List<Integer> categories;
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

    public User(Integer study, ByteArray picture_company, String name_company, String description_company, String email_user, String password_user) {
        this.study = study;
        this.picture_company = picture_company;
        this.name_company = name_company;
        this.description_company = description_company;
        this.email_user = email_user;
        this.password_user = password_user;
    }
}
