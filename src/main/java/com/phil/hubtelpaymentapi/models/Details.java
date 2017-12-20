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
public class Details {
    private String Status;
    private String AccountType;

    public String getStatus ()
    {
        return Status;
    }

    public void setStatus (String Status)
    {
        this.Status = Status;
    }

    public String getAccountType ()
    {
        return AccountType;
    }

    public void setAccountType (String AccountType)
    {
        this.AccountType = AccountType;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [Status = "+Status+", AccountType = "+AccountType+"]";
    }

    public void setAttributesFromJsonObject(JSONObject detailJsonObject) {
        this.setStatus(detailJsonObject.getString("Status"));
        this.setAccountType(detailJsonObject.getString("AccountType"));
    }
}
