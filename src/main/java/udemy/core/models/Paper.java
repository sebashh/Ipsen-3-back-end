package udemy.core.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;

public class Paper {

    @JsonProperty
    public int projectId;

    @NotNull
    @JsonProperty
    public String title;

    @NotNull
    @JsonProperty
    public String author;

    @NotNull
    @JsonProperty
    public int uploadedBy;

    @JsonProperty
    public String uploadDate;

    @NotNull
    @JsonProperty
    public String paperFile;

    @JsonProperty
    public int id;

    public Paper() {}

    public Paper(int projectId, String title, String author, int uploadedBy, String uploadDate, String paperFile, int id) {
        this.projectId = projectId;
        this.title = title;
        this.author = author;
        this.paperFile = paperFile;
        this.uploadedBy = uploadedBy;
        this.uploadDate = uploadDate.substring(0,10);;
        this.id = id;
    }



    public Paper(String title, String author, String paperFile, int id) {
        this.title = title;
        this.author = author;
        this.paperFile = paperFile;
        this.id = id;
    }
}
