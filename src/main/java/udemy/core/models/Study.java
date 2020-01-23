package udemy.core.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;

public class Study {

    @NotNull
    @JsonProperty
    public String name;

    public Study(String name) {
        this.name = name;
    }
}
