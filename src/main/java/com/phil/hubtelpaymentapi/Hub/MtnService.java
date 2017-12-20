/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.phil.hubtelpaymentapi.Hub;

/**
 *
 * @author Philip
 */
public enum MtnService {
    POSTPAID,
    PREPAID;
    
    public String getServiceName() {
        switch (this) {
            case POSTPAID:
                return "postpaid";
            case PREPAID:
                return "prepaid";
            default:
                throw new AssertionError("Unknown operations " + this);             
        }
    }
}
