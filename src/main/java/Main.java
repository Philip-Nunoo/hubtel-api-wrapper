
import com.phil.hubtelpaymentapi.Config;
import com.phil.hubtelpaymentapi.pojos.Surfline;
import com.phil.hubtelpaymentapi.Hubtel;
import com.phil.hubtelpaymentapi.HubtelVend;
import com.phil.hubtelpaymentapi.pojos.Busy;
import com.phil.hubtelpaymentapi.Hub.HubtelNetworks;
import com.phil.hubtelpaymentapi.exceptions.PaymentException;
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
            hubtelPayment.purchaseAirtime("0206242008", HubtelNetworks.GH_VODAFONE, 0.2, null);
            
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
        } catch (PaymentException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
