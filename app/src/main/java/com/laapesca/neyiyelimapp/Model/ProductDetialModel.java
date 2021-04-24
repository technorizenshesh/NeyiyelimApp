package com.laapesca.neyiyelimapp.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProductDetialModel {

    @SerializedName("submenuID")
    @Expose
    private String submenuID;
    @SerializedName("menuID")
    @Expose
    private String menuID;
    @SerializedName("catID")
    @Expose
    private String catID;
    @SerializedName("subcatID")
    @Expose
    private String subcatID;
    @SerializedName("branchID")
    @Expose
    private String branchID;
    @SerializedName("submenu_title")
    @Expose
    private String submenuTitle;
    @SerializedName("submenu_description")
    @Expose
    private String submenuDescription;
    @SerializedName("submenu_price")
    @Expose
    private String submenuPrice;
    @SerializedName("submenu_quantity")
    @Expose
    private String submenuQuantity;
    @SerializedName("open_time")
    @Expose
    private String openTime;
    @SerializedName("close_time")
    @Expose
    private String closeTime;
    @SerializedName("available_days")
    @Expose
    private String availableDays;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("created")
    @Expose
    private String created;
    @SerializedName("isActive")
    @Expose
    private String isActive;
    @SerializedName("isOption")
    @Expose
    private String isOption;
    @SerializedName("submenu_index")
    @Expose
    private String submenuIndex;

    public String getSubmenuID() {
        return submenuID;
    }

    public void setSubmenuID(String submenuID) {
        this.submenuID = submenuID;
    }

    public String getMenuID() {
        return menuID;
    }

    public void setMenuID(String menuID) {
        this.menuID = menuID;
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

    public String getBranchID() {
        return branchID;
    }

    public void setBranchID(String branchID) {
        this.branchID = branchID;
    }

    public String getSubmenuTitle() {
        return submenuTitle;
    }

    public void setSubmenuTitle(String submenuTitle) {
        this.submenuTitle = submenuTitle;
    }

    public String getSubmenuDescription() {
        return submenuDescription;
    }

    public void setSubmenuDescription(String submenuDescription) {
        this.submenuDescription = submenuDescription;
    }

    public String getSubmenuPrice() {
        return submenuPrice;
    }

    public void setSubmenuPrice(String submenuPrice) {
        this.submenuPrice = submenuPrice;
    }

    public String getSubmenuQuantity() {
        return submenuQuantity;
    }

    public void setSubmenuQuantity(String submenuQuantity) {
        this.submenuQuantity = submenuQuantity;
    }

    public String getOpenTime() {
        return openTime;
    }

    public void setOpenTime(String openTime) {
        this.openTime = openTime;
    }

    public String getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(String closeTime) {
        this.closeTime = closeTime;
    }

    public String getAvailableDays() {
        return availableDays;
    }

    public void setAvailableDays(String availableDays) {
        this.availableDays = availableDays;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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

    public String getIsOption() {
        return isOption;
    }

    public void setIsOption(String isOption) {
        this.isOption = isOption;
    }

    public String getSubmenuIndex() {
        return submenuIndex;
    }

    public void setSubmenuIndex(String submenuIndex) {
        this.submenuIndex = submenuIndex;
    }

}
