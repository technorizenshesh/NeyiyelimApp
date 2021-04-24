package com.laapesca.neyiyelimapp.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RestaurentModelData {

    @SerializedName("restaurantID")
    @Expose
    private String restaurantID;
    @SerializedName("ownerID")
    @Expose
    private String ownerID;
    @SerializedName("restaurant_name")
    @Expose
    private String restaurantName;
    @SerializedName("restaurant_address")
    @Expose
    private String restaurantAddress;
    @SerializedName("phone")
    @Expose
    private String phone;
    @SerializedName("restaurant_logo")
    @Expose
    private String restaurantLogo;
    @SerializedName("resgistration_date")
    @Expose
    private String resgistrationDate;
    @SerializedName("tax_number")
    @Expose
    private String taxNumber;
    @SerializedName("catID")
    @Expose
    private String catID;
    @SerializedName("subcatID")
    @Expose
    private String subcatID;
    @SerializedName("overall_promo")
    @Expose
    private String overallPromo;
    @SerializedName("restaurant_description")
    @Expose
    private String restaurantDescription;
    @SerializedName("restaurant_date")
    @Expose
    private String restaurantDate;
    @SerializedName("restaurant_index")
    @Expose
    private String restaurantIndex;
    @SerializedName("isActive")
    @Expose
    private String isActive;
    @SerializedName("country")
    @Expose
    private String country;
    @SerializedName("city")
    @Expose
    private String city;
    @SerializedName("branchID")
    @Expose
    private String branchID;
    @SerializedName("branch_min_order_amount")
    @Expose
    private String branchMinOrderAmount;
    @SerializedName("branch_min_delivery_time")
    @Expose
    private String branchMinDeliveryTime;
    @SerializedName("payment_types_accepted")
    @Expose
    private String paymentTypesAccepted;

    public String getRestaurantID() {
        return restaurantID;
    }

    public void setRestaurantID(String restaurantID) {
        this.restaurantID = restaurantID;
    }

    public String getOwnerID() {
        return ownerID;
    }

    public void setOwnerID(String ownerID) {
        this.ownerID = ownerID;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public String getRestaurantAddress() {
        return restaurantAddress;
    }

    public void setRestaurantAddress(String restaurantAddress) {
        this.restaurantAddress = restaurantAddress;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRestaurantLogo() {
        return restaurantLogo;
    }

    public void setRestaurantLogo(String restaurantLogo) {
        this.restaurantLogo = restaurantLogo;
    }

    public String getResgistrationDate() {
        return resgistrationDate;
    }

    public void setResgistrationDate(String resgistrationDate) {
        this.resgistrationDate = resgistrationDate;
    }

    public String getTaxNumber() {
        return taxNumber;
    }

    public void setTaxNumber(String taxNumber) {
        this.taxNumber = taxNumber;
    }

    public String getCatID() {
        return catID;
    }

    public void setCatID(String catID) {
        this.catID = catID;
    }

    public String getSubcatID() {
        return subcatID;
    }

    public void setSubcatID(String subcatID) {
        this.subcatID = subcatID;
    }

    public String getOverallPromo() {
        return overallPromo;
    }

    public void setOverallPromo(String overallPromo) {
        this.overallPromo = overallPromo;
    }

    public String getRestaurantDescription() {
        return restaurantDescription;
    }

    public void setRestaurantDescription(String restaurantDescription) {
        this.restaurantDescription = restaurantDescription;
    }

    public String getRestaurantDate() {
        return restaurantDate;
    }

    public void setRestaurantDate(String restaurantDate) {
        this.restaurantDate = restaurantDate;
    }

    public String getRestaurantIndex() {
        return restaurantIndex;
    }

    public void setRestaurantIndex(String restaurantIndex) {
        this.restaurantIndex = restaurantIndex;
    }

    public String getIsActive() {
        return isActive;
    }

    public void setIsActive(String isActive) {
        this.isActive = isActive;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getBranchID() {
        return branchID;
    }

    public void setBranchID(String branchID) {
        this.branchID = branchID;
    }

    public String getBranchMinOrderAmount() {
        return branchMinOrderAmount;
    }

    public void setBranchMinOrderAmount(String branchMinOrderAmount) {
        this.branchMinOrderAmount = branchMinOrderAmount;
    }

    public String getBranchMinDeliveryTime() {
        return branchMinDeliveryTime;
    }

    public void setBranchMinDeliveryTime(String branchMinDeliveryTime) {
        this.branchMinDeliveryTime = branchMinDeliveryTime;
    }

    public String getPaymentTypesAccepted() {
        return paymentTypesAccepted;
    }

    public void setPaymentTypesAccepted(String paymentTypesAccepted) {
        this.paymentTypesAccepted = paymentTypesAccepted;
    }

}
