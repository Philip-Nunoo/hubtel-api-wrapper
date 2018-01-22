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
public class BillPaymentErrorResponse extends ErrorReponse {
    private String errorCode;
    private String description;

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
        
//        this.errorCode = jSONObject.getString("ErrorCode");
//        this.description = jSONObject.getString("Description");
    }
}
