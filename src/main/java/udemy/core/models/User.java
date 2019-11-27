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
    private String password;
    @JsonProperty
    private boolean student;
    @JsonProperty
    private boolean teacher;
    @JsonProperty
    private boolean admin;


    public User(Integer id, String username, String password, boolean student, boolean teacher, boolean admin) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.student = student;
        this.teacher = teacher;
        this.admin = admin;
    }

    public boolean isStudent() {
        return student;
    }

    public void setStudent(boolean student) {
        this.student = student;
    }

    public boolean isTeacher() {
        return teacher;
    }

    public void setTeacher(boolean teacher) {
        this.teacher = teacher;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAdmin() { return admin; }

    public void setAdmin(boolean admin) { admin = admin; }
}
