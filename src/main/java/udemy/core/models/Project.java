package udemy.core.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.NotNull;

public class Project {

    @NotNull
    @JsonProperty
    public int projectId;
    @NotNull
    @JsonProperty
    public String title;
    @NotNull
    @JsonProperty
    public String description;
    @NotNull
    @JsonProperty
    public String study;
    @JsonProperty
    public String category;
    @JsonProperty
    public String createdOn;
    @NotNull
    @JsonProperty
    public int clientId;

    public Project(){

    }

    public Project(int projectId, String title, String description, String study, String category,
                   String createdOn, int clientId) {
        this.projectId = projectId;
        this.title = title;
        this.description = description;
        this.study = study;
        this.category = category;
        this.createdOn = createdOn;
        this.clientId = clientId;
    }
}
