package udemy.core.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;

public class Interest {

    @NotNull
    @JsonProperty
    public String email;
    @NotNull
    @JsonProperty
    public int category_id;

    public Interest(String email, int category_id) {
        this.email = email;
        this.category_id = category_id;
    }

    public Interest() {

    }

}
