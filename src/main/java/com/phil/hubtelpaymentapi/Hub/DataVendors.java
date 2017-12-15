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
public enum DataVendors {
    GH_SURFLINE,
    GH_BUSY,
    GH_VODAFONE;
    
    public String getVendorPaymentString() {
        switch(this) {
            case GH_BUSY:
                return "busy";
            case GH_SURFLINE:
                return "surfline";
            case GH_VODAFONE:
                return "vodafone";
            default:
                throw new AssertionError("Unknown operations " + this);
        }
    }
    
    public String getVendorPurhasePrefix() {
        switch(this) {
            case GH_BUSY:
                return "account";
            case GH_SURFLINE:
                return "device";
            case GH_VODAFONE:
                return "service";
            default:
                throw new AssertionError("Unknown operations " + this);
        }
    }
}
