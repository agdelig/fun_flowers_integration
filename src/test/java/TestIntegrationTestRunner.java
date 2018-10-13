/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.flexionmobile.codingchallenge.integration.IntegrationTestRunner;
import com.funflowers.billing.FunFlowersIntegration;
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
    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    @Test
    public void hello() {
        System.out.println("**************************************************");
        IntegrationTestRunner testRunner = new IntegrationTestRunner();
        testRunner.runTests(new FunFlowersIntegration());
    }
}
