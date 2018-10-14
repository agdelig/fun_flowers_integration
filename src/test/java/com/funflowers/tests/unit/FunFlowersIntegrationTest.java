package com.funflowers.tests.unit;

import com.flexionmobile.codingchallenge.integration.Purchase;
import com.funflowers.billing.FunFlowersIntegration;
import java.util.List;
import org.json.JSONObject;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Agelos Deligiannis 
 */
public class FunFlowersIntegrationTest {
    static JSONObject json;
    public FunFlowersIntegrationTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
        json = null;
        assertNull(json);
    }
    
    @Before
    public void setUp() {
        json = new JSONObject();

        json.put("id", "dev")
                .put("consumed", true)
                .put("itemId", "item1");
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void createPurchaseValidJson(){
        FunFlowersIntegration integration = new FunFlowersIntegration();
        
        Purchase purchase = integration.parsePurchase(json);
        
        assertEquals(purchase.getId(), json.get("id"));
        assertEquals(purchase.getConsumed(), json.get("consumed"));
        assertEquals(purchase.getItemId(), json.get("itemId"));
    }
    
    @Test
    public void createPurchaseMissingId(){
        FunFlowersIntegration integration = new FunFlowersIntegration();
        json.remove("id");
        Purchase purchase = integration.parsePurchase(json);
        
        assertFalse(json.has("id"));
        assertNull(purchase);
    }
    
    @Test
    public void createPurchaseMissingItemId(){
        FunFlowersIntegration integration = new FunFlowersIntegration();
        json.remove("itemId");
        Purchase purchase = integration.parsePurchase(json);
        
        assertFalse(json.has("itemId"));
        assertNull(purchase);
    }
    
    @Test
    public void createPurchaseMissingConsumed(){
        FunFlowersIntegration integration = new FunFlowersIntegration();
        json.remove("consumed");
        Purchase purchase = integration.parsePurchase(json);
        
        assertFalse(json.has("consumed"));
        assertNull(purchase);
    }
    
    @Test
    public void purchaseListValidJson(){
        FunFlowersIntegration integration = new FunFlowersIntegration();
        JSONObject purchases = new JSONObject();
        purchases.append("purchases", json);
        purchases.append("purchases", json);
        List<Purchase> purchase = integration.parsePurchaseList(purchases);
        
        assertEquals(2, purchase.size());
    }
    
    @Test
    public void purchaseListInvalidJson(){
        FunFlowersIntegration integration = new FunFlowersIntegration();
        JSONObject purchases = new JSONObject();
        purchases.append("purch", json);
        purchases.append("purch", json);
        List<Purchase> purchase = integration.parsePurchaseList(purchases);
        
        assertEquals(0, purchase.size());
    }
    
    @Test
    public void purchaseListInvalidPurchaseJson(){
        FunFlowersIntegration integration = new FunFlowersIntegration();
        JSONObject purchases = new JSONObject();
        json.remove("id");
        purchases.append("purchases", json);
        purchases.append("purchases", json);
        List<Purchase> purchase = integration.parsePurchaseList(purchases);
        
        assertEquals(0, purchase.size());
    }
    
    @Test
    public void purchaseListInvalidAndValidPurchaseJson(){
        FunFlowersIntegration integration = new FunFlowersIntegration();
        JSONObject purchases = new JSONObject();
        purchases.append("purchases", json);
        JSONObject invJson = new JSONObject();
        invJson.put("id", "dev");
        invJson.put("itemId", "item1");
        purchases.append("purchases", invJson);
        List<Purchase> purchase = integration.parsePurchaseList(purchases);
        
        assertEquals(1, purchase.size());
    }
}
