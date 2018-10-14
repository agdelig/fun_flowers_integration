package com.funflowers.tests.integration;

import com.flexionmobile.codingchallenge.integration.IntegrationTestRunner;
import com.funflowers.billing.FunFlowersIntegration;
import org.junit.Test;

/**
 * Test class to run IntegrationTestRunner provided in pluginapi.jar. 
 * 
 * @author Agelos Deligiannis
 */
public class IntegrationTest {
    
    public IntegrationTest() {
    }
    
    @Test
    public void hello() {
        IntegrationTestRunner testRunner = new IntegrationTestRunner();
        System.out.println("Running Integration tests!");
        testRunner.runTests(new FunFlowersIntegration());
    }
}
