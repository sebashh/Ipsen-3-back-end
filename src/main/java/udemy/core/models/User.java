package udemy.core.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;

public class User {

    @NotNull
    @JsonProperty
    private Integer id;
    @NotNull
    @JsonProperty
    private String username;
    @NotNull
    @JsonProperty
    private String user;


    public User(Integer id, String username, String password, boolean student, boolean teacher, boolean admin) {
        this.id = id;
        this.username = username;
        user = "GUEST";
        if(student)  user = "USER";
        if(teacher)  user = "TEACHER";
        if(admin) user = "ADMIN";

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}