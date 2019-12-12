package udemy.core.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;

public class Project2 {

    @NotNull
    @JsonProperty
    private int projectId;
    @NotNull
    @JsonProperty
    private String author;
    @NotNull
    @JsonProperty
    private String projectName;
    @NotNull
    @JsonProperty
    private String category;
    @JsonProperty
    private String uploadDate; // moet string worden en naam aanpassingen
    @JsonProperty
    private int likes;
    @NotNull
    @JsonProperty
    private String study;
    @NotNull
    @JsonProperty
    private String course;
    @NotNull
    @JsonProperty
    private int uploadedBy; // naam aanpassingen
    @JsonProperty
    private byte[] pdfFile;

    public Project2(int projectId, String author, String projectName, String category, String uploadDate,
                    int likes, String study, String course, int uploadedBy, byte[] pdfFile) {
        this.projectId = projectId;
        this.author = author;
        this.projectName = projectName;
        this.category = category;
        this.uploadDate = uploadDate;
        this.likes = likes;
        this.study = study;
        this.course = course;
        this.uploadedBy = uploadedBy;
        this.pdfFile = pdfFile;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(String uploadDate) {
        this.uploadDate = uploadDate;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public String getStudy() {
        return study;
    }

    public void setStudy(String study) {
        this.study = study;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
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


    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public byte[] getPdfFile() {
        return pdfFile;
    }

    public void setPdfFile(byte[] pdfFile) {
        this.pdfFile = pdfFile;
    }

}
