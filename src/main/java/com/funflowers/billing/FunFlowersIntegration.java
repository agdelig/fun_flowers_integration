package com.funflowers.billing;

import com.flexionmobile.codingchallenge.integration.Integration;
import com.flexionmobile.codingchallenge.integration.Purchase;
import com.funflowers.apiconsumer.ApiConsumer;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author Agelos
 */
public class FunFlowersIntegration implements Integration{
    private final ApiConsumer apiConsumer;
    private final String devId;
    
    public FunFlowersIntegration(){
        this.apiConsumer = new ApiConsumer();
        this.devId = "dev";
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
    
    private List<Purchase> parsePurchaseList(JSONObject json){
        List<Purchase> purchases = new ArrayList<>();
        
        if(json.has("purchases")){
            JSONArray array = json.getJSONArray("purchases");
            
            for(Object obj : array){
                if(obj instanceof JSONObject){
                    purchases.add(parsePurchase((JSONObject) obj));
                }
            }
        }
        return purchases;
    }
    
    private Purchase parsePurchase(JSONObject json){
        Purchase purchase = null;
        if(json != null){
            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> " + json);
        }
        if(json != null 
                &&json.has("id") 
                && json.has("consumed") 
                && json.has("itemId")){
            purchase = new FunFlowersPurchase(json.getString("id"), 
                    json.getBoolean("consumed"), 
                    json.getString("itemId"));
        }
        
        return purchase;
    }
    
}
