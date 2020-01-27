package udemy.core.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.NotNull;

public class Project {

    @JsonProperty
    public int projectId;
    @JsonProperty
    public String title;
    @NotNull
    @JsonProperty
    public String description;
    @NotNull
    @JsonProperty
    public int study;
    @JsonProperty
    public int category;
    @JsonProperty
    public String createdOn;
    @JsonProperty
    public int clientId;

    public Project(){

    }

    public Project(int projectId, String title, String description, int study, int category,
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
