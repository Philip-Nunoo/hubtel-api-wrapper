
import com.phil.hubtelpaymentapi.Config;
import com.phil.hubtelpaymentapi.models.Surfline;
import com.phil.hubtelpaymentapi.Hubtel;
import com.phil.hubtelpaymentapi.HubtelVend;
import com.phil.hubtelpaymentapi.models.Busy;
import com.phil.hubtelpaymentapi.Hub.HubtelNetworks;
import com.phil.hubtelpaymentapi.exceptions.PaymentException;
import com.phil.hubtelpaymentapi.responses.AirtimePurchaseResponse;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Philip
 */
public class Main {
    public static void main(String[] args) {        
        Hubtel hubtel = new Hubtel(Config.USER_TOKEN, Config.CLIENT_SECRET, Config.CLIENT_PASSWORD);
        HubtelVend hubtelPayment = new HubtelVend(hubtel);
        
        try {
            /** Test airtime **/
//            AirtimePurchaseResponse response = hubtelPayment.purchaseAirtime("0546140388", HubtelNetworks.GH_MTN, 0.5, null);
            hubtelPayment.payDstvBill("dssd", 322, "dsfd");
            /** Test Surfline query **/
            // Surfline surfline = hubtelPayment.getUserSurflineInfo("233255027342");
            // get the first data package. In this case 
            // surfline.setPaymentBundle(surfline.getBundles().get(0));
            // hubtelPayment.paySurflineBundle(surfline);
            /** Test busy **/
            // Busy busy = hubtelPayment.getUserBusyInfo("104899");
            // System.err.println(busy);
            // busy.setPaymentBundle(busy.getBundles().get(2));
            // hubtelPayment.payBusyBundle(busy);
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } 
//        catch (PaymentException ex) {
//            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }
}
