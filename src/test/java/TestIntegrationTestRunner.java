/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.flexionmobile.codingchallenge.integration.IntegrationTestRunner;
import com.flexionmobile.codingchallenge.integration.Purchase;
import com.funflowers.billing.FunFlowersIntegration;
import org.json.JSONObject;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Agelos
 */
public class TestIntegrationTestRunner {
    static JSONObject json;
    public TestIntegrationTestRunner() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
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
    public void hello() {
        //IntegrationTestRunner testRunner = new IntegrationTestRunner();
        //testRunner.runTests(new FunFlowersIntegration());
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
}
