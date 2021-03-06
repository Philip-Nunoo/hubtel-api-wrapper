/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.phil.hubtelpaymentapi;

import com.phil.hubtelpaymentapi.Hub.BasicAuth;
import com.phil.hubtelpaymentapi.Hub.DataVendors;

/**
 *
 * @author Philip
 */
public class Hubtel {

    public static String airtimeURL = "https://api.hubtel.com/vend/airtime";
    public static String bundleUrl = "https://api.hubtel.com/vend";

    public static String getSurflineQueryUrl(String number) {
        return bundleUrl + "/surfline?device=" + number;
    }

    public static String getBusyQueryUrl(String number) {
        return bundleUrl + "/busy?account=" + number;
    }

    public static String getPaymentEndpointUrl(DataVendors vendor) {
        return bundleUrl + vendor.getVendorPaymentString();
    }

    public static String getVendBalanceUrl(String token) {
        return bundleUrl + "/balance/" + token;
    }

    public static String getDstvBillPaymentUrl() {
        return bundleUrl + "/dstv";
    }

    public static String getGoTvBillUrl() {
        return bundleUrl + "/gotv";
    }

    public static String getDstvBoBillPaymentUrl() {
        return bundleUrl + "/dstvbo";
    }

    public static String getSurflineBundleUrl() {
        return bundleUrl + "/surfline";
    }

    public static String getBusyBundleUrl() {
        return bundleUrl + "/busy";
    }

    public static String getTvLicenseUrl() {
        return bundleUrl + "/tvlicense";
    }

    public static String getVodafoneBillUrl() {
        return bundleUrl + "/vodafone";
    }

    public static String getMtnPostpaidUrl() {
        return bundleUrl + "/mtn";
    }

    public static String getEcgPostpaidBillPaymentUrl() {
        return bundleUrl + "/ecg";
    }

    public static String getAeroAirlineTicketUrl() {
        return bundleUrl + "/aero";
    }
    private final String token;
    private final String base64StringPass;

    public Hubtel(String token, String clientSecret, String clientPassword) {
        // TODO: throw exception if both are null
        if (token == null) {

        }
        BasicAuth basicAuth = new BasicAuth(clientSecret, clientPassword);

        this.token = token;
        this.base64StringPass = basicAuth.getCredentials();
    }

    public String getToken() {
        return this.token;
    }

    public String getBase64StringPass() {
        return base64StringPass;
    }

    public String getNetwork(String networkString) {
        return networkString;
    }

}
