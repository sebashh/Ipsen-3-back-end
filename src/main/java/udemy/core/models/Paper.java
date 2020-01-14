package udemy.core.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;

public class Paper {

    @JsonProperty
    private int projectId;

    @NotNull
    @JsonProperty
    private String title;

    @NotNull
    @JsonProperty
    private String author;

    @NotNull
    @JsonProperty
    private int uploadedBy;

    @JsonProperty
    private String uploadDate;

    @NotNull
    @JsonProperty
    private String paperFile;


    public Paper (int projectId, String title, String author, int uploadedBy, String uploadDate, String paperFile){
        this.projectId = projectId;
        this.title = title;
        this.author = author;
        this.paperFile = paperFile;
        this.uploadedBy = uploadedBy;
        this.uploadDate = uploadDate;
    }

    public Paper (){

    }

    public int getUploadedBy() {
        return uploadedBy;
    }

    public String getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(String uploadDate) {
        this.uploadDate = uploadDate;
    }

    public void setUploadedBy(int uploadedBy) {
        this.uploadedBy = uploadedBy;
    }


    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPaperFile() {
        return paperFile;
    }

    public void setPaperFile(String paperFile) {
        this.paperFile = paperFile;
    }
}
