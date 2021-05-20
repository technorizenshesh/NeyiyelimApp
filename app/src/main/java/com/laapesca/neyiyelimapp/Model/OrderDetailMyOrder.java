package com.laapesca.neyiyelimapp.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OrderDetailMyOrder {
    @SerializedName("detail_id")
    @Expose
    private String detailId;
    @SerializedName("order_id")
    @Expose
    private String orderId;
    @SerializedName("menu_id")
    @Expose
    private String menuId;
    @SerializedName("submenu_id")
    @Expose
    private String submenuId;
    @SerializedName("option_id")
    @Expose
    private String optionId;
    @SerializedName("suboption_id")
    @Expose
    private String suboptionId;
    @SerializedName("quantity")
    @Expose
    private String quantity;
    @SerializedName("amount")
    @Expose
    private String amount;
    @SerializedName("original_amount")
    @Expose
    private String originalAmount;
    @SerializedName("spl_note")
    @Expose
    private String splNote;

    public String getDetailId() {
        return detailId;
    }

    public void setDetailId(String detailId) {
        this.detailId = detailId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    public String getSubmenuId() {
        return submenuId;
    }

    public void setSubmenuId(String submenuId) {
        this.submenuId = submenuId;
    }

    public String getOptionId() {
        return optionId;
    }

    public void setOptionId(String optionId) {
        this.optionId = optionId;
    }

    public String getSuboptionId() {
        return suboptionId;
    }

    public void setSuboptionId(String suboptionId) {
        this.suboptionId = suboptionId;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getOriginalAmount() {
        return originalAmount;
    }

    public void setOriginalAmount(String originalAmount) {
        this.originalAmount = originalAmount;
    }

    public String getSplNote() {
        return splNote;
    }

    public void setSplNote(String splNote) {
        this.splNote = splNote;
    }
}
