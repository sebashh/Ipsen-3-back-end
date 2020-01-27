package udemy.core.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;

public class Study {

    @NotNull
    @JsonProperty
    public String name;
    @NotNull
    @JsonProperty
    public int id;


    public Study(String name, int id) {
        this.name = name;
        this.id = id;
    }
}
