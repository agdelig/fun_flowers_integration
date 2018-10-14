/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.funflowers.tests.integration;

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
