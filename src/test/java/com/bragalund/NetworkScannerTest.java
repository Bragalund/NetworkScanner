package com.bragalund;

import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class NetworkScannerTest{

  @Test
    public void findOtherDevicesOnNetwork(){
      NetworkScanner networkScanner = new NetworkScanner();

     Map IPsOfOtherDevicesOnNetwork = networkScanner.findIPOfOtherDevicesOnNetwork();

     assertNotNull(IPsOfOtherDevicesOnNetwork);
  }
}
