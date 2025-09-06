package models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class CarBrand {
    @JsonProperty("brand")
    private String brand;
    
    @JsonProperty("country")
    private String country;
    
    @JsonProperty("founded")
    private int founded;
    
    @JsonProperty("models")
    private List<CarModel> models;
    
    @JsonProperty("features")
    private List<String> features;

    public String getBrand() { return brand; }
    public String getCountry() { return country; }
    public int getFounded() { return founded; }
    public List<CarModel> getModels() { return models; }
    public List<String> getFeatures() { return features; }

    public void setBrand(String brand) { this.brand = brand; }
    public void setCountry(String country) { this.country = country; }
    public void setFounded(int founded) { this.founded = founded; }
    public void setModels(List<CarModel> models) { this.models = models; }
    public void setFeatures(List<String> features) { this.features = features; }
}