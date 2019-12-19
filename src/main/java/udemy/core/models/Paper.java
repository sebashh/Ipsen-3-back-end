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
    private byte[] paperFile;

    @NotNull
    @JsonProperty
    private int uploadedBy;

    public Paper (int projectId, String title, String author, byte[] paperFile, int uploadedBy){
        this.projectId = projectId;
        this.title = title;
        this.author = author;
        this.paperFile = paperFile;
        this.uploadedBy = uploadedBy;
    }

    public Paper (){

    }

    public int getUploadedBy() {
        return uploadedBy;
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

    public byte[] getPaperFile() {
        return paperFile;
    }

    public void setPaperFile(byte[] paperFile) {
        this.paperFile = paperFile;
    }
}
