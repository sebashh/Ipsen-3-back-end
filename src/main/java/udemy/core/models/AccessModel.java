package udemy.core.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;

public class AccessModel {
    @NotNull
    @JsonProperty
    public String email;
    @NotNull
    @JsonProperty
    public int id;


    public AccessModel(String userEmail, int teacher) {
        this.email = userEmail;
        this.id = teacher;
    }

    public AccessModel(){

    }
}
