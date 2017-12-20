/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.phil.hubtelpaymentapi.interfaces;

import com.phil.hubtelpaymentapi.Hub.HubtelNetworks;
import com.phil.hubtelpaymentapi.Hub.MtnService;
import com.phil.hubtelpaymentapi.Hub.VodafoneService;
import com.phil.hubtelpaymentapi.exceptions.PaymentException;
import com.phil.hubtelpaymentapi.models.Busy;
import com.phil.hubtelpaymentapi.models.BillPaymentReceipt;
import com.phil.hubtelpaymentapi.models.Surfline;
import com.phil.hubtelpaymentapi.models.VendBalance;
import com.phil.hubtelpaymentapi.models.Vodafone;
import com.phil.hubtelpaymentapi.responses.AirtimePurchaseResponse;
import java.io.IOException;

/**
 *
 * @author Philip
 */
public interface PaymentInterface {
    
    /**
     * Vend balance
     * @return 
     * @throws java.io.IOException
     */
    public VendBalance checkVendBalance() throws IOException;
    
    /** 
     * Send Air time to user phone number
     * @param phoneNumber
     * @param network
     * @param amount
     * @param foreignId
     * @return 
     * @throws com.phil.hubtelpaymentapi.exceptions.PaymentException
     * @throws java.io.IOException
     */
    public AirtimePurchaseResponse purchaseAirtime(String phoneNumber, HubtelNetworks network, double amount, String foreignId) throws PaymentException, IOException;
    
    /**
     * Pay for DSTV bill
     * @param account
     * @param amount
     * @param foreignId
     * @return 
     * @throws java.io.IOException
     */
    public BillPaymentReceipt payDstvBill(String account, double amount, String foreignId) throws IOException;
    
    /**
     * Pay for Surfline data bundle
     * @param device
     * @param amount
     * @param surfline
     * @param foreignId
     * @return 
     * @throws java.io.IOException
     */
    public BillPaymentReceipt paySurflineBundle(String device, double amount, Surfline surfline, String foreignId) throws IOException;
    
    /**
     * Pay for GoTv bill
     * @param account
     * @param amount
     * @param foreignId
     * @return 
     * @throws java.io.IOException
     */
    public BillPaymentReceipt payGoTvBill(String account, double amount, String foreignId) throws IOException;
    
    /**
     * Pay for DSTVbo bill
     * @param account
     * @param amount
     * @param foreignId
     * @return 
     * @throws java.io.IOException
     */
    public BillPaymentReceipt payDstvBoBill(String account, double amount, String foreignId) throws IOException;
    
    /**
     * Pay for Busy data bundle
     * @param account
     * @param foreignId
     * @param busy
     * @return 
     * @throws java.io.IOException
     */
    public BillPaymentReceipt payBusyBundle(String account, String foreignId, Busy busy) throws IOException;
    
    /**
     * Pay for TV license bundle
     * @param account
     * @param amount
     * @param foreignId
     * @return 
     * @throws java.io.IOException
     */
    public BillPaymentReceipt payTvLicense(String account, double amount, String foreignId) throws IOException;
    
    /**
     * Pay for Vodafone bills
     * @param account
     * @param amount
     * @param service
     * @param foreignId
     * @return 
     * @throws java.io.IOException
     */
    public BillPaymentReceipt payVodafoneBill(String account, double amount, VodafoneService service, String foreignId) throws IOException;
    
    /**
     * Pay for MTN Postpaid Bills
     * @param phone
     * @param amount
     * @param service
     * @param foreignId
     * @return 
     * @throws java.io.IOException
     */
    public BillPaymentReceipt payMtnPostpaidBill(String phone, double amount, MtnService service, String foreignId) throws IOException;
    
    // TODO: hubtel
    // TODO: mobile money
    
    /**
     * Pay for ECG Post Paid
     * @param account
     * @param amount
     * @param name
     * @param foreignId
     * @return 
     * @throws java.io.IOException
     */
    public BillPaymentReceipt payEcgPostPaidBill(String account, double amount, String name, String foreignId) throws IOException;
        
    /**
     * Pay for AERO Airline Tickets
     * @param account
     * @param amount
     * @param foreignId
     * @return 
     * @throws java.io.IOException
     */
    public BillPaymentReceipt payAeroAirlineTicket(String account, double amount, String foreignId) throws IOException;
        
    /**
     * get Surfline user data info 
     * @param phoneNumber
     * @return 
     * @throws java.io.IOException
     */
    public Surfline getUserSurflineInfo(String phoneNumber) throws IOException;
    
    /**
     * get busy user data info
     * @param number
     * @return 
     * @throws java.io.IOException 
     */
    public Busy getUserBusyInfo(String number) throws IOException;
    
    /**
     * get Vodafone user data info
     * @param account
     * @return 
     * @throws java.io.IOException
     */
    public Vodafone getUserVodafoneInfo(String account) throws IOException;
}
