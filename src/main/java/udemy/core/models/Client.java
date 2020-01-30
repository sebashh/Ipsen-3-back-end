package udemy.core.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Client extends ExtendThisUser{

    @JsonProperty
    public String company_name;
    public String description;
    public String picture;

    public Client(){

    }

    public Client(int id, String email, String password, String lastLogin, String company_name, String description, String picture){
        this.id = id;
        this.email = email;
        this.password = password;
        this.lastLogin = lastLogin.substring(0, 10);
        this.company_name = company_name;
        this.description = description;
        this.picture = picture;
    }

    public Client(int id, String email, String lastLogin, String company_name, String description, String picture){
        this.id = id;
        this.email = email;
        this.password = password;
        this.lastLogin = lastLogin;
        this.company_name = company_name;
        this.description = description;
        this.picture = picture;
    }
}
