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
public class AirtimePurchaseErrorResponse extends ErrorReponse {

    private String foreignId;
    private String customer;
    private Double amount;
    private String errorCode;
    private String description;

    public String getForeignId() {
        return foreignId;
    }

    public void setForeignId(String foreignId) {
        this.foreignId = foreignId;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    
    @Override
    public void setAttributesFromJsonObject(JSONObject jSONObject) {
        super.setAttributesFromJsonObject(jSONObject);
        
        this.foreignId = jSONObject.getString("ForeignId");
        this.amount = jSONObject.getDouble("Amount");
        this.errorCode = jSONObject.getString("ErrorCode");
        this.description = jSONObject.getString("Description");
    }
    
}
