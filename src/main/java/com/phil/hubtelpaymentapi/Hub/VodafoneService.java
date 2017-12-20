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
public enum VodafoneService {
    POSTPAID,
    BROADBAND;
    
    public String getServiceName() {
        switch (this) {
            case POSTPAID:
                return "postpaid";
            case BROADBAND:
                return "broadband";
            default:
                throw new AssertionError("Unknown operations " + this);             
        }
    }
}
