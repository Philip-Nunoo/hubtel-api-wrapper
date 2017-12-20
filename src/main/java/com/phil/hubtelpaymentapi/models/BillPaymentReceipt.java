/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.phil.hubtelpaymentapi.models;

import org.json.JSONObject;

/**
 *
 * @author Philip
 */
public class BillPaymentReceipt {
    private String id;
    private String customer;
    private String foreignId;
    private String providerId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getForeignId() {
        return foreignId;
    }

    public void setForeignId(String foreignId) {
        this.foreignId = foreignId;
    }

    public String getProviderId() {
        return providerId;
    }

    public void setProviderId(String providerId) {
        this.providerId = providerId;
    }
    
    public void setAttributesFromJsonObject(JSONObject jSONObject) {
        this.id = jSONObject.getString("Id");
        this.customer = jSONObject.getString("Customer");
        this.foreignId = jSONObject.getString("ForeignId");
        this.providerId = jSONObject.getString("ProviderId");
    }
}
