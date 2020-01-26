package udemy.core.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

public class Project {

    @NotNull
    @JsonProperty
    public int id;
    @NotNull
    @JsonProperty
    public String title;
    @NotNull
    @JsonProperty
    public String summary;
    @NotNull
    @JsonProperty
    public String createdOn;
    @NotNull
    @JsonProperty
    public int client;
    @JsonProperty
    public String study;
    @JsonProperty
    public String category;

    public Project(int projectId, String title, String summary, String createdOn , int client, String study,
                   String category) {
        this.id = projectId;
        this.title = title;
        this.summary = summary;
        this.createdOn = createdOn.substring(0, 10);
        this.client = client;
        this.study = study;
        this.category = category;
    }
}
