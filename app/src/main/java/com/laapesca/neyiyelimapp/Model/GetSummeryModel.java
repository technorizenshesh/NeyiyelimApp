package com.laapesca.neyiyelimapp.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetSummeryModel {

    @SerializedName("total")
    @Expose
    private Double total;
    @SerializedName("result")
    @Expose
    private List<GetSummeryModelData> result = null;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("status")
    @Expose
    private String status;

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public List<GetSummeryModelData> getResult() {
        return result;
    }

    public void setResult(List<GetSummeryModelData> result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
