/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.phil.hubtelpaymentapi;

import com.phil.hubtelpaymentapi.models.Surfline;
import com.phil.hubtelpaymentapi.Hub.HubtelNetworks;
import com.phil.hubtelpaymentapi.Hub.MtnService;
import com.phil.hubtelpaymentapi.Hub.VodafoneService;
import com.phil.hubtelpaymentapi.exceptions.PaymentException;
import com.phil.hubtelpaymentapi.interfaces.PaymentInterface;
import com.phil.hubtelpaymentapi.models.Bundle;
import com.phil.hubtelpaymentapi.models.Busy;
import com.phil.hubtelpaymentapi.models.BillPaymentReceipt;
import com.phil.hubtelpaymentapi.models.VendBalance;
import com.phil.hubtelpaymentapi.models.Vodafone;
import com.phil.hubtelpaymentapi.responses.AirtimePurchaseResponse;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
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

    private BillPaymentReceipt payBill(String url, HashMap<String, Object> hmap) throws IOException {
        StringBuilder builder = new StringBuilder();
        BillPaymentReceipt dstvReceipt = new BillPaymentReceipt();
        
        Set set = hmap.entrySet();
        Iterator iterator = set.iterator();
        builder.append("{");
        
        while (iterator.hasNext()) {
            Map.Entry mentry = (Map.Entry)iterator.next();
            builder.append("\"").append(mentry.getKey()).append("\": \"").append(mentry.getValue());
            
            if (iterator.hasNext())
                builder.append("\", ");
            else
                builder.append("\"");
        }
        
        builder.append("}");
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, builder.toString());
        
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .addHeader("accept", "application/json")
                .addHeader("content-type", "application/json")
                .addHeader("authorization", hubtel.getBase64StringPass())
                .build();
        
        Response response = client.newCall(request).execute();
        // todo: check 404
        JSONObject jSONObject = new JSONObject(response.body().string());
        
        dstvReceipt.setAttributesFromJsonObject(response.code(), jSONObject);
        return dstvReceipt;
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
    public BillPaymentReceipt payDstvBill(String account, double amount, String foreignId) throws IOException {
        HashMap<String, Object> hashMap = new HashMap<>();
        
        hashMap.put("account", account);
        hashMap.put("amount", amount);
        hashMap.put("token", hubtel.getToken());
        hashMap.put("foreignId", foreignId);
        
        return this.payBill(Hubtel.getDstvBillPaymentUrl(), hashMap);
    }
    
    @Override
    public BillPaymentReceipt paySurflineBundle(String device, double amount, Surfline surfline, String foreignId) throws IOException {
        HashMap<String, Object> hashMap = new HashMap<>();
        
        Bundle bundle = surfline.getPaymentBundle();
        
        hashMap.put("device", device);
        hashMap.put("amount", amount);
        hashMap.put("bundle", bundle.getBundle());
        hashMap.put("token", hubtel.getToken());
        hashMap.put("foreignId", foreignId);
        
        return this.payBill(Hubtel.getSurflineBundleUrl(), hashMap);
    }

    @Override
    public BillPaymentReceipt payGoTvBill(String account, double amount, String foreignId) throws IOException {
        HashMap<String, Object> hashMap = new HashMap<>();
        
        hashMap.put("account", account);
        hashMap.put("amount", amount);
        hashMap.put("token", hubtel.getToken());
        hashMap.put("foreignId", foreignId);
        
        return this.payBill(Hubtel.getGoTvBillUrl(), hashMap);
    }

    @Override
    public BillPaymentReceipt payDstvBoBill(String account, double amount, String foreignId) throws IOException {
        HashMap<String, Object> hashMap = new HashMap<>();
        
        hashMap.put("account", account);
        hashMap.put("amount", amount);
        hashMap.put("token", hubtel.getToken());
        hashMap.put("foreignId", foreignId);
        
        return this.payBill(Hubtel.getDstvBoBillPaymentUrl(), hashMap);
    }
    
    @Override
    public BillPaymentReceipt payBusyBundle(String account, String foreignId, Busy busy) throws IOException {
        HashMap<String, Object> hashMap = new HashMap<>();
        
        Bundle bundle = busy.getPaymentBundle();
        
        hashMap.put("account", account);
        hashMap.put("amount", bundle.getValue());
        hashMap.put("bundle", bundle.getBundle());
        hashMap.put("token", hubtel.getToken());
        hashMap.put("foreignId", foreignId);
        
        return this.payBill(Hubtel.getBusyBundleUrl(), hashMap);
    }

    @Override
    public BillPaymentReceipt payTvLicense(String account, double amount, String foreignId) throws IOException {
        HashMap<String, Object> hashMap = new HashMap<>();
        
        hashMap.put("account", account);
        hashMap.put("amount", amount);
        hashMap.put("token", hubtel.getToken());
        hashMap.put("foreignId", foreignId);
        
        return this.payBill(Hubtel.getTvLicenseUrl(), hashMap);
    }

    @Override
    public BillPaymentReceipt payVodafoneBill(String account, double amount, VodafoneService service, String foreignId) throws IOException {
        HashMap<String, Object> hashMap = new HashMap<>();
        
        hashMap.put("account", account);
        hashMap.put("amount", amount);
        hashMap.put("token", hubtel.getToken());
        hashMap.put("service", service.getServiceName());
        hashMap.put("foreignId", foreignId);
        
        return this.payBill(Hubtel.getVodafoneBillUrl(), hashMap);
    }

    @Override
    public BillPaymentReceipt payMtnPostpaidBill(String phone, double amount, MtnService service, String foreignId) throws IOException {
        HashMap<String, Object> hashMap = new HashMap<>();
        
        hashMap.put("phone", phone);
        hashMap.put("amount", amount);
        hashMap.put("token", hubtel.getToken());
        hashMap.put("service", service.getServiceName());
        hashMap.put("foreignId", foreignId);
        
        return this.payBill(Hubtel.getMtnPostpaidUrl(), hashMap);
    }

    @Override
    public BillPaymentReceipt payEcgPostPaidBill(String account, double amount, String name, String foreignId) throws IOException {
        HashMap<String, Object> hashMap = new HashMap<>();
        
        hashMap.put("account", account);
        hashMap.put("amount", amount);
        hashMap.put("token", hubtel.getToken());
        hashMap.put("name", name);
        hashMap.put("foreignId", foreignId);
        
        return this.payBill(Hubtel.getEcgPostpaidBillPaymentUrl(), hashMap);
    }

    @Override
    public BillPaymentReceipt payAeroAirlineTicket(String account, double amount, String foreignId) throws IOException {
        HashMap<String, Object> hashMap = new HashMap<>();
        
        hashMap.put("account", account);
        hashMap.put("amount", amount);
        hashMap.put("token", hubtel.getToken());
        hashMap.put("foreignId", foreignId);
        
        return this.payBill(Hubtel.getAeroAirlineTicketUrl(), hashMap);
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
}
