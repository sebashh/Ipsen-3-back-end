package udemy.core.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Admin {
    @JsonProperty
    public String name;
    public String password;

    public Admin() {}

    public Admin(String name, String password){
        this.name = name;
        this.password = password;
    }
}
