package udemy.core.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PaperModel {

    @JsonProperty
    public int id;
    @JsonProperty
    public String title;
    @JsonProperty
    public String author;
    @JsonProperty
    public int uploaded_by;
    @JsonProperty
    public int upload_date;
    @JsonProperty
    public String pdf_location;
    @JsonProperty
    public int project_id;

    public PaperModel() {

    }

    public PaperModel(int id, String title, String author, int uploaded_by, int upload_date, String pdf_location, int project_id) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.uploaded_by = uploaded_by;
        this.upload_date = upload_date;
        this.pdf_location = pdf_location;
        this.project_id = project_id;
    }

    public PaperModel(String title, String author, String pdf_location, int project_id) {
        this.title = title;
        this.author = author;
        this.pdf_location = pdf_location;
        this.project_id = project_id;
    }
}
