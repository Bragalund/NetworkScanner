package com.bragalund;

import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class ScannerTest {

  @Test
    public void findOtherDevicesOnNetwork(){
      Scanner scanner = new Scanner();

     Map IPsOfOtherDevicesOnNetwork = scanner.findIPOfOtherDevicesOnNetwork();

     assertNotNull(IPsOfOtherDevicesOnNetwork);
  }
}
