package com.funflowers.apiconsumer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONObject;

/**
 *
 * @author Agelos
 */
public class ApiConsumer {
    private final String baseWebSwrvice = "http://sandbox.flexionmobile.com/javachallenge/rest/developer/";
    
    public JSONObject buyItemResponse(String devId, String itemId){
        try {
            String buyItemWebService = this.baseWebSwrvice + "%s/buy/%s";
            String wS = String.format(buyItemWebService, devId, itemId);
            System.out.println("+++++++++++++++++++++++++++++++++++++\n" + wS);
            JSONObject body = new JSONObject();
            body.put("developerId", devId);
            body.put("itemId", itemId);
            return makePostRequest(wS, body);
        } catch (Exception ex) {
            Logger.getLogger(ApiConsumer.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public JSONObject allPurchasesResponce(String devId){
        try {
            String allPurchasesWebService = this.baseWebSwrvice + "%s/all";
            String wS = String.format(allPurchasesWebService, devId);
            return makeGetRequest(wS);
        } catch (Exception ex) {
            Logger.getLogger(ApiConsumer.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public JSONObject consumePurchaseResponse(String devId, String purchaseId){
        try {
            String consumePurchaseWebService = this.baseWebSwrvice + "%s/consume/%s";
            String wS = String.format(consumePurchaseWebService, devId, purchaseId);
            
            JSONObject body = new JSONObject();
            body.put("developerId", devId);
            body.put("purchaseId", purchaseId);
            
            
            return makePostRequest(wS, body);
        } catch (Exception ex) {
            Logger.getLogger(ApiConsumer.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    private HttpURLConnection prepareConnection(String webService) throws MalformedURLException, IOException{
        URL url = new URL(webService);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestProperty("Accept", "application/json");
        
        return connection;
    }
    
    private JSONObject makeGetRequest(String webService) throws MalformedURLException, ProtocolException, IOException{
        HttpURLConnection connection = prepareConnection(webService);
        connection.setRequestMethod("GET");
        
        
        return getResponse(connection);
    }
    
    private JSONObject makePostRequest(String webService, JSONObject body) throws ProtocolException, MalformedURLException, IOException{
        HttpURLConnection connection = prepareConnection(webService);
        connection.setRequestMethod("POST");
        System.out.println(";;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;; " + body);
        
        connection.setDoOutput(true);
        OutputStreamWriter stream = new OutputStreamWriter(connection.getOutputStream());
        stream.write(body.toString());
        stream.flush();
        
        
        
        return getResponse(connection);
    }
    
    private JSONObject getResponse(HttpURLConnection connection) throws IOException{
        int responseCode = connection.getResponseCode();
        System.out.println("------------------------------- " + responseCode + connection.getURL());
        
        if(responseCode == 200){
            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            
            String line;
            StringBuilder output = new StringBuilder();
            
            while((line = br.readLine()) != null){
                output.append(line);
            }
            
            System.out.println(output + " ------------------------------- ");
            JSONObject json;
            json = output.length() > 0 ? new JSONObject(output.toString()) : null;

            return json;
        }else{
            return null;
        }
    }
}
