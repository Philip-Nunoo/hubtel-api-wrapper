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
public enum HubtelNetworks {
    GH_VODAFONE,
    GH_MTN,
    GH_AIRTEL,
    GH_GLO,
    GH_TIGO,
    GH_EXPRESSO;

    public String getNetworkCode() {
        switch (this) {
            case GH_VODAFONE:
                return "62002";
            case GH_MTN:
                return "62001";
            case GH_AIRTEL:
                return "62006";
            case GH_GLO:
                return "62007";
            case GH_TIGO:
                return "62003";
            case GH_EXPRESSO:
                return "62004";
            default:
                throw new AssertionError("Unknown operations " + this);
        }
    }
}
