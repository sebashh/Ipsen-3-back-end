package udemy.core.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.NotNull;

public class Category {

    @NotNull
    @JsonProperty
    public String name;

    public Category(String name) {
        this.name = name;
    }
}
