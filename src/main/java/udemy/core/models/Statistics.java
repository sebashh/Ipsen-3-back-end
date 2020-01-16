package udemy.core.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;

public class Statistics {

    @NotNull
    @JsonProperty
    public int userAmount;
    @NotNull
    @JsonProperty
    public int studentAmount;
    @NotNull
    @JsonProperty
    public int teacherAmount;
    @NotNull
    @JsonProperty
    public int clientAmount;
    @NotNull
    @JsonProperty
    public int projectAmount;


    public Statistics(){}

    public Statistics(int userAmount, int studentAmount, int teacherAmount, int clientAmount, int projectAmount){
        this.userAmount = userAmount;
        this.studentAmount = studentAmount;
        this.teacherAmount = teacherAmount;
        this.clientAmount = clientAmount;
        this.projectAmount = projectAmount;
    }

}
