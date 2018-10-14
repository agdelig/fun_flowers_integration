package com.funflowers.billing;

import com.flexionmobile.codingchallenge.integration.Integration;
import com.flexionmobile.codingchallenge.integration.Purchase;
import com.funflowers.apiconsumer.ApiConsumer;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * FunFlowersIntegration class 
 * Class implementing the Integration interface.
 * 
 * @author Agelos Deligiannis 
 */
public class FunFlowersIntegration implements Integration{
    private final ApiConsumer apiConsumer;
    private final String devId;
    
    /**
     * Constructor. 
     * 
     * @param devId String
     */
    public FunFlowersIntegration(String devId){
        this.apiConsumer = new ApiConsumer();
        this.devId = devId;
    }
    
    /**
     * Constructor. 
     * Defaults to devId = "dev".
     * Intended for testing purposes
     */
    public FunFlowersIntegration(){
        this("dev");
    }

    @Override
    public Purchase buy(String string) {
        JSONObject response = apiConsumer.buyItemResponse(devId, string);
        
        return parsePurchase(response);
    }

    @Override
    public List<Purchase> getPurchases() {
        JSONObject json = apiConsumer.allPurchasesResponce(devId);
        
        return parsePurchaseList(json);
    }

    @Override
    public void consume(Purchase prchs) {
        apiConsumer.consumePurchaseResponse(devId, prchs.getId());
    }
    
    /**
     * Creates a List<Purchase> out of given JSONObject. 
     * 
     * @param json JSONObject
     * @return List<Purchase>
     */
    public List<Purchase> parsePurchaseList(JSONObject json){
        List<Purchase> purchases = new ArrayList<>();
        
        if(json.has("purchases")){
            JSONArray array = json.getJSONArray("purchases");
            
            for(Object obj : array){
                if(obj instanceof JSONObject){
                    Purchase p = parsePurchase((JSONObject) obj);
                    if(p != null)purchases.add(p);
                }
            }
        }
        return purchases;
    }
    
    /**
     * Creates a Purchase object out of given JSONObject
     * @param json
     * @return 
     */
    public Purchase parsePurchase(JSONObject json){
        Purchase purchase = null;
 
        if(json != null 
                && json.has("id") 
                && json.has("consumed") 
                && json.has("itemId")){
            purchase = new FunFlowersPurchase(json.getString("id"), 
                    json.getBoolean("consumed"), 
                    json.getString("itemId"));
        }
        
        return purchase;
    }
    
}
