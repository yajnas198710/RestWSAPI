package com.reno.test;
 
import static org.junit.Assert.assertTrue;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import com.reno.AccountManager;
import com.reno.RestServer;

 
 
public class AccountManagerTestSuccessful {
 public static RestServer restServer;
 public static AccountManager accountManager = null;
final static Logger logger = Logger.getLogger(AccountManagerTestSuccessful.class);
 
	@BeforeClass
    public static void initRestService() throws Exception {
    	if(accountManager == null){
    		accountManager = new AccountManager();
    	}
    }
 
    @SuppressWarnings("static-access")
	@Before
    public void beforeEachTest() throws Exception {
    	restServer.main(null);
    	logger.info("This is info : " + "beforeEachTest - RestServer Start");
    }
 
    @After
    public void afterEachTest() {
        System.out.println("This is exceuted after each Test");
    }
 
    @Test
    public void testAddAccount() {
    	logger.info("This is info : " + "addAccount - Start");
        accountManager.addAccount("SANJAY");
        logger.info("This is info : " + "addAccount - Done");
    }
 
    @Test
    public void testDivison() {
        try {
       
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }
    }
 
    @Test(expected = Exception.class)
    public void testDivisionException() throws Exception {
         
    }
 
    @Ignore
    @Test
    public void testEqual() {
         
    }
 
    @Ignore
    @Test
    public void testSubstraction() {
        int result = 10 - 3;
 
        assertTrue(result == 9);
    }
}