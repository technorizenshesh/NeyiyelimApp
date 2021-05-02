package com.laapesca.neyiyelimapp.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetCityModelData {
    @SerializedName("cityID")
    @Expose
    private String cityID;
    @SerializedName("countryID")
    @Expose
    private String countryID;
    @SerializedName("city_name")
    @Expose
    private String cityName;
    @SerializedName("city_abbr")
    @Expose
    private String cityAbbr;
    @SerializedName("isActive")
    @Expose
    private String isActive;
    @SerializedName("city_index")
    @Expose
    private String cityIndex;
    @SerializedName("TimeZone")
    @Expose
    private String timeZone;

    public String getCityID() {
        return cityID;
    }

    public void setCityID(String cityID) {
        this.cityID = cityID;
    }

    public String getCountryID() {
        return countryID;
    }

    public void setCountryID(String countryID) {
        this.countryID = countryID;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCityAbbr() {
        return cityAbbr;
    }

    public void setCityAbbr(String cityAbbr) {
        this.cityAbbr = cityAbbr;
    }

    public String getIsActive() {
        return isActive;
    }

    public void setIsActive(String isActive) {
        this.isActive = isActive;
    }

    public String getCityIndex() {
        return cityIndex;
    }

    public void setCityIndex(String cityIndex) {
        this.cityIndex = cityIndex;
    }

    public String getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }
}
