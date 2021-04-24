package com.laapesca.neyiyelimapp.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BranchModel {

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
    @SerializedName("menu_title")
    @Expose
    private String menuTitle;
    @SerializedName("menu_description")
    @Expose
    private String menuDescription;
    @SerializedName("menu_index")
    @Expose
    private String menuIndex;
    @SerializedName("menu_price")
    @Expose
    private String menuPrice;
    @SerializedName("menu_quantity")
    @Expose
    private String menuQuantity;
    @SerializedName("low_price_menu")
    @Expose
    private String lowPriceMenu;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("created")
    @Expose
    private String created;
    @SerializedName("isActive")
    @Expose
    private String isActive;

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

    public String getMenuTitle() {
        return menuTitle;
    }

    public void setMenuTitle(String menuTitle) {
        this.menuTitle = menuTitle;
    }

    public String getMenuDescription() {
        return menuDescription;
    }

    public void setMenuDescription(String menuDescription) {
        this.menuDescription = menuDescription;
    }

    public String getMenuIndex() {
        return menuIndex;
    }

    public void setMenuIndex(String menuIndex) {
        this.menuIndex = menuIndex;
    }

    public String getMenuPrice() {
        return menuPrice;
    }

    public void setMenuPrice(String menuPrice) {
        this.menuPrice = menuPrice;
    }

    public String getMenuQuantity() {
        return menuQuantity;
    }

    public void setMenuQuantity(String menuQuantity) {
        this.menuQuantity = menuQuantity;
    }

    public String getLowPriceMenu() {
        return lowPriceMenu;
    }

    public void setLowPriceMenu(String lowPriceMenu) {
        this.lowPriceMenu = lowPriceMenu;
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

}
