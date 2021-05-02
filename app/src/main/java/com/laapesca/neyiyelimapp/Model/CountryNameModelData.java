package com.laapesca.neyiyelimapp.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CountryNameModelData {

    @SerializedName("countryID")
    @Expose
    private String countryID;
    @SerializedName("country_name")
    @Expose
    private String countryName;
    @SerializedName("isActive")
    @Expose
    private String isActive;
    @SerializedName("countries_index")
    @Expose
    private Object countriesIndex;
    @SerializedName("country_code")
    @Expose
    private String countryCode;
    @SerializedName("country_code2")
    @Expose
    private String countryCode2;
    @SerializedName("currency_code")
    @Expose
    private String currencyCode;
    @SerializedName("currency_icon")
    @Expose
    private String currencyIcon;

    public String getCountryID() {
        return countryID;
    }

    public void setCountryID(String countryID) {
        this.countryID = countryID;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getIsActive() {
        return isActive;
    }

    public void setIsActive(String isActive) {
        this.isActive = isActive;
    }

    public Object getCountriesIndex() {
        return countriesIndex;
    }

    public void setCountriesIndex(Object countriesIndex) {
        this.countriesIndex = countriesIndex;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getCountryCode2() {
        return countryCode2;
    }

    public void setCountryCode2(String countryCode2) {
        this.countryCode2 = countryCode2;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getCurrencyIcon() {
        return currencyIcon;
    }

    public void setCurrencyIcon(String currencyIcon) {
        this.currencyIcon = currencyIcon;
    }

}
