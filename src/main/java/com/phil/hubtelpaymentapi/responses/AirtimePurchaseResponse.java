/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.phil.hubtelpaymentapi.responses;

import org.json.JSONObject;

/**
 *
 * @author Philip
 */
public class AirtimePurchaseResponse
{
    private Double balanceAfter;
    private String customer;
    private Double commission;
    private Double amount;
    private String description;
    private Double balanceBefore;
    private String providerId;
    private String id;
    private Double charge;
    private String foreignId;
    private AirtimePurchaseErrorResponse errorResponse;
    
    public Double getBalanceAfter() {
        return balanceAfter;
    }

    public void setBalanceAfter(Double balanceAfter) {
        this.balanceAfter = balanceAfter;
    }

    public String getCustomer() {
        return customer;
    }

    public AirtimePurchaseErrorResponse getErrorResponse() {
        return errorResponse;
    }

    public void setErrorResponse(AirtimePurchaseErrorResponse errorResponse) {
        this.errorResponse = errorResponse;
    }    

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public Double getCommission() {
        return commission;
    }

    public void setCommission(Double commission) {
        this.commission = commission;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getBalanceBefore() {
        return balanceBefore;
    }

    public void setBalanceBefore(Double balanceBefore) {
        this.balanceBefore = balanceBefore;
    }

    public String getProviderId() {
        return providerId;
    }

    public void setProviderId(String providerId) {
        this.providerId = providerId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Double getCharge() {
        return charge;
    }

    public void setCharge(Double charge) {
        this.charge = charge;
    }

    public String getForeignId() {
        return foreignId;
    }

    public void setForeignId(String foreignId) {
        this.foreignId = foreignId;
    }

    @Override
    public String toString() {
        return "TopUpResponse{" + "balanceAfter=" + balanceAfter + ", customer=" + customer + ", commission=" + commission + ", amount=" + amount + ", description=" + description + ", balanceBefore=" + balanceBefore + ", providerId=" + providerId + ", id=" + id + ", charge=" + charge + ", foreignId=" + foreignId + '}';
    }

    public void setAttributesFromJsonObject(int code, JSONObject jSONObject) {
        if (code > 200) {
            errorResponse.setAttributesFromJsonObject(jSONObject);
        } else {
            errorResponse = null;
            this.id = jSONObject.getString("Id");
            this.providerId = jSONObject.getString("ProviderId");
            this.customer = jSONObject.getString("Customer");
            this.foreignId = jSONObject.getString("ForeignId");
            this.description = jSONObject.getString("Description");
            this.amount = jSONObject.getDouble("Amount");
            this.commission = jSONObject.getDouble("Commission");
            this.charge = jSONObject.getDouble("Charge");
            this.balanceBefore = jSONObject.getDouble("BalanceBefore");
            this.balanceAfter = jSONObject.getDouble("BalanceAfter");
        }
    }
}