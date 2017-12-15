/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.phil.hubtelpaymentapi;

import com.phil.hubtelpaymentapi.Hub.DataVendors;
import com.phil.hubtelpaymentapi.pojos.Surfline;
import com.phil.hubtelpaymentapi.Hub.HubtelNetworks;
import com.phil.hubtelpaymentapi.exceptions.PaymentException;
import com.phil.hubtelpaymentapi.interfaces.PaymentInterface;
import com.phil.hubtelpaymentapi.pojos.Bundle;
import com.phil.hubtelpaymentapi.pojos.Busy;
import com.phil.hubtelpaymentapi.pojos.VendBalance;
import com.phil.hubtelpaymentapi.pojos.Vodafone;
import com.phil.hubtelpaymentapi.responses.AirtimePurchaseResponse;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;
import java.io.IOException;
import org.json.JSONObject;

/**
 *
 * @author Philip
 */
public class HubtelVend implements PaymentInterface {

    private final Hubtel hubtel;
    private final OkHttpClient client;
    
    public HubtelVend(Hubtel hubtel) {
        this.hubtel = hubtel;
        this.client = new OkHttpClient();
    }

    @Override
    public VendBalance checkVendBalance() throws IOException {
        VendBalance balance = null;
        
        Request request = new Request.Builder()
          .url(Hubtel.getVendBalanceUrl(hubtel.getToken()))
          .get()
          .addHeader("accept", "application/json")
          .addHeader("content-type", "application/json")
          .addHeader("authorization", hubtel.getBase64StringPass())
          .build();

        Response response = client.newCall(request).execute();
        
        if (response.code() < 400) {
            JSONObject jSONObject = new JSONObject(response.body().string());
            
            String accountBalance = jSONObject.getString("AccountBalance");
            // Hubtel why this { 'accountBalance': 'GHS 232.23' }
            // why not { 'balance': 232.23, 'currency': 'GHS' }
            String[] accountBalanceArray = accountBalance.split(" ");
    
            Double amount = Double.parseDouble(accountBalanceArray[1]);
            String currency = accountBalanceArray[0];
            
            balance = new VendBalance(currency, amount);
        }
        
        return balance;
    }

    
    @Override
    public AirtimePurchaseResponse purchaseAirtime(String phoneNumber, HubtelNetworks network, double amount, String foreignId) throws IOException, PaymentException {
        if (amount < 0.5) {
            throw new PaymentException("amount: " + amount + " is below service minimum of 0.50");
        }

        AirtimePurchaseResponse purchaseResponse = new AirtimePurchaseResponse();
        StringBuilder builder = new StringBuilder();
        
        builder.append("{");
        builder.append("\"phone\": \"").append(phoneNumber).append("\", ");
        builder.append("\"amount\": \"").append(amount).append("\", ");
        builder.append("\"token\": \"").append(hubtel.getToken()).append("\", ");
        builder.append("\"network\": \"").append(network.getNetworkCode()).append("\", ");
        builder.append("\"foreignId\": \"").append(foreignId).append("\"");
        builder.append("}");
        
        
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, builder.toString());
        
        Request request = new Request.Builder()
          .url(Hubtel.airtimeURL)
          .post(body)
          .addHeader("accept", "application/json")
          .addHeader("content-type", "application/json")
          .addHeader("authorization", hubtel.getBase64StringPass())
          .build();

        Response response = client.newCall(request).execute();
        JSONObject jSONObject = new JSONObject(response.body().string());
        
        purchaseResponse.setAttributesFromJsonObject(response.code(), jSONObject);
        
        return purchaseResponse;
    }

    @Override
    public Surfline getUserSurflineInfo(String number) throws IOException {
        Surfline surfline = new Surfline();    
        
        Request request = new Request.Builder()
          .url(Hubtel.getSurflineQueryUrl(number))
          .get()
          .addHeader("accept", "application/json")
          .addHeader("content-type", "application/json")
          .addHeader("authorization", hubtel.getBase64StringPass())
          .build();

        Response response = client.newCall(request).execute();
        // todo: check if it is not 404
        String jsonDataString = response.body().string();
        JSONObject jsonObject = new JSONObject(jsonDataString);        
        surfline.setAttributesFromJsonObject(jsonObject);
        
        return surfline;
    }

    @Override
    public Busy getUserBusyInfo(String number) throws IOException {
        Busy busy = new Busy();
        
        Request request = new Request.Builder()
          .url(Hubtel.getBusyQueryUrl(number))
          .get()
          .addHeader("accept", "application/json")
          .addHeader("content-type", "application/json")
          .addHeader("authorization", hubtel.getBase64StringPass())
          .build();

        Response response = client.newCall(request).execute();
        // todo: check if it is not 404
        String jsonDataString = response.body().string();
        JSONObject jsonObject = new JSONObject(jsonDataString);        
        busy.setAttributesFromJsonObject(jsonObject);
        
        return busy;
    }    

    @Override
    public Vodafone getUserVodafoneInfo(String account) throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void paySurflineBundle(Surfline surfline) throws IOException {
        if (surfline.getPaymentBundle() != null) {
            System.out.println("Purchasing Surfline data " + surfline.getPaymentBundle() + " for device: " + surfline.getDevice());
            this.makeBundlePayment(DataVendors.GH_SURFLINE, surfline.getDevice(), surfline.getPaymentBundle());
        } else {
            System.err.println("No bundle specified.");
        }
    }

    @Override
    public void payBusyBundle(Busy busy) throws IOException {
        if (busy.getPaymentBundle() != null) {
            System.out.println("Purchasing Busy data " + busy.getPaymentBundle() + " for device: " + busy.getFirstname() + " " + busy.getLastname());
            this.makeBundlePayment(DataVendors.GH_BUSY, busy.getMsisdn(), busy.getPaymentBundle());
        } else {
            System.err.println("No bundle specified");
        }        
    }

    @Override
    public void payVodafoneBundle() throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    private void makeBundlePayment(DataVendors vendor, String account, Bundle bundle) throws IOException {

        StringBuilder builder = new StringBuilder();
        
        builder.append("{");
        builder.append("\"amount\": \"").append(bundle.getValue()).append("\", ");
        builder.append("\"bundle\": \"").append(bundle.getBundle()).append("\", ");
        builder.append("\"token\": \"").append(hubtel.getToken()).append("\" ");
        builder.append("\"").append(vendor.getVendorPurhasePrefix()).append("\": ")
                .append(account)
                .append("\", ");
        builder.append("}");
        
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, builder.toString());
        
        Request request = new Request.Builder()
          .url(Hubtel.getPaymentEndpointUrl(vendor))
          .post(body)
          .addHeader("accept", "application/json")
          .addHeader("content-type", "application/json")
          .build();

        Response response = client.newCall(request).execute();
    }
    
}
