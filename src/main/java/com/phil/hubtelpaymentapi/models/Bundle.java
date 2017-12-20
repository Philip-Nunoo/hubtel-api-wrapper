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
public class Bundle {
    private String name;
    private String bundle;
    private String value;
    private String shortName;

    public String getName ()
    {
        return name;
    }

    public void setName (String Name)
    {
        this.name = Name;
    }

    public String getBundle ()
    {
        return bundle;
    }

    public void setBundle (String Bundle)
    {
        this.bundle = Bundle;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    
    public String getValue ()
    {
        return value;
    }

    public void setValue (String Value)
    {
        this.value = Value;
    }

    @Override
    public String toString() {
        return "Bundle{" + "name=" + name + ", bundle=" + bundle + ", value=" + value + ", shortName=" + shortName + '}';
    }
    
    void setAttributesFromJsonObject(JSONObject bundleJsonObject) {
        this.name = bundleJsonObject.getString("Name");
        this.value = bundleJsonObject.getString("Value");
        this.bundle = bundleJsonObject.getString("Bundle");
        
        if (bundleJsonObject.has("ShortName"))
        this.shortName = bundleJsonObject.getString("ShortName");
    }
}
