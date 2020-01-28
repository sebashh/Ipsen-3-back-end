package udemy.core.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.NotNull;

public class Category {

    @NotNull
    @JsonProperty
    public String name;
    @NotNull
    @JsonProperty
    public int id;

    public Category(String name, int id) {
        this.name = name;
        this.id = id;
    }
}
