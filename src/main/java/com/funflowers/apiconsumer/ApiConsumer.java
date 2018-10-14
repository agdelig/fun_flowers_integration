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
 * ApiConsumer class 
 * Used to consume the "http://sandbox.flexionmobile.com/javachallenge/rest/developer/"
 * api. Routes and spec are defined on the challenge doc.
 * 
 * @author Agelos Deligiannis
 */
public class ApiConsumer {
    private final String baseWebSwrvice = "http://sandbox.flexionmobile.com/javachallenge/rest/developer/";
    
    /**
     * Method used to make a POST request to 
     * "http://sandbox.flexionmobile.com/javachallenge/rest/developer/{devId}/buy/{itemId}"
     * 
     * @param devId String
     * @param itemId String
     * @return JSONObject
     */
    public JSONObject buyItemResponse(String devId, String itemId){
        try {
            String buyItemWebService = this.baseWebSwrvice + "%s/buy/%s";
            String wS = String.format(buyItemWebService, devId, itemId);
            JSONObject body = new JSONObject();
            body.put("developerId", devId);
            body.put("itemId", itemId);
            return makePostRequest(wS, body);
        } catch (Exception ex) {
            Logger.getLogger(ApiConsumer.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    /**
     * Method used to make a GET request to  route 
     * "http://sandbox.flexionmobile.com/javachallenge/rest/developer/{devId}/all" 
     * 
     * @param devId String
     * @return JSONObject
     */
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
    
    /**
     * Method used to make a POST request to 
     * "http://sandbox.flexionmobile.com/javachallenge/rest/developer/{devId}/consume/{purchaseId}" 
     * 
     * @param devId String
     * @param purchaseId String 
     */
    public void consumePurchaseResponse(String devId, String purchaseId){
        try {
            String consumePurchaseWebService = this.baseWebSwrvice + "%s/consume/%s";
            String wS = String.format(consumePurchaseWebService, devId, purchaseId);
            
            JSONObject body = new JSONObject();
            body.put("developerId", devId);
            body.put("purchaseId", purchaseId);
            
            
            makePostRequest(wS, body);
        } catch (Exception ex) {
            Logger.getLogger(ApiConsumer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Private method preparing connection to given url. 
     * 
     * @param webService String
     * @return HttpURLConnection
     * @throws MalformedURLException
     * @throws IOException 
     */
    private HttpURLConnection prepareConnection(String webService) throws MalformedURLException, IOException{
        URL url = new URL(webService);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestProperty("Accept", "application/json");
        
        return connection;
    }
    
    /**
     * Private method making GET request to given url.  
     * Returns Response as a JSONObject.
     * 
     * @param webService Sting
     * @return JSONObject
     * @throws MalformedURLException
     * @throws ProtocolException
     * @throws IOException 
     */
    private JSONObject makeGetRequest(String webService) throws MalformedURLException, ProtocolException, IOException{
        HttpURLConnection connection = prepareConnection(webService);
        connection.setRequestMethod("GET");
        
        
        return getResponse(connection);
    }
    
    /**
     * Private method making POST request to given url 
     * attaching given body. 
     * Returns Response as a JSONObject.
     * 
     * @param webService String 
     * @param body String
     * @return JSONObject
     * @throws ProtocolException
     * @throws MalformedURLException
     * @throws IOException 
     */
    private JSONObject makePostRequest(String webService, JSONObject body) throws ProtocolException, MalformedURLException, IOException{
        HttpURLConnection connection = prepareConnection(webService);
        connection.setRequestMethod("POST");
        
        connection.setDoOutput(true);
        OutputStreamWriter stream = new OutputStreamWriter(connection.getOutputStream());
        stream.write(body.toString());
        stream.flush();
        
        
        
        return getResponse(connection);
    }
    
    /**
     * Private method used to read response from given connection and return it as a JSONObject. 
     * 
     * @param connection HttpURLConnection
     * @return JSONObject
     * @throws IOException 
     */
    private JSONObject getResponse(HttpURLConnection connection) throws IOException{
        int responseCode = connection.getResponseCode();
        
        if(responseCode == 200){
            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            
            String line;
            StringBuilder output = new StringBuilder();
            
            while((line = br.readLine()) != null){
                output.append(line);
            }
            
            JSONObject json;
            json = output.length() > 0 ? new JSONObject(output.toString()) : null;

            return json;
        }else{
            return null;
        }
    }
}
