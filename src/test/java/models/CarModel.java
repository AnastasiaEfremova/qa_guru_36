package models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CarModel {
    @JsonProperty("name")
    private String name;
    
    @JsonProperty("type")
    private String type;
    
    @JsonProperty("year")
    private int year;
    

    public String getName() { return name; }
    public String getType() { return type; }
    public int getYear() { return year; }

    public void setName(String name) { this.name = name; }
    public void setType(String type) { this.type = type; }
    public void setYear(int year) { this.year = year; }
}