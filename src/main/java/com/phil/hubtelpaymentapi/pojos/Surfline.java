/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.phil.hubtelpaymentapi.pojos;

import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author Philip
 */
public class Surfline {
    private ArrayList<Bundle> bundles;
    private Bundle paymentBundle;
    private String device;
    private String serviceName;
    private Details details;
    private String[] endPoint;

    public ArrayList<Bundle> getBundles ()
    {
        return bundles;
    }

    public void setBundles (ArrayList<Bundle> Bundles)
    {
        this.bundles = Bundles;
    }

    public String getDevice ()
    {
        return device;
    }

    public void setDevice (String Device)
    {
        this.device = Device;
    }

    public String getServiceName ()
    {
        return serviceName;
    }

    public void setServiceName (String ServiceName)
    {
        this.serviceName = ServiceName;
    }

    public Details getDetails ()
    {
        return details;
    }

    public void setDetails (Details Details)
    {
        this.details = Details;
    }

    public String[] getEndPoint ()
    {
        return endPoint;
    }

    public void setEndPoint (String[] EndPoint)
    {
        this.endPoint = EndPoint;
    }

    public Bundle getPaymentBundle() {
        return paymentBundle;
    }

    public void setPaymentBundle(Bundle paymentBundle) {
        this.paymentBundle = paymentBundle;
    }

    @Override
    public String toString() {
        return "Surfline{" + "bundles=" + bundles + ", paymentBundle=" + paymentBundle + ", device=" + device + ", serviceName=" + serviceName + ", details=" + details + ", endPoint=" + endPoint + '}';
    }

    public void setAttributesFromJsonObject(JSONObject jsonObject) {
        // datails
//        this.details.setAttributesFromJsonObject(jsonObject.getJSONObject("Details"));
        // serviceName
        this.serviceName = jsonObject.getString("ServiceName");
        // device
        this.device = jsonObject.getString("Device");
        // endpoints
//        JSONArray endpointJsonArray = jsonObject.getJSONArray(device);
//        for (int i = 0; i < endpointJsonArray.length(); i++) {
//            JSONObject endpointJsonObject = endpointJsonArray.getJSONObject(i);
//            
//            
//        }
        // this.endPoint
        // bundles
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
