/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.phil.hubtelpaymentapi.models;

import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author Philip
 */
public class Busy {
    private ArrayList<Bundle> Bundles;
    private Bundle paymentBundle;
    private String Lastname;
    private String Firstname;
    private String Msisdn;

    public ArrayList<Bundle> getBundles ()
    {
        return Bundles;
    }

    public void setBundles (ArrayList<Bundle> Bundles)
    {
        this.Bundles = Bundles;
    }

    public String getLastname ()
    {
        return Lastname;
    }

    public Bundle getPaymentBundle() {
        return paymentBundle;
    }

    public void setPaymentBundle(Bundle paymentBundle) {
        this.paymentBundle = paymentBundle;
    }

    public void setLastname (String Lastname)
    {
        this.Lastname = Lastname;
    }

    public String getFirstname ()
    {
        return Firstname;
    }

    public void setFirstname (String Firstname)
    {
        this.Firstname = Firstname;
    }

    public String getMsisdn ()
    {
        return Msisdn;
    }

    public void setMsisdn (String Msisdn)
    {
        this.Msisdn = Msisdn;
    }

    @Override
    public String toString() {
        return "Busy{" + "Bundles=" + Bundles + ", paymentBundle=" + paymentBundle + ", Lastname=" + Lastname + ", Firstname=" + Firstname + ", Msisdn=" + Msisdn + '}';
    }

    public void setAttributesFromJsonObject(JSONObject jsonObject) {
        // msisdn
        this.Msisdn = jsonObject.getString("Msisdn");
        
        // firstName
        this.Firstname = jsonObject.getString("Firstname");
        
        // lastName
        this.Lastname = jsonObject.getString("Lastname");
        
        // Bundle
        JSONArray bundleJsonArray = jsonObject.getJSONArray("Bundles");
        
        ArrayList<Bundle> mBundles = new ArrayList<>();
        
        for (int i = 0; i < bundleJsonArray.length(); i++) {
            JSONObject bundleJsonObject = bundleJsonArray.getJSONObject(i);
            Bundle bundle = new Bundle();
            bundle.setAttributesFromJsonObject(bundleJsonObject);
            
            mBundles.add(bundle);
        }
        
        this.setBundles(mBundles);
    }
}
