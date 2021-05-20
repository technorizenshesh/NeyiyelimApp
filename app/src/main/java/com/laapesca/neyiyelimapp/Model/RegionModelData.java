package com.laapesca.neyiyelimapp.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RegionModelData {

    @SerializedName("regionID")
    @Expose
    private String regionID;
    @SerializedName("cityID")
    @Expose
    private String cityID;
    @SerializedName("region_name")
    @Expose
    private String regionName;
    @SerializedName("region_index")
    @Expose
    private String regionIndex;
    @SerializedName("created")
    @Expose
    private String created;
    @SerializedName("isActive")
    @Expose
    private String isActive;

    public String getRegionID() {
        return regionID;
    }

    public void setRegionID(String regionID) {
        this.regionID = regionID;
    }

    public String getCityID() {
        return cityID;
    }

    public void setCityID(String cityID) {
        this.cityID = cityID;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public String getRegionIndex() {
        return regionIndex;
    }

    public void setRegionIndex(String regionIndex) {
        this.regionIndex = regionIndex;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getIsActive() {
        return isActive;
    }

    public void setIsActive(String isActive) {
        this.isActive = isActive;
    }

}
