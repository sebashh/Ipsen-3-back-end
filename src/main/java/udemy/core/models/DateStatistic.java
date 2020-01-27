package udemy.core.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;

public class DateStatistic {

    @NotNull
    @JsonProperty
    public String date;
    @NotNull
    @JsonProperty
    public int amount;

    public DateStatistic(){}

    public DateStatistic(String date, int amount){
        this.date= date;
        this.amount = amount;
    }
}
