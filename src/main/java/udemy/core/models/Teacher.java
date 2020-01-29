package udemy.core.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Teacher extends ExtendThisUser {
    @JsonProperty
    public int study;

    public Teacher(){

    }
    public Teacher(int id, String email, String password, String lastLogin,int study){
        this.id = id;
        this.email = email;
        this.password = password;
        this.lastLogin = lastLogin.substring(0,10);;
        this.study = study;
    }

    public Teacher(ExtendThisUser extendThisUser, int study){
        this.id = extendThisUser.id;
        this.email = extendThisUser.email;
        this.password = extendThisUser.password;
        this.lastLogin = extendThisUser.lastLogin.substring(0,10);;
        this.study = study;
    }

}
