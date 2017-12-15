/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.phil.hubtelpaymentapi.interfaces;

import com.phil.hubtelpaymentapi.Hub.HubtelNetworks;
import com.phil.hubtelpaymentapi.exceptions.PaymentException;
import com.phil.hubtelpaymentapi.pojos.Busy;
import com.phil.hubtelpaymentapi.pojos.Surfline;
import com.phil.hubtelpaymentapi.pojos.VendBalance;
import com.phil.hubtelpaymentapi.pojos.Vodafone;
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
    
    /**
     * Pay for Surfline data bundle
     * @param surfline
     * @throws java.io.IOException
     */
    public void paySurflineBundle(Surfline surfline) throws IOException;
    
    /**
     * Pay for Busy data bundle
     * @param busy
     * @throws java.io.IOException
     */
    public void payBusyBundle(Busy busy) throws IOException;
    
    /**
     * Pay for Vodafone data bundle
     * @throws java.io.IOException
     */
    public void payVodafoneBundle() throws IOException;
}
