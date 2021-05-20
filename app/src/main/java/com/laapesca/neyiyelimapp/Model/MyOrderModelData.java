package com.laapesca.neyiyelimapp.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MyOrderModelData {

    @SerializedName("order_id")
    @Expose
    private String orderId;
    @SerializedName("order_refno")
    @Expose
    private String orderRefno;
    @SerializedName("customer_id")
    @Expose
    private String customerId;
    @SerializedName("branch_id")
    @Expose
    private String branchId;
    @SerializedName("paymentTypeID")
    @Expose
    private String paymentTypeID;
    @SerializedName("order_date")
    @Expose
    private String orderDate;
    @SerializedName("order_time")
    @Expose
    private String orderTime;
    @SerializedName("delivery_date")
    @Expose
    private String deliveryDate;
    @SerializedName("delivery_time")
    @Expose
    private String deliveryTime;
    @SerializedName("total_amount")
    @Expose
    private String totalAmount;
    @SerializedName("discount_amount")
    @Expose
    private String discountAmount;
    @SerializedName("coupon_id")
    @Expose
    private String couponId;
    @SerializedName("coupon_code")
    @Expose
    private String couponCode;
    @SerializedName("isCancel")
    @Expose
    private String isCancel;
    @SerializedName("address_type")
    @Expose
    private String addressType;
    @SerializedName("customerLat")
    @Expose
    private String customerLat;
    @SerializedName("customerLag")
    @Expose
    private String customerLag;
    @SerializedName("customerPingpoint")
    @Expose
    private String customerPingpoint;
    @SerializedName("order_status")
    @Expose
    private String orderStatus;
    @SerializedName("order_flag")
    @Expose
    private String orderFlag;
    @SerializedName("isOwnerApprove")
    @Expose
    private String isOwnerApprove;
    @SerializedName("orderAcceptStatus")
    @Expose
    private String orderAcceptStatus;
    @SerializedName("adminIVRStatus")
    @Expose
    private String adminIVRStatus;
    @SerializedName("totalCallFromAdmin")
    @Expose
    private String totalCallFromAdmin;
    @SerializedName("adminIVRResponse")
    @Expose
    private String adminIVRResponse;
    @SerializedName("isShiped")
    @Expose
    private String isShiped;
    @SerializedName("totalCall")
    @Expose
    private String totalCall;
    @SerializedName("totalCallA")
    @Expose
    private String totalCallA;
    @SerializedName("totalCallTO")
    @Expose
    private String totalCallTO;
    @SerializedName("totalChargebleCall")
    @Expose
    private String totalChargebleCall;
    @SerializedName("totalCountNext")
    @Expose
    private String totalCountNext;
    @SerializedName("totalCountNext2")
    @Expose
    private String totalCountNext2;
    @SerializedName("totalSMS")
    @Expose
    private String totalSMS;
    @SerializedName("wireTransferDetails")
    @Expose
    private String wireTransferDetails;
    @SerializedName("oderFromOwnWebsite")
    @Expose
    private String oderFromOwnWebsite;
    @SerializedName("isROLogin")
    @Expose
    private String isROLogin;
    @SerializedName("ivr_date")
    @Expose
    private String ivrDate;
    @SerializedName("notification_no")
    @Expose
    private String notificationNo;
    @SerializedName("commision")
    @Expose
    private String commision;
    @SerializedName("per_ivr_cost")
    @Expose
    private String perIvrCost;
    @SerializedName("per_sms_cost")
    @Expose
    private String perSmsCost;
    @SerializedName("per_coupon_cost")
    @Expose
    private String perCouponCost;
    @SerializedName("per_promotion_cost")
    @Expose
    private String perPromotionCost;
    @SerializedName("order_type")
    @Expose
    private String orderType;
    @SerializedName("cart_id")
    @Expose
    private String cartId;
    @SerializedName("order_detail")
    @Expose
    private OrderDetailMyOrder orderDetail;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderRefno() {
        return orderRefno;
    }

    public void setOrderRefno(String orderRefno) {
        this.orderRefno = orderRefno;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }

    public String getPaymentTypeID() {
        return paymentTypeID;
    }

    public void setPaymentTypeID(String paymentTypeID) {
        this.paymentTypeID = paymentTypeID;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }

    public String getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public String getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(String deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public String getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(String discountAmount) {
        this.discountAmount = discountAmount;
    }

    public String getCouponId() {
        return couponId;
    }

    public void setCouponId(String couponId) {
        this.couponId = couponId;
    }

    public String getCouponCode() {
        return couponCode;
    }

    public void setCouponCode(String couponCode) {
        this.couponCode = couponCode;
    }

    public String getIsCancel() {
        return isCancel;
    }

    public void setIsCancel(String isCancel) {
        this.isCancel = isCancel;
    }

    public String getAddressType() {
        return addressType;
    }

    public void setAddressType(String addressType) {
        this.addressType = addressType;
    }

    public String getCustomerLat() {
        return customerLat;
    }

    public void setCustomerLat(String customerLat) {
        this.customerLat = customerLat;
    }

    public String getCustomerLag() {
        return customerLag;
    }

    public void setCustomerLag(String customerLag) {
        this.customerLag = customerLag;
    }

    public String getCustomerPingpoint() {
        return customerPingpoint;
    }

    public void setCustomerPingpoint(String customerPingpoint) {
        this.customerPingpoint = customerPingpoint;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getOrderFlag() {
        return orderFlag;
    }

    public void setOrderFlag(String orderFlag) {
        this.orderFlag = orderFlag;
    }

    public String getIsOwnerApprove() {
        return isOwnerApprove;
    }

    public void setIsOwnerApprove(String isOwnerApprove) {
        this.isOwnerApprove = isOwnerApprove;
    }

    public String getOrderAcceptStatus() {
        return orderAcceptStatus;
    }

    public void setOrderAcceptStatus(String orderAcceptStatus) {
        this.orderAcceptStatus = orderAcceptStatus;
    }

    public String getAdminIVRStatus() {
        return adminIVRStatus;
    }

    public void setAdminIVRStatus(String adminIVRStatus) {
        this.adminIVRStatus = adminIVRStatus;
    }

    public String getTotalCallFromAdmin() {
        return totalCallFromAdmin;
    }

    public void setTotalCallFromAdmin(String totalCallFromAdmin) {
        this.totalCallFromAdmin = totalCallFromAdmin;
    }

    public String getAdminIVRResponse() {
        return adminIVRResponse;
    }

    public void setAdminIVRResponse(String adminIVRResponse) {
        this.adminIVRResponse = adminIVRResponse;
    }

    public String getIsShiped() {
        return isShiped;
    }

    public void setIsShiped(String isShiped) {
        this.isShiped = isShiped;
    }

    public String getTotalCall() {
        return totalCall;
    }

    public void setTotalCall(String totalCall) {
        this.totalCall = totalCall;
    }

    public String getTotalCallA() {
        return totalCallA;
    }

    public void setTotalCallA(String totalCallA) {
        this.totalCallA = totalCallA;
    }

    public String getTotalCallTO() {
        return totalCallTO;
    }

    public void setTotalCallTO(String totalCallTO) {
        this.totalCallTO = totalCallTO;
    }

    public String getTotalChargebleCall() {
        return totalChargebleCall;
    }

    public void setTotalChargebleCall(String totalChargebleCall) {
        this.totalChargebleCall = totalChargebleCall;
    }

    public String getTotalCountNext() {
        return totalCountNext;
    }

    public void setTotalCountNext(String totalCountNext) {
        this.totalCountNext = totalCountNext;
    }

    public String getTotalCountNext2() {
        return totalCountNext2;
    }

    public void setTotalCountNext2(String totalCountNext2) {
        this.totalCountNext2 = totalCountNext2;
    }

    public String getTotalSMS() {
        return totalSMS;
    }

    public void setTotalSMS(String totalSMS) {
        this.totalSMS = totalSMS;
    }

    public String getWireTransferDetails() {
        return wireTransferDetails;
    }

    public void setWireTransferDetails(String wireTransferDetails) {
        this.wireTransferDetails = wireTransferDetails;
    }

    public String getOderFromOwnWebsite() {
        return oderFromOwnWebsite;
    }

    public void setOderFromOwnWebsite(String oderFromOwnWebsite) {
        this.oderFromOwnWebsite = oderFromOwnWebsite;
    }

    public String getIsROLogin() {
        return isROLogin;
    }

    public void setIsROLogin(String isROLogin) {
        this.isROLogin = isROLogin;
    }

    public String getIvrDate() {
        return ivrDate;
    }

    public void setIvrDate(String ivrDate) {
        this.ivrDate = ivrDate;
    }

    public String getNotificationNo() {
        return notificationNo;
    }

    public void setNotificationNo(String notificationNo) {
        this.notificationNo = notificationNo;
    }

    public String getCommision() {
        return commision;
    }

    public void setCommision(String commision) {
        this.commision = commision;
    }

    public String getPerIvrCost() {
        return perIvrCost;
    }

    public void setPerIvrCost(String perIvrCost) {
        this.perIvrCost = perIvrCost;
    }

    public String getPerSmsCost() {
        return perSmsCost;
    }

    public void setPerSmsCost(String perSmsCost) {
        this.perSmsCost = perSmsCost;
    }

    public String getPerCouponCost() {
        return perCouponCost;
    }

    public void setPerCouponCost(String perCouponCost) {
        this.perCouponCost = perCouponCost;
    }

    public String getPerPromotionCost() {
        return perPromotionCost;
    }

    public void setPerPromotionCost(String perPromotionCost) {
        this.perPromotionCost = perPromotionCost;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getCartId() {
        return cartId;
    }

    public void setCartId(String cartId) {
        this.cartId = cartId;
    }

    public OrderDetailMyOrder getOrderDetail() {
        return orderDetail;
    }

    public void setOrderDetail(OrderDetailMyOrder orderDetail) {
        this.orderDetail = orderDetail;
    }
}
