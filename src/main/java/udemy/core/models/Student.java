package udemy.core.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Student extends StudentUser {
    @JsonProperty
    public String study;

    public Student(int id, String email, String password, String lastLogin, String study){
        this.id = id;
        this.email = email;
        this.password = password;
        this.lastLogin = lastLogin;
        this.study = study;
    }

    public Student(StudentUser studentUser, String study){
        this.id = studentUser.id;
        this.email = studentUser.email;
        this.password = studentUser.password;
        this.lastLogin = studentUser.lastLogin;
        this.study = study;
    }

}
