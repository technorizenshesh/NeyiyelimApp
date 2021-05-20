package com.laapesca.neyiyelimapp.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RestaurentModelData {

    @SerializedName("ID")
    @Expose
    private String id;
    @SerializedName("branchID")
    @Expose
    private String branchID;
    @SerializedName("regionID")
    @Expose
    private String regionID;
    @SerializedName("branch_min_order_amount")
    @Expose
    private String branchMinOrderAmount;
    @SerializedName("min_delivery_time")
    @Expose
    private String minDeliveryTime;
    @SerializedName("min_pickup_time")
    @Expose
    private String minPickupTime;
    @SerializedName("restaurant_data")
    @Expose
    private RestaurantDataDetailsModel restaurantData;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBranchID() {
        return branchID;
    }

    public void setBranchID(String branchID) {
        this.branchID = branchID;
    }

    public String getRegionID() {
        return regionID;
    }

    public void setRegionID(String regionID) {
        this.regionID = regionID;
    }

    public String getBranchMinOrderAmount() {
        return branchMinOrderAmount;
    }

    public void setBranchMinOrderAmount(String branchMinOrderAmount) {
        this.branchMinOrderAmount = branchMinOrderAmount;
    }

    public String getMinDeliveryTime() {
        return minDeliveryTime;
    }

    public void setMinDeliveryTime(String minDeliveryTime) {
        this.minDeliveryTime = minDeliveryTime;
    }

    public String getMinPickupTime() {
        return minPickupTime;
    }

    public void setMinPickupTime(String minPickupTime) {
        this.minPickupTime = minPickupTime;
    }

    public RestaurantDataDetailsModel getRestaurantData() {
        return restaurantData;
    }

    public void setRestaurantData(RestaurantDataDetailsModel restaurantData) {
        this.restaurantData = restaurantData;
    }

}
